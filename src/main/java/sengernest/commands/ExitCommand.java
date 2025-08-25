package sengernest.commands;
import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage("Goodbye, hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
