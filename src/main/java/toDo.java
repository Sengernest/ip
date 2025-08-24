public class toDo extends task {

    public toDo(String tasking) {
        super(tasking);
    }

    @Override
    public String getTasking() {
        return "[T]" + super.getTasking();
    }

    @Override
    public String toFileFormat() {
        return "T " + super.toFileFormat();
    }
}
