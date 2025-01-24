package bearbot;

import java.util.Scanner;
import bearbot.commands.*;
import bearbot.exceptions.*;
import bearbot.tasks.*;
import java.util.ArrayList;

public class BearBot {
    // This class handles the program logic
    private static final int MAX_TASKS = 100;
    // private String[] tasks;
    private ArrayList<Task> tasks;
    private Ui ui;
    private Scanner scanner;

    public BearBot() {
        // tasks = new String[MAX_TASKS];
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        ui.showWelcomeMessage();

        String input;
        while (true) { // infinite loop that will run till break
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                new ListCommand(this.tasks).execute();
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                new MarkCommand(this.tasks, taskIndex).execute();
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                new UnmarkCommand(this.tasks, taskIndex).execute();
            } else if (input.startsWith("delete")){
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1; // Convert to 0-based index
                    new DeleteCommand(tasks, index).execute();
                } catch (BearBotException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    new AddCommand(this.tasks, input).execute();
                } catch (TaskLimitException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                } catch (BearBotException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("UNKNOWN ERROR");
                }
            }
        }
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new BearBot().run();
    }
}
