import java.util.Scanner;

public class Sengernest {

    public static void printList(int count, task[] list) {
        System.out.println("Your List:");
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + ". "+ list[i].getTasking());
        }
    }

    public static void nextCommand() {
        System.out.println();
        System.out.print("Enter next command: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        task[] list = new task[100];
        int count = 0;
        list[0] = new task("");

        System.out.println("Hello! I'm Sengernest");
        System.out.print("What can I do for you?: ");

        while(true) {
            String[] commands = scanner.nextLine().trim().split(" ", 2);
            String command = commands[0];

            if (command.equals("bye")) {
                System.out.println();
                System.out.println("Goodbye, hope to see you again soon!");
                break;

            } else if (command.equals("list")) {
                System.out.println();
                if (list[0].isEmpty()) {
                    System.out.println("Nothing added yet!");
                } else {
                    printList(count, list);
                }
                nextCommand();

            } else if (command.equals("mark")) {
                list[Integer.parseInt(commands[1]) - 1].finish();
                printList(count, list);
                nextCommand();
            } else if (command.equals("unmark")) {
                list[Integer.parseInt(commands[1]) - 1].unfinish();
                printList(count, list);
                nextCommand();
            } else {
                String commandTask;
                if (commands.length > 1) {
                    commandTask = command + " " + commands[1];
                } else {
                    commandTask = command;
                }
                list[count] = new task(commandTask);
                count += 1;
                System.out.println();
                System.out.println("added to list: " + commandTask);
                nextCommand();
            }
        }

        scanner.close();
    }

}
