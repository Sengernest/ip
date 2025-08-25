package sengernest.commands;
import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    
    public boolean isExit() {
        return false;
    }
}
