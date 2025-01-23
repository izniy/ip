package bearbot.commands;

public abstract class Command {
    private String description;

    public Command(String description) {
        this.description = description; // allow subclasses to store common data
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute();
}
