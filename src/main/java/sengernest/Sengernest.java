package sengernest;

import java.util.ArrayList;

import sengernest.commands.Command;
import sengernest.parser.Parser;
import sengernest.storage.Storage;
import sengernest.tasks.TaskList;
import sengernest.ui.Ui;

/**
 * Entry point for Sengernest
 */
public class Sengernest {
    /** Path to the file where tasks are stored. */
    private static final String FILE_PATH = "data/Sengernest.txt";

    /** Handles loading and saving tasks to persistent storage. */
    private final Storage storage;

    /** The list of tasks currently in memory. */
    private final TaskList tasks;

    /** Handles user interactions and input/output. */
    private final Ui ui;

    /**
     * Constructs a Sengernest instance.
     */
    public Sengernest() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(new ArrayList<>(storage.load()));
        } catch (Exception e) {
            ui.displayError("[warn] Could not load tasks. Starting empty. Reason: " + e.getMessage());
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /**
     * Runs the main program loop.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.newCommand();
                ui.newLine();
                Command command = Parser.parse(fullCommand);
                if (command == null) {
                    continue;
                }
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.newLine();
            }
        }
        ui.close();
    }
    
    /**
     * The main entry point of the application.
     */
    public static void main(String[] args) {
        new Sengernest().run();
    }
}
