package bearbot.ui;

import bearbot.BearBot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BearBot bearBot;

    private Image mochaImage = new Image(this.getClass().getResourceAsStream("/images/mocha.png"));
    private Image milkImage = new Image(this.getClass().getResourceAsStream("/images/milk.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.setStyle("-fx-background-image: url('/images/chat-bg.png');"
                + "-fx-background-size: cover;");
    }

    /**
     * Injects the Duke instance
     */
    public void setBearBot(BearBot b) {
        bearBot = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bearBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, mochaImage),
                DialogBox.getBearBotDialog(response, milkImage)
        );
        // If "bye" was entered, close GUI after displaying the response
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit(); // Gracefully closes JavaFX application
        }
        userInput.clear();
    }
}
