package bearbot.storage;

import bearbot.tasks.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Warning: No previous data found. Starting fresh.");
            System.out.println();
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromDataString(line));
            }
        }

        if (tasks.isEmpty()) {
            System.out.println("Warning: No previous data found. Starting fresh.");
            System.out.println();
        }

        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toDataString() + System.lineSeparator());
            }
        }
    }
}