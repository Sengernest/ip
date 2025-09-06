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
        return this.tasks.get(index);
    }

    /**
     * Marks the task at the specified index as finished.
     */
    public boolean markTask(int index) {
        Task task = getTask(index);
        if (task.isFinished()) {
            return false;
        }
        task.finish();
        return true;
    }

    /**
     * Unmarks the task at the specified index (sets it as not finished).
     */
    public boolean unmarkTask(int index) {
        Task task = getTask(index);
        if (!task.isFinished()) {
            return false;
        }
        task.unfinish();
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
