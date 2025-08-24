public class task {
    private final String tasking;
    private boolean finished;

    public task(String tasking) {
        this.tasking = tasking;
        this.finished = false;
    }

    public boolean isEmpty() {
        return this.tasking.isEmpty();
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void finish() {
        this.finished = true;
    }

    public void unfinish() {
        this.finished = false;
    }

    public String getTasking() {
        if (this.finished) {
            return "[X] " + this.tasking;
        } else {
            return "[ ] " + this.tasking;
        }
    }

    public String getTaskDescription() {
        return this.tasking;
    }

    public String toFileFormat() {
        if (this.isFinished()) {
            return "| 1 | " + this.getTaskDescription(); 
        } else {
            return "| 0 | " + this.getTaskDescription() ; 
        }
    }
}

