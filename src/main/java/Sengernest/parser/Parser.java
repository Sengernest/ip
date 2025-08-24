package Sengernest.parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Sengernest.commands.AddTaskCommand;
import Sengernest.commands.Command;
import Sengernest.commands.DeleteCommand;
import Sengernest.commands.ExitCommand;
import Sengernest.commands.MarkCommand;
import Sengernest.commands.PrintListCommand;
import Sengernest.commands.UnmarkCommand;
import Sengernest.exceptions.EmptyTaskDescriptionException;
import Sengernest.exceptions.InvalidDateFormatException;
import Sengernest.exceptions.MissingDateException;
import Sengernest.exceptions.MissingTaskNumberException;
import Sengernest.exceptions.UnknownCommandException;
import Sengernest.tasks.Deadline;
import Sengernest.tasks.Event;
import Sengernest.tasks.ToDo;

public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Command parse(String fullCommand) {
        try {
            String[] parts = fullCommand.split(" ", 2);
            String commandWord = parts[0];

            switch (commandWord) {
                case "bye":
                    return new ExitCommand();

                case "list":
                    return new PrintListCommand();

                case "todo":
                    if (parts.length < 2 || parts[1].trim().isEmpty())
                        throw new EmptyTaskDescriptionException("The description of a todo cannot be empty!");
                    return new AddTaskCommand(new ToDo(parts[1].trim()));

                case "deadline":
                    if (parts.length < 2 || parts[1].trim().isEmpty())
                        throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty!");
                    String[] deadlineParts = parts[1].split("/by", 2);
                    if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty())
                        throw new MissingDateException("Deadline must have a /by date in yyyy-MM-dd HHmm format!");
                    try {
                        return new AddTaskCommand(new Deadline(
                                deadlineParts[0].trim(),
                                LocalDateTime.parse(deadlineParts[1].trim(), INPUT_FORMAT)));
                    } catch (DateTimeParseException e) {
                        throw new InvalidDateFormatException("Invalid date format! Use yyyy-MM-dd HHmm!");
                    }

                case "event":
                    if (parts.length < 2 || parts[1].trim().isEmpty())
                        throw new EmptyTaskDescriptionException("The description of an event cannot be empty!");
                    String[] firstSplit = parts[1].split("/from", 2);
                    if (firstSplit.length < 2 || firstSplit[1].trim().isEmpty())
                        throw new MissingDateException("Event must have a /from start date!");
                    String[] secondSplit = firstSplit[1].split("/to", 2);
                    if (secondSplit.length < 2 || secondSplit[1].trim().isEmpty())
                        throw new MissingDateException("Event must have a /to end date!");
                    try {
                        return new AddTaskCommand(new Event(
                                firstSplit[0].trim(),
                                LocalDateTime.parse(secondSplit[0].trim(), INPUT_FORMAT),
                                LocalDateTime.parse(secondSplit[1].trim(), INPUT_FORMAT)
                        ));
                    } catch (DateTimeParseException e) {
                        throw new InvalidDateFormatException("Invalid date format! Use yyyy-MM-dd HHmm!");
                    }

                case "mark":
                    if (parts.length < 2) throw new MissingTaskNumberException("Specify task number to mark!");
                    return new MarkCommand(parts[1].trim());

                case "unmark":
                    if (parts.length < 2) throw new MissingTaskNumberException("Specify task number to unmark!");
                    return new UnmarkCommand(parts[1].trim());

                case "delete":
                    if (parts.length < 2) throw new MissingTaskNumberException("Specify task number to delete!");
                    return new DeleteCommand(parts[1].trim());

                default:
                    throw new UnknownCommandException("Invalid command! Use valid commands: list, todo, deadline, event, mark, unmark, delete, bye");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
