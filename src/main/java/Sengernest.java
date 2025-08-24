import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sengernest {

private static final String FILE_PATH = "data/Sengernest.txt";
private static Storage storage = new Storage(FILE_PATH);
private static ArrayList<task> list = new ArrayList<>();

    public static void printList(int size, ArrayList<task> list) {
        System.out.println("Your List:");
        if (size == 0) {
            System.out.println("Nothing added yet!");
        }
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + list.get(i).getTasking());
        }
    }

    public static void nextCommand() {
        System.out.println();
        System.out.print("Enter next command:");
    }

    public static void main(String[] args) {

        try {
            list = new ArrayList<>(storage.load());
        } catch (IOException e) {
            System.out.println("[warn] Could not load tasks, starting empty. Reason: " + e.getMessage());
            list = new ArrayList<>();
        }


        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Sengernest");
        System.out.print("What can I do for you?:");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                nextCommand();
                continue;
            }

            String[] commands = input.split(" ", 2);
            String command = commands[0];

            try {
                switch (command) {
                    case "bye":
                        System.out.println();
                        System.out.println("Goodbye, hope to see you again soon!");
                        scanner.close();
                        return;

                    case "list":
                        System.out.println();
                        printList(list.size(), list);
                        break;

                    case "mark":
                        try {
                            if (list.isEmpty()) throw new emptyTaskListException("Your list is empty! Create a toDo, deadline or event task first.");
                            if (commands.length < 2 || commands[1].trim().isEmpty())
                                throw new missingTaskNumberException("Please specify the task number to mark.");

                            int index;
                            try {
                                index = Integer.parseInt(commands[1].trim()) - 1;
                            } catch (NumberFormatException e) {
                                throw new unknownCommandException("Invalid input! Please enter a task number after 'mark'.");
                            }

                            if (index < 0 || index >= list.size()) throw new invalidTaskNumberException("Invalid task number! Choose only existing task numbers in the list.");
                            if (list.get(index).isFinished()) throw new markFinishedTaskException("The task is already marked as done!");
                            list.get(index).finish();
                            try { storage.save(list); } catch (IOException e) { System.out.println("[save error] " + e.getMessage()); }

                            System.out.println();
                            printList(list.size(), list);
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(e);
                        }
                        break;

                    case "unmark":
                        try {
                            if (list.isEmpty()) throw new emptyTaskListException("Your list is empty! Create a toDo, deadline or event task first.");
                            if (commands.length < 2 || commands[1].trim().isEmpty())
                                throw new missingTaskNumberException("Please specify the task number to unmark.");

                            int index;
                            try {
                                index = Integer.parseInt(commands[1].trim()) - 1;
                            } catch (NumberFormatException e) {
                                throw new unknownCommandException("Invalid input! Please enter a task number after 'unmark'.");
                            }
                            if (index < 0 || index >= list.size()) throw new invalidTaskNumberException("Invalid task number! Choose only existing task numbers in the list.");
                            if (!list.get(index).isFinished()) throw new unmarkUnfinishedTaskException("The task is already unmarked as not done!");
                            list.get(index).unfinish();
                            try { storage.save(list); } catch (IOException e) { System.out.println("[save error] " + e.getMessage()); }

                            System.out.println();
                            printList(list.size(), list);
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(e);
                        }
                        break;

                    case "todo":
                        if (commands.length < 2 || commands[1].trim().isEmpty())
                            throw new emptyTaskDescriptionException("The description of a todo cannot be empty! Continue to input the description of the task.");
                        list.add(new toDo(commands[1].trim()));
                        try { storage.save(list); } catch (IOException e) { System.out.println("[save error] " + e.getMessage()); }
                        System.out.println();
                        System.out.println("added to list: " + commands[1].trim());
                        System.out.println();
                        printList(list.size(), list);
                        break;

                    case "deadline":
                        if (commands.length < 2 || commands[1].trim().isEmpty())
                            throw new emptyTaskDescriptionException("The description of a deadline cannot be empty! Continue to input the description of the task.");
                        String[] parts = commands[1].split("/by", 2);
                        String desc = parts[0].trim();
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new missingDateException("The deadline must have a /by date! Continue to input the completion date of the task.");
                        }
                        String by = parts[1].trim();
                        list.add(new deadline(desc, by));
                        try { storage.save(list); } catch (IOException e) { System.out.println("[save error] " + e.getMessage()); }
                        System.out.println();
                        System.out.println("added to list: " + desc + " by " + by);
                        System.out.println();
                        printList(list.size(), list);
                        break;

                    case "event":
                        if (commands.length < 2 || commands[1].trim().isEmpty())
                            throw new emptyTaskDescriptionException("The description of an event cannot be empty! Continue to input the description of the task.");

                        String[] firstSplit = commands[1].split("/from", 2);
                        String eventDesc = firstSplit[0].trim();
                        if (firstSplit.length < 2 || firstSplit[1].trim().isEmpty()) {
                            throw new missingDateException("The event must have a /from start date! Continue to input the start date of the task.");
                        }

                        String[] secondSplit = firstSplit[1].split("/to", 2);
                        if (secondSplit.length < 2 || secondSplit[1].trim().isEmpty()) {
                            throw new missingDateException("The event must have a /to end date! Continue to input the end date of the task.");
                        }

                        String from = secondSplit[0].trim();
                        String to = secondSplit[1].trim();
                        list.add(new event(eventDesc, from, to));
                        try { storage.save(list); } catch (IOException e) { System.out.println("[save error] " + e.getMessage()); }
                        System.out.println();
                        System.out.println("added to list: " + eventDesc + " from " + from + " to " + to);
                        System.out.println();
                        printList(list.size(), list);
                        break;

                    case "delete":
                        try {
                            if (list.isEmpty()) throw new emptyTaskListException("Your list is empty! Create a toDo, deadline or event task first.");
                            if (commands.length < 2 || commands[1].trim().isEmpty())
                                throw new missingTaskNumberException("Please specify the task number to delete.");
                            int index;
                            try {
                                index = Integer.parseInt(commands[1].trim()) - 1;
                            } catch (NumberFormatException e) {
                                throw new unknownCommandException("Invalid input! Please enter a task number after 'delete'.");
                            }
                            if (index < 0 || index >= list.size()) throw new invalidTaskNumberException("Invalid task number! Choose only existing task numbers in the list.");
                            System.out.println();
                            System.out.println("deleted from list: " + list.get(index).getTaskDescription());
                            list.remove(index);
                            try { storage.save(list); } catch (IOException e) { System.out.println("[save error] " + e.getMessage()); }
                            System.out.println();
                            printList(list.size(), list);
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(e);
                        }
                        break;

                    default:
                        throw new unknownCommandException( "Invalid command! Please enter a valid command such as: list, todo, deadline, event, mark, unmark, delete, or bye.");
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println(e);
            }
            nextCommand();
        }

        scanner.close();
    }
}
