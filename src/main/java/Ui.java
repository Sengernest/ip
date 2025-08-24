import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private boolean firstCommand = true; // track first input

    public void greet() {
        System.out.println("Hello! I'm Sengernest");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.out.println(message);
    }

    public String newCommand() {
        if (firstCommand) {
            System.out.print("What can I do for you?: ");
            firstCommand = false;
        } else {
            System.out.print("Enter next command: ");
        }
        return scanner.nextLine().trim();
    }

    public void printList(TaskList tasks) {
        System.out.println("Your List:");
        if (tasks.size() == 0) System.out.println("Nothing added yet!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).getTasking());
        }
    }

    public void close() {
        scanner.close();
    }

    public void newLine() {
        System.out.println();
    }
}
