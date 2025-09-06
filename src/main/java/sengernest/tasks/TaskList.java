package sengernest.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /**
     * The list storing all tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add null task to list";
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     */
    public Task getTask(int index) {
        Task t = this.tasks.get(index);
        assert t != null : "Task retrieved should not be null";
        return t;
    }

    /**
     * Marks the task at the specified index as finished.
     */
    public boolean markTask(int index) {
        Task t = this.getTask(index);
        assert t != null : "Task to mark should not be null";
        if (t.isFinished()) {
            return false;
        }
        t.finish();
        return true;
    }

    /**
     * Unmarks the task at the specified index (sets it as not finished).
     */
    public boolean unmarkTask(int index) {
        Task t = this.getTask(index);
        assert t != null : "Task to unmark should not be null";
        if (t.isFinished()) {
            return false;
        }
        t.unfinish();
        return true;
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a copy of the task list.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * Returns whether the task list is empty.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
