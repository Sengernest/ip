package Sengernest.commands;
import Sengernest.storage.Storage;
import Sengernest.tasks.TaskList;
import Sengernest.ui.Ui;

public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    
    public boolean isExit() {
        return false;
    }
}
