package Sengernest.storage;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Sengernest.tasks.Deadline;
import Sengernest.tasks.Event;
import Sengernest.tasks.Task;
import Sengernest.tasks.TaskList;
import Sengernest.tasks.ToDo;

public class Storage {
    private final Path path;
    private static final DateTimeFormatter DEADLINE_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter EVENT_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String relativePath) {
        this.path = Path.of(relativePath);
    }
    
    public ArrayList<Task> load() throws IOException {
        ensureFileReady();
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                try {
                    tasks.add(parseLine(line));
                } catch (IllegalArgumentException ex) {
                    System.out.println("[warn] Skipping corrupted line: " + line);
                }
            }
        }
        return tasks;
    }
    
    public void save(TaskList tasks) throws IOException {
        ensureDirReady();
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Task t : tasks.getTasks()) {  
                bw.write(t.toFileFormat());
                bw.newLine();
            }
        }
    }

    private void ensureDirReady() throws IOException {
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    private void ensureFileReady() throws IOException {
        ensureDirReady();
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new IllegalArgumentException("Too few fields");

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();

        Task t;
        switch (type) {
            case "T":
                t = new ToDo(desc);
                break;
            case "D":
                if (parts.length < 4) throw new IllegalArgumentException("Missing deadline date/time");
                LocalDateTime deadlineDate = LocalDateTime.parse(parts[3].trim(), DEADLINE_INPUT);
                t = new Deadline(desc, deadlineDate);
                break;
            case "E":
                if (parts.length < 5) throw new IllegalArgumentException("Missing event start/end date/time");
                LocalDateTime start = LocalDateTime.parse(parts[3].trim(), EVENT_INPUT);
                LocalDateTime end = LocalDateTime.parse(parts[4].trim(), EVENT_INPUT);
                t = new Event(desc, start, end);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (done) t.finish();
        
        return t;
    }
}
