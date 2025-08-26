package sengernest.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public boolean markTask(int index) {
        if (this.getTask(index).isFinished()) {
            return false;
        }
        this.getTask(index).finish();
        return true;
    }

    public boolean unmarkTask(int index) {
        if (!this.getTask(index).isFinished()) {
            return false;
        }
        this.getTask(index).unfinish();
        return true;
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
