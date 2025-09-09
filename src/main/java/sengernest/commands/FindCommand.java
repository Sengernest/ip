package sengernest.commands;

import sengernest.storage.Storage;
import sengernest.tasks.Task;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * Represents a command that searches for tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    /**
     * The keyword to search for in task descriptions.
     */
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command: searches for tasks containing the keyword
     * and sets the output message in the UI.
     *
     * @param tasks   the TaskList to search through
     * @param ui      the UI for displaying messages
     * @param storage the Storage (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.getTasking().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.displayMessage("No matching tasks found for: " + keyword);
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks:\n");
            int count = 1;
            for (Task t : matchingTasks.getTasks()) {
                sb.append(count).append(". ").append(t.getTasking()).append("\n");
                count++;
            }
            ui.displayMessage(sb.toString().trim());
        }
    }
}
