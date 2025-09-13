package sengernest.commands;

import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * Represents an abstract command in Sengernest.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The task list on which the command operates.
     * @param ui      The UI handler for displaying messages.
     * @param storage The storage handler for saving changes if needed.
     * @throws Exception If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Indicates whether this command will exit the application.
     *
     * @return true if this command exits the program; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
