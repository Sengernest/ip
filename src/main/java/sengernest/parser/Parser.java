package sengernest.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sengernest.commands.*;
import sengernest.exceptions.*;
import sengernest.tasks.Deadline;
import sengernest.tasks.Event;
import sengernest.tasks.ToDo;

/**
 * Parses user input strings into {@link Command} objects for Sengernest.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a full user command string into a {@link Command} object.
     *
     * @param fullCommand the complete input string from the user
     * @return the corresponding {@link Command} object
     * @throws Exception if the input is invalid or cannot be parsed
     */
    public static Command parse(String fullCommand) throws Exception {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "bye" -> {
            return new ExitCommand();
        }
        case "list" -> {
            return new PrintListCommand();
        }
        case "todo" -> {
            return parseToDo(parts);
        }
        case "deadline" -> {
            return parseDeadline(parts);
        }
        case "event" -> {
            return parseEvent(parts);
        }
        case "mark" -> {
            return parseMark(parts);
        }
        case "unmark" -> {
            return parseUnmark(parts);
        }
        case "delete" -> {
            return parseDelete(parts);
        }
        case "find" -> {
            return parseFind(parts);
        }
        default -> throw new UnknownCommandException(
                "Invalid command! Use: list, todo, deadline, event, mark, unmark, delete, find, bye");
        }
    }

    private static AddTaskCommand parseToDo(String[] parts) throws EmptyTaskDescriptionException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a todo cannot be empty!");
        }
        return new AddTaskCommand(new ToDo(parts[1].trim()));
    }

    private static AddTaskCommand parseDeadline(String[] parts)
            throws EmptyTaskDescriptionException, MissingDateException, InvalidDateFormatException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty!");
        }
        String[] deadlineParts = parts[1].split("/by", 2);
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new MissingDateException("Deadline must have a /by date in yyyy-MM-dd HHmm format!");
        }
        try {
            return new AddTaskCommand(new Deadline(
                    deadlineParts[0].trim(),
                    LocalDateTime.parse(deadlineParts[1].trim(), INPUT_FORMAT)));
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format! Use yyyy-MM-dd HHmm!");
        }
    }

    private static AddTaskCommand parseEvent(String[] parts)
            throws EmptyTaskDescriptionException, MissingDateException, InvalidDateFormatException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of an event cannot be empty!");
        }

        String[] fromSplit = parts[1].split("/from", 2);
        if (fromSplit.length < 2 || fromSplit[1].trim().isEmpty()) {
            throw new MissingDateException("Event must have a /from start date!");
        }

        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2 || toSplit[1].trim().isEmpty()) {
            throw new MissingDateException("Event must have a /to end date!");
        }

        try {
            return new AddTaskCommand(new Event(
                    fromSplit[0].trim(),
                    LocalDateTime.parse(toSplit[0].trim(), INPUT_FORMAT),
                    LocalDateTime.parse(toSplit[1].trim(), INPUT_FORMAT)));
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format! Use yyyy-MM-dd HHmm!");
        }
    }

    private static MarkCommand parseMark(String[] parts) throws MissingTaskNumberException {
        if (parts.length < 2) {
            throw new MissingTaskNumberException("Specify task number to mark!");
        }
        return new MarkCommand(parts[1].trim());
    }

    private static UnmarkCommand parseUnmark(String[] parts) throws MissingTaskNumberException {
        if (parts.length < 2) {
            throw new MissingTaskNumberException("Specify task number to unmark!");
        }
        return new UnmarkCommand(parts[1].trim());
    }

    private static DeleteCommand parseDelete(String[] parts) throws MissingTaskNumberException {
        if (parts.length < 2) {
            throw new MissingTaskNumberException("Specify task number to delete!");
        }
        return new DeleteCommand(parts[1].trim());
    }

    private static sengernest.commands.FindCommand parseFind(String[] parts) throws EmptyTaskDescriptionException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("The find command requires a keyword!");
        }
        return new sengernest.commands.FindCommand(parts[1].trim());
    }
}
