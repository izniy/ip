package bearbot;

import java.util.Scanner;
import bearbot.commands.*;

public class BearBot {
    // This class handles the program logic
    private static final int MAX_TASKS = 100;
    private String[] tasks;
    private int taskCount;
    private Ui ui;
    private Scanner scanner;

    public BearBot() {
        tasks = new String[MAX_TASKS];
        taskCount = 0;
        ui = new Ui();
        scanner = new Scanner(System.in);
    }

    public void run() {
        ui.showWelcomeMessage();

        String input;
        while (true) { // infinite loop that will run till break
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                new ListCommand(tasks, taskCount).execute();
            } else {
                new AddCommand(tasks, taskCount, input).execute();
                taskCount++;
            }
        }
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new BearBot().run();
    }
}
