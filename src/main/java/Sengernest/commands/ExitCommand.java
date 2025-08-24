package Sengernest.commands;
import Sengernest.storage.Storage;
import Sengernest.tasks.TaskList;
import Sengernest.ui.Ui;

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
