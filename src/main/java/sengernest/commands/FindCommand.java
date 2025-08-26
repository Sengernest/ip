package sengernest.commands;

import sengernest.storage.Storage;
import sengernest.tasks.Task;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.getTasking().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.displayMessage("No matching tasks found for: " + keyword);
        } else {
            ui.displayMessage("Here are the matching tasks:");
            new PrintListCommand().execute(matchingTasks, ui, storage);
        }
    }
}
