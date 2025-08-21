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
        System.out.print("Enter next command:");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        task[] list = new task[100];
        int count = 0;
        list[0] = new task("");

        System.out.println("Hello! I'm Sengernest");
        System.out.print("What can I do for you?:");

        while (scanner.hasNextLine()) {
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
            } else if (command.equals("todo")) {
                list[count] = new toDo(commands[1]);
                count ++;
                System.out.println();
                System.out.println("added to list: " + commands[1]);
                nextCommand();
            } else if (command.equals("deadline")) {
                if (commands.length > 1) {
                    String[] parts = commands[1].split("/by", 2);
                    String description = parts[0].trim();
                    String by = (parts.length > 1) ? parts[1].trim() : "";
                    list[count] = new deadline(description, by);
                    count++;
                    System.out.println();
                    System.out.println("added to list: " + description + " by " + by);
                }
                nextCommand();
            }
            else {
                if (commands.length > 1) {
                    String[] firstSplit = commands[1].split("/from", 2);
                    String description = firstSplit[0].trim();
                    String from = "";
                    String to = "";
                    if (firstSplit.length > 1) {
                        String[] secondSplit = firstSplit[1].split("/to", 2);
                        from = secondSplit[0].trim();
                        if (secondSplit.length > 1) {
                            to = secondSplit[1].trim();
                        }
                    }
                    list[count] = new event(description, from, to);
                    count++;
                    System.out.println();
                    System.out.println("added to list: " + description + " from " + from + " to " + to);
                }
                nextCommand();
            }
        }

        scanner.close();
    }

}
