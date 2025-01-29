package bearbot;

import java.util.ArrayList;
import java.util.Scanner;
import bearbot.commands.*;
import bearbot.exceptions.*;
import bearbot.tasks.*;

public class BearBot {
    private static final String FILE_PATH = "./data/bearbot.txt"; // Path to store tasks
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Scanner scanner;

    public BearBot() {
        this.storage = new Storage(FILE_PATH); // Storage now knows where to read from/ write to
        this.ui = new Ui();
        this.scanner = new Scanner(System.in);

        TaskList tempTaskList;

        try {
            tempTaskList = new TaskList(storage);  // Load tasks from storage
            // TaskList catches IOException, but there could be other exceptions too
        } catch (Exception e) { // Catches all exception because BearBot needs a valid TaskList no matter what
            System.out.println("Error loading tasks. Starting with an empty list.");
            System.out.println();
            tempTaskList = new TaskList(storage, new ArrayList<>());  // Start fresh if loading fails
        }
        this.taskList = tempTaskList;
    }

    public void run() {
        ui.showWelcomeMessage();

        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            try {
                Command command = Parser.parse(input, taskList);
                command.execute();
            } catch (BearBotException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("UNKNOWN ERROR TO BEARS");
            }
        }

        // Save tasks before exiting
        try {
            storage.save(taskList.getTasks());
            System.out.println("Tasks saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving tasks.");
        }

        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new BearBot().run();
    }
}
