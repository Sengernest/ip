public class deadline extends task {
    private final String date;

    public deadline(String tasking, String date) {
        super(tasking);
        this.date = date;
    }

    @Override
    public String getTasking() {
        return "[D]" + super.getTasking() + " (by: " + this.date + ")";
    }

    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + " (by: " + this.date + ")";
    }
}
