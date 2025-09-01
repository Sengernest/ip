package sengernest;

import sengernest.commands.Command;
import sengernest.parser.Parser;
import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * The main class for Sengernest.
 */
public class Sengernest {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Sengernest instance.
     */
    public Sengernest() {
        ui = new Ui();
        storage = new Storage("data/Sengernest.txt");
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.displayError("Could not load tasks. Starting empty: " + e.getMessage());
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /**
     * Processes a user input and returns a response as a string from the UI.
     *
     * @param input The user input string.
     * @return The response message from the bot, or an error message if parsing or execution fails.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return ui.getLastMessage();
        } catch (Exception e) {
            return "[Error] " + (e.getMessage() != null ? e.getMessage() : e.toString());
        }
    }
}
