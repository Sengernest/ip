public class ExitCommand extends Command {
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage("Goodbye, hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
