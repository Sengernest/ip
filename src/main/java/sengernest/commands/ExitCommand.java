package sengernest.commands;

import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * Represents a command to exit Sengernest.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks   The task list (not modified by this command).
     * @param ui      The UI handler for displaying messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage("Goodbye, hope to see you again soon!");
    }

    /**
     * Indicates that this command will terminate the application.
     *
     * @return true, since this command exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
