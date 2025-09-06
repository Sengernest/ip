package sengernest.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sengernest.commands.AddTaskCommand;
import sengernest.commands.Command;
import sengernest.commands.DeleteCommand;
import sengernest.commands.ExitCommand;
import sengernest.commands.MarkCommand;
import sengernest.commands.PrintListCommand;
import sengernest.commands.UnmarkCommand;
import sengernest.exceptions.EmptyTaskDescriptionException;
import sengernest.exceptions.InvalidDateFormatException;
import sengernest.exceptions.MissingDateException;
import sengernest.exceptions.MissingTaskNumberException;
import sengernest.exceptions.UnknownCommandException;
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
        assert fullCommand != null && !fullCommand.isBlank() : "Input command should not be null or blank";
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new PrintListCommand();
        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new EmptyTaskDescriptionException("The description of a todo cannot be empty!");
            }
            return new AddTaskCommand(new ToDo(parts[1].trim()));
        case "deadline":
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
        case "event":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new EmptyTaskDescriptionException("The description of an event cannot be empty!");
            }
            String[] firstSplit = parts[1].split("/from", 2);
            if (firstSplit.length < 2 || firstSplit[1].trim().isEmpty()) {
                throw new MissingDateException("Event must have a /from start date!");
            }
            String[] secondSplit = firstSplit[1].split("/to", 2);
            if (secondSplit.length < 2 || secondSplit[1].trim().isEmpty()) {
                throw new MissingDateException("Event must have a /to end date!");
            }
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
            if (parts.length < 2) {
                throw new MissingTaskNumberException("Specify task number to mark!");
            }
            return new MarkCommand(parts[1].trim());
        case "unmark":
            if (parts.length < 2) {
                throw new MissingTaskNumberException("Specify task number to unmark!");
            }
            return new UnmarkCommand(parts[1].trim());
        case "delete":
            if (parts.length < 2) {
                throw new MissingTaskNumberException("Specify task number to delete!");
            }
            return new DeleteCommand(parts[1].trim());
        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new EmptyTaskDescriptionException("The find command requires a keyword!");
            }
            return new sengernest.commands.FindCommand(parts[1].trim());
        default:
            throw new UnknownCommandException("Invalid command! "
                    + "Use: list, todo, deadline, event, mark, unmark, delete, find, bye");
        }
    }
}
