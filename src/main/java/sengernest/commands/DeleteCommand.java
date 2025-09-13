package sengernest.commands;

import sengernest.exceptions.EmptyTaskListException;
import sengernest.exceptions.InvalidTaskNumberException;
import sengernest.storage.Storage;
import sengernest.tasks.Task;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * The zero-based index of the task to delete.
     */
    private final int index;

    /**
     * Constructs a DeleteCommand from a string representing the task number.
     *
     * @param indexStr The 1-based task number provided by the user.
     * @throws IllegalArgumentException if the provided string is not a valid number.
     */
    public DeleteCommand(String indexStr) {
        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid number after 'delete'.");
        }
    }

    /**
     * Executes the delete command.
     *
     * @param tasks   The task list containing the task to delete.
     * @param ui      The UI handler for displaying messages.
     * @param storage The storage handler for saving changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.size() == 0) {
                throw new EmptyTaskListException("Your list is empty! Add tasks first!");
            } else if (index < 0 || index >= tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number! "
                        + "Only choose valid task numbers in the list.");
            } else {
                Task removed = tasks.getTask(index);
                tasks.removeTask(index);
                storage.save(tasks);
                ui.displayMessage("Deleted from list: " + removed.getTasking());
                ui.printList(tasks);
            }
        } catch (Exception e) {
            ui.displayMessage("[Error] " + e.getMessage());
        }
    }
}
