package sengernest.tasks;
public class ToDo extends Task {

    public ToDo(String tasking) {
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
