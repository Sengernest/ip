package sengernest.commands;

import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

public class PrintListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
