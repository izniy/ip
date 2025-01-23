package bearbot;

import java.util.Scanner;

public class BearBot {
    // This class handles the program logic
    private Ui ui;
    private Scanner scanner;

    public BearBot() {
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
            }
            ui.echo(input);
        }
        ui.showGoodbyeMessage();
    }
    public static void main(String[] args) {
        new BearBot().run();
    }
}
