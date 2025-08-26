package sengernest.commands;

import sengernest.storage.Storage;
import sengernest.tasks.Task;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddTaskCommand extends Command {
    /** The task to be added to the task list. */
    private final Task task;

    /**
     * Constructs an AddTaskCommand with the specified task.
     *
     * @param task The task to add to the list.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks   The task list to which the task will be added.
     * @param ui      The UI handler for displaying messages.
     * @param storage The storage handler for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.save(tasks);
        } catch (Exception e) {
            ui.displayMessage("[save error] " + e.getMessage());
        }
        ui.displayMessage("Added to list: " + task.getTasking());
        ui.printList(tasks);
    }
}
