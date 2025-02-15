package bearbot.tasks;

import bearbot.storage.Storage;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks managed by the user.
 * This class allows adding, removing, marking, and unmarking tasks.
 * It also handles saving and loading tasks using {@code Storage}.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskList with a given storage and an initial list of tasks.
     *
     * @param storage The storage handler for saving and loading tasks.
     * @param tasks   The initial list of tasks to be managed.
     */
    public TaskList(Storage storage, List<Task> tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList by loading tasks from storage.
     * If no previous data is found, initializes an empty task list.
     *
     * @param storage The storage handler for saving and loading tasks.
     */
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

    /**
     * Adds a new task to the task list and saves the updated list to storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    /**
     * Removes a task from the task list by its index and updates storage.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
        saveTasks();
    }

    /**
     * Marks a task as done by its index and updates storage.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
        saveTasks();
    }

    /**
     * Unmarks a task as not done by its index and updates storage.
     *
     * @param index The index of the task to mark as not done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
        saveTasks();
    }

    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();
    }

    /**
     * Saves the current task list to storage.
     * Prints an error message if saving fails.
     */
    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Error: Unable to save tasks!");
            System.out.println();
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The total number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves a specific task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getOneTask(int index) {
        return tasks.get(index);
    }

    /**
     * Clears all tasks from the task list and updates storage.
     */
    public void clearTasks() {
        tasks.clear(); // Remove all tasks
        saveTasks();   // Ensure storage reflects this change
    }
}
