/**
 * Storage.java
 * <p>
 * Handles reading from and writing to the file system for task storage.
 *
 * <p><b>AI Assistance Note:</b> This file was written and refined with the help
 * of ChatGPT (OpenAI). AI was used to:
 * <ul>
 *   <li>Generate and polish Javadoc comments</li>
 *   <li>Explain constructor behavior and add assertion checks</li>
 *   <li>Suggest structure for error handling and helper methods</li>
 * </ul>
 * </p>
 *
 * @author Ernest
 */
package sengernest.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sengernest.tasks.Deadline;
import sengernest.tasks.Event;
import sengernest.tasks.Task;
import sengernest.tasks.TaskList;
import sengernest.tasks.ToDo;

/**
 * Handles reading from and writing to the file system for task storage.
 * <p>
 * (AI-assisted: Javadoc and some method-level refinements were produced with ChatGPT)
 * </p>
 */
public class Storage {
    /**
     * Date format used for deadlines in the storage file.
     * (AI-assisted constant naming and docs)
     */
    private static final DateTimeFormatter DEADLINE_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Date format used for events in the storage file.
     * (AI-assisted constant naming and docs)
     */
    private static final DateTimeFormatter EVENT_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * File path for storing tasks.
     */
    private final Path path;

    /**
     * Constructs a {@code Storage} object using the given relative file path.
     * (AI-assisted: Javadoc + assertion reasoning)
     *
     * @param relativePath the relative file path for this storage
     * @throws AssertionError if {@code relativePath} is {@code null} or blank
     */
    public Storage(String relativePath) {
        assert relativePath != null && !relativePath.isBlank() : "File path must not be null or empty";
        this.path = Path.of(relativePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return List of tasks read from the file.
     * @throws IOException If the file cannot be read.
     */
    public ArrayList<Task> load() throws IOException {
        ensureFileReady();
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    Task t = parseLine(line);
                    assert t != null : "Parsed task should never be null";
                    tasks.add(t);
                } catch (IllegalArgumentException ex) {
                    System.out.println("[warn] Skipping corrupted line: " + line);
                }
            }
        }

        return tasks;
    }

    /**
     * Saves the given task list to the storage file.
     *
     * @param tasks Task list to save.
     * @throws IOException If the file cannot be written.
     */
    public void save(TaskList tasks) throws IOException {
        assert tasks != null : "TaskList to save should not be null";
        ensureDirReady();
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Task t : tasks.getTasks()) {
                assert t != null : "Task in TaskList should not be null";
                bw.write(t.toFileFormat());
                bw.newLine();
            }
        }
    }

    /**
     * Ensures that the parent directory exists.
     */
    private void ensureDirReady() throws IOException {
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
        assert parent == null || Files.exists(parent) : "Parent directory should exist after creation";
    }

    /**
     * Ensures that the storage file exists.
     */
    private void ensureFileReady() throws IOException {
        ensureDirReady();
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        assert Files.exists(path) : "Storage file should exist after ensureFileReady()";
    }

    /**
     * Parses a line from the file into a Task.
     *
     * @param line The line to parse.
     * @return Task represented by the line.
     */
    private Task parseLine(String line) {
        assert line != null && !line.isBlank() : "Input line must not be null or blank";
        String[] parts = line.split(" \\| ");
        assert parts.length >= 3 : "Line must have at least 3 parts";

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();
        assert desc != null : "Task description should not be null";

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(desc);
            break;
        case "D":
            assert parts.length >= 4 : "Deadline must have a date/time field";
            LocalDateTime deadlineDate = LocalDateTime.parse(parts[3].trim(), DEADLINE_INPUT);
            task = new Deadline(desc, deadlineDate);
            break;
        case "E":
            assert parts.length >= 5 : "Event must have start and end date/time";
            LocalDateTime start = LocalDateTime.parse(parts[3].trim(), EVENT_INPUT);
            LocalDateTime end = LocalDateTime.parse(parts[4].trim(), EVENT_INPUT);
            task = new Event(desc, start, end);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (done) {
            task.finish();
        }
        return task;
    }
}
