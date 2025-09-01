package sengernest.ui;

import sengernest.tasks.TaskList;

/**
 * Handles user interaction for Sengernest.
 */
public class Ui {

    /** Stores the last message for GUI display */
    private String lastMessage = "";

    /**
     * Sets the last message.
     *
     * @param message The message to store.
     */
    private void setLastMessage(String message) {
        lastMessage = message;
    }

    /**
     * Returns the last message stored.
     *
     * @return Last message string.
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Greets the user with a welcome message.
     */
    public void greet() {
        setLastMessage("Hello! I'm Sengernest");
    }

    /**
     * Displays a message.
     *
     * @param message Message string.
     */
    public void displayMessage(String message) {
        setLastMessage(message);
    }

    /**
     * Displays an error message.
     *
     * @param message Error message string.
     */
    public void displayError(String message) {
        setLastMessage("[Error] " + message);
    }

    /**
     * Prints the current task list.
     *
     * @param tasks Task list.
     */
    public void printList(TaskList tasks) {
        if (tasks.size() == 0) {
            setLastMessage("Your List is empty!");
        } else {
            StringBuilder sb = new StringBuilder("Your List:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.getTask(i).getTasking()).append("\n");
            }
            setLastMessage(sb.toString().trim());
        }
    }
}
