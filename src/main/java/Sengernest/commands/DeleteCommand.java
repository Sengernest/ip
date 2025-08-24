package Sengernest.commands;
import Sengernest.exceptions.EmptyTaskListException;
import Sengernest.exceptions.InvalidTaskNumberException;
import Sengernest.storage.Storage;
import Sengernest.tasks.Task;
import Sengernest.tasks.TaskList;
import Sengernest.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String indexStr) {
        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid number after 'delete'.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.size() == 0) { 
                throw new EmptyTaskListException("Your list is empty! Add tasks first!");
            } else if (index < 0 || index >= tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number! Only choose valid task numbers in the list.");
            } else {
                Task removed = tasks.getTask(index);
                tasks.removeTask(index);
                storage.save(tasks);
                ui.displayMessage("Deleted from list: " + removed.getTasking());
                ui.printList(tasks);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


