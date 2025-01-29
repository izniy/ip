package bearbot.storage;

import bearbot.tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Loads tasks from file
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Warning: No previous data found. Starting fresh.");
            System.out.println();
            return tasks; // No existing data, return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromDataString(line)); // Convert file data back into tasks
            }
        }

        if (tasks.isEmpty()) {
            System.out.println("Warning: No previous data found. Starting fresh.");
            System.out.println();
        }

        return tasks;
    }

    // Saves tasks to file
    public void save(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        // gets parent directory and creates it if missing to ensure directory exists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toDataString() + System.lineSeparator());
            }
            // toDataString() allows each task type to implement write itself in the proper format
        }
    }
}