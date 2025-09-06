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
 */
public class Storage {
    private static final DateTimeFormatter DEADLINE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter EVENT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private final Path path;

    public Storage(String relativePath) {
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
                if (!line.isEmpty()) {
                    try {
                        tasks.add(parseLine(line));
                    } catch (IllegalArgumentException ex) {
                        System.out.println("[warn] Skipping corrupted line: " + line);
                    }
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
        ensureDirReady();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
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
    }

    /**
     * Ensures that the storage file exists.
     */
    private void ensureFileReady() throws IOException {
        ensureDirReady();
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    /**
     * Parses a line from the file into a Task.
     *
     * @param line The line to parse.
     * @return Task represented by the line.
     */
    private Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Too few fields");
        }

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(desc);
            break;
        case "D":
            task = parseDeadline(parts, desc);
            break;
        case "E":
            task = parseEvent(parts, desc);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (done) {
            task.finish();
        }

        return task;
    }

    /**
     * Parses a Deadline from the storage line.
     */
    private Deadline parseDeadline(String[] parts, String desc) {
        if (parts.length < 4) {
            throw new IllegalArgumentException("Missing deadline date/time");
        }
        LocalDateTime by = LocalDateTime.parse(parts[3].trim(), DEADLINE_FORMAT);
        return new Deadline(desc, by);
    }

    /**
     * Parses an Event from the storage line.
     */
    private Event parseEvent(String[] parts, String desc) {
        if (parts.length < 5) {
            throw new IllegalArgumentException("Missing event start/end date/time");
        }
        LocalDateTime start = LocalDateTime.parse(parts[3].trim(), EVENT_FORMAT);
        LocalDateTime end = LocalDateTime.parse(parts[4].trim(), EVENT_FORMAT);
        return new Event(desc, start, end);
    }
}
