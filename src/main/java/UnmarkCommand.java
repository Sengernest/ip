public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String indexStr) {
        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid number after 'mark'.");
        }
    }
    
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (index < 0 || index >= tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number! Only choose valid task numbers in the list.");
            } else if (!tasks.unmarkTask(index)) {
                throw new UnmarkUnfinishedTaskException("Task already unmarked!");
            } else {
                tasks.unmarkTask(index);
                storage.save(tasks);
                ui.displayMessage("Unmarked task " + (index + 1));
                ui.printList(tasks);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
