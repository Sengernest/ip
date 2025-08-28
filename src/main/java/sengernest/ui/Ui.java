package sengernest.ui;

import java.util.Scanner;

import sengernest.tasks.TaskList;

/**
 * Handles user interaction for Sengernest.
 */
public class Ui {
    /** Scanner for reading user input from the console. */
    private final Scanner scanner = new Scanner(System.in);

    /** Tracks whether this is the first command entered by the user. */
    private boolean firstCommand = true;

    /**
     * Greets the user with a welcome message.
     */
    public void greet() {
        System.out.println("Hello! I'm Sengernest");
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Reads a new command from the user.
     *
     * @return The command string entered by the user.
     */
    public String newCommand() {
        if (firstCommand) {
            System.out.print("What can I do for you?: ");
            firstCommand = false;
        } else {
            System.out.print("Enter next command: ");
        }
        return scanner.nextLine().trim();
    }

    /**
     * Prints the current task list to the console.
     *
     * @param tasks The task list to print.
     */
    public void printList(TaskList tasks) {
        System.out.println("Your List:");
        if (tasks.size() == 0) {
            System.out.println("Nothing added yet!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).getTasking());
        }
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Prints a new line to the console.
     */
    public void newLine() {
        System.out.println();
    }
}
