public class event extends task {
    private final String startDate;
    private final String endDate;

    public event(String tasking, String startDate, String endDate) {
        super(tasking);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTasking() {
        return "[E]" + super.getTasking() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
