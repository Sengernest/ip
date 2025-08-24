package Sengernest;
import java.util.ArrayList;

import Sengernest.commands.Command;
import Sengernest.parser.Parser;
import Sengernest.storage.Storage;
import Sengernest.tasks.TaskList;
import Sengernest.ui.Ui;

public class Sengernest {
    private static final String FILE_PATH = "data/Sengernest.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.greet(); 
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.newCommand();
                ui.newLine();
                Command command = Parser.parse(fullCommand);
                if (command == null ) {
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
    
    public static void main(String[] args) {
        new Sengernest().run();
    }
}
