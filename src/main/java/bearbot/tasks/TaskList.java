package bearbot.tasks;

import bearbot.storage.Storage;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;

    public TaskList(Storage storage, List<Task> tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }
    public TaskList(Storage storage) {
        // takes Storage object as input which allows TaskList to load tasks from a file
        this.storage = storage;
        List<Task> loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (IOException e) { // file missing or corrupt (load() throws IOException)
            loadedTasks = new ArrayList<>(); // instead of crashing, recover by using empty task list
            System.out.println("Warning: No previous data found. Starting fresh.");
            System.out.println();
        }
        this.tasks = loadedTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public void removeTask(int index) {
        tasks.remove(index);
        saveTasks();
    }

    public void markTask(int index) {
        tasks.get(index).markAsDone();
        saveTasks();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
        saveTasks();
    }

    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Error: Unable to save tasks!");
            System.out.println();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getOneTask(int index) {
        return tasks.get(index);
    }
}
