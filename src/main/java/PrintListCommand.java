public class PrintListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
