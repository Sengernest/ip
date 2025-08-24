package Sengernest.commands;
import Sengernest.storage.Storage;
import Sengernest.tasks.TaskList;
import Sengernest.ui.Ui;

public class PrintListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
