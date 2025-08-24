import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sengernest {

    private static final String FILE_PATH = "data/Sengernest.txt";
    private static final Storage storage = new Storage(FILE_PATH);
    private static ArrayList<Task> list = new ArrayList<>();

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static void printList(int size, ArrayList<Task> list) {
        System.out.println("Your List:");
        if (size == 0) System.out.println("Nothing added yet!");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + list.get(i).getTasking());
        }
    }

    public static void nextCommand() {
        System.out.println();
        System.out.print("Enter next command: ");
    }

    public static void main(String[] args) {
        
        try {
            list = new ArrayList<>(storage.load());
        } catch (IOException e) {
            System.out.println("Could not load tasks, starting empty. Reason: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Sengernest");
        System.out.print("What can I do for you?: ");

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
                        return; 

                    case "list":
                        System.out.println();
                        printList(list.size(), list);
                        break;

                    case "todo":
                        if (commands.length < 2 || commands[1].trim().isEmpty())
                            throw new EmptyTaskDescriptionException("The description of a todo cannot be empty!");
                        list.add(new ToDo(commands[1].trim()));
                        storage.save(list);
                        System.out.println();
                        System.out.println("added to list: " + commands[1].trim());
                        printList(list.size(), list);
                        break;

                    case "deadline":
                        if (commands.length < 2 || commands[1].trim().isEmpty())
                            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty!");
                        String[] deadlineParts = commands[1].split("/by", 2);
                        String desc = deadlineParts[0].trim();
                        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty())
                            throw new MissingDateException("Deadline must have a /by date in format yyyy-MM-dd HHmm!");
                        try {
                            LocalDateTime by = LocalDateTime.parse(deadlineParts[1].trim(), INPUT_FORMAT);
                            list.add(new Deadline(desc, by));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2025-08-25 1800)");
                            break;
                        }
                        storage.save(list);
                        System.out.println(); 
                        System.out.println("added to list: " + desc + " by " + deadlineParts[1].trim());
                        printList(list.size(), list);
                        break;

                    case "event":
                        if (commands.length < 2 || commands[1].trim().isEmpty())
                            throw new EmptyTaskDescriptionException("The description of an event cannot be empty!");
                        String[] eventFirstSplit = commands[1].split("/from", 2);
                        String eventDesc = eventFirstSplit[0].trim();
                        if (eventFirstSplit.length < 2 || eventFirstSplit[1].trim().isEmpty())
                            throw new MissingDateException("Event must have a /from start date!");
                        String[] eventSecondSplit = eventFirstSplit[1].split("/to", 2);
                        if (eventSecondSplit.length < 2 || eventSecondSplit[1].trim().isEmpty())
                            throw new MissingDateException("Event must have a /to end date!");
                        try {
                            LocalDateTime from = LocalDateTime.parse(eventSecondSplit[0].trim(), INPUT_FORMAT);
                            LocalDateTime to = LocalDateTime.parse(eventSecondSplit[1].trim(), INPUT_FORMAT);
                            list.add(new Event(eventDesc, from, to));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2025-08-25 1800)");
                            break;
                        }
                        storage.save(list);
                        System.out.println();
                        System.out.println("added to list: " + eventDesc + " from " + eventSecondSplit[0].trim() +
                                " to " + eventSecondSplit[1].trim());
                        printList(list.size(), list);
                        break;
                        
                    case "mark":
                        try {
                            if (list.isEmpty()) throw new EmptyTaskListException("Your list is empty!");
                            if (commands.length < 2 || commands[1].trim().isEmpty())
                                throw new MissingTaskNumberException("Please specify the task number to mark.");
                            int index = Integer.parseInt(commands[1].trim()) - 1;
                            if (index < 0 || index >= list.size()) throw new InvalidTaskNumberException("Invalid task number! Only choose valid task numbers in the list.");
                            if (list.get(index).isFinished()) throw new MarkFinishedTaskException("Task already marked!");
                            list.get(index).finish();
                            storage.save(list);
                            System.out.println();
                            printList(list.size(), list);
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a valid number after 'mark'.");
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(e);
                        }
                        break;
                        
                    case "unmark":
                        try {
                            if (list.isEmpty()) throw new EmptyTaskListException("Your list is empty!");
                            if (commands.length < 2 || commands[1].trim().isEmpty())
                                throw new MissingTaskNumberException("Please specify the task number to unmark.");
                            int index = Integer.parseInt(commands[1].trim()) - 1;
                            if (index < 0 || index >= list.size()) throw new InvalidTaskNumberException("Invalid task number! Only choose valid task numbers in the list.");
                            if (!list.get(index).isFinished()) throw new UnmarkUnfinishedTaskException("Task already unmarked!");
                            list.get(index).unfinish();
                            storage.save(list);
                            System.out.println();
                            printList(list.size(), list);
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a valid number after 'unmark'.");
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(e);
                        }
                        break;
                        
                    case "delete":
                        try {
                            if (list.isEmpty()) throw new EmptyTaskListException("Your list is empty!");
                            if (commands.length < 2 || commands[1].trim().isEmpty())
                                throw new MissingTaskNumberException("Please specify the task number to delete.");
                            int index = Integer.parseInt(commands[1].trim()) - 1;
                            if (index < 0 || index >= list.size()) throw new InvalidTaskNumberException("Invalid task number! Only choose valid task numbers in the list.");
                            System.out.println();
                            System.out.println("deleted from list: " + list.get(index).getTaskDescription());
                            list.remove(index);
                            storage.save(list);
                            System.out.println();
                            printList(list.size(), list);
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a valid number after 'delete'.");
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(e);
                        }
                        break;

                    default:
                        throw new UnknownCommandException("Invalid command! Valid commands: list, todo, deadline, event, mark, unmark, delete, bye.");
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
