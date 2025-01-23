package bearbot;

public class BearBot {
    private Ui ui;

    public BearBot() {
        ui = new Ui();
    }

    public void run() {
        ui.showWelcomeMessage();
        ui.showGoodbyeMessage();
    }
    public static void main(String[] args) {
        new BearBot().run();
    }
}
