public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.save(tasks);
        } catch (Exception e) {
            ui.displayMessage("[save error] " + e.getMessage());
        }
        ui.displayMessage("Added to list: " + task.getTasking());
        ui.printList(tasks);
    }
}
