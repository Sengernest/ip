package sengernest.commands;

import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * Represents a command to print the current task list.
 */
public class PrintListCommand extends Command {

    /**
     * Executes the print list command.
     *
     * @param tasks   The task list to display.
     * @param ui      The UI handler for printing the task list.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
