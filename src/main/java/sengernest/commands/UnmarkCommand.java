package sengernest.commands;

import sengernest.exceptions.EmptyTaskListException;
import sengernest.exceptions.InvalidTaskNumberException;
import sengernest.exceptions.UnmarkUnfinishedTaskException;
import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * Represents a command to unmark a task as unfinished in the task list.
 */
public class UnmarkCommand extends Command {
    /**
     * The zero-based index of the task to unmark.
     */
    private final int index;

    /**
     * Constructs an UnmarkCommand from a string representing the task number.
     *
     * @param indexStr The 1-based task number provided by the user.
     * @throws IllegalArgumentException if the provided string is not a valid number.
     */
    public UnmarkCommand(String indexStr) {
        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid number after 'mark'.");
        }
    }

    /**
     * Executes the unmark command.
     *
     * @param tasks   The task list containing the task to unmark.
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
            } else if (!tasks.unmarkTask(index)) {
                throw new UnmarkUnfinishedTaskException("Task already unmarked!");
            } else {
                tasks.unmarkTask(index);
                storage.save(tasks);
                ui.displayMessage("Unmarked task " + (index + 1));
                ui.printList(tasks);
            }
        } catch (Exception e) {
            ui.displayMessage("[Error] " + e.getMessage());
        }
    }
}
