package sengernest.commands;
import sengernest.exceptions.EmptyTaskListException;
import sengernest.exceptions.InvalidTaskNumberException;
import sengernest.exceptions.MarkFinishedTaskException;
import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String indexStr) {
        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid number after 'mark'.");
        }
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.size() == 0) { 
                throw new EmptyTaskListException("Your list is empty! Add tasks first!");
            } else if (index < 0 || index >= tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number! Only choose valid task numbers in the list.");
            } else if (!tasks.markTask(index)) {
                throw new MarkFinishedTaskException("Task already marked!");
            } else {
                tasks.markTask(index);
                storage.save(tasks);
                ui.displayMessage("Marked task " + (index + 1) + " as done.");
                ui.printList(tasks);
            }
        } catch (Exception e) {
            System.out.println(e); 
        }
    }
}
