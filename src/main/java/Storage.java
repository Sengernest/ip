import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Storage {
    private final Path path;

    public Storage(String relativePath) {
        this.path = Path.of(relativePath);
    }

    public List<task> load() throws IOException {
        ensureFileReady();
        List<task> tasks = new ArrayList<>();

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

    public void save(List<task> tasks) throws IOException {
        ensureDirReady();
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (task t : tasks) {
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

   private task parseLine(String line) {
    String[] parts = line.split(" \\| ");
    if (parts.length < 3) throw new IllegalArgumentException("Too few fields");

    String type = parts[0].trim();
    boolean done = parts[1].trim().equals("1");
    String desc = parts[2].trim();

    task t;

    switch (type) {
        case "T":
            t = new toDo(desc);
            break;
        case "D":
            if (parts.length < 4) throw new IllegalArgumentException("Missing deadline time");
            t = new deadline(desc, parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) throw new IllegalArgumentException("Missing event time");
            t = new event(desc, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
    }

    if (done) {
        t.finish();
    }
    
    return t;
}

}
