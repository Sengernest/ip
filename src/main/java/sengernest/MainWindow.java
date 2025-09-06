package sengernest;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI window of Sengernest.
 */
public class MainWindow {

    /**
     * Scrollable container for dialog messages.
     */
    @FXML
    private ScrollPane scrollPane;

    /**
     * Vertical container holding all dialog boxes.
     */
    @FXML
    private VBox dialogContainer;

    /**
     * TextField for user input.
     */
    @FXML
    private TextField userInput;

    /**
     * Button to send user input.
     */
    @FXML
    private Button sendButton;

    /**
     * The backend chatbot instance.
     */
    private Sengernest sengernest;

    /**
     * Avatar image for the user.
     */
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/user.jpg"));

    /**
     * Avatar image for the bot.
     */
    private final Image botImage = new Image(getClass().getResourceAsStream("/images/bot.png"));

    /**
     * Initializes the controller after FXML fields are injected.
     * Sets up event handlers for user input and binds scrolling
     * to the dialog container height.
     */
    @FXML
    public void initialize() {
        sengernest = new Sengernest();
        sendButton.setOnAction(e -> handleUserInput());
        userInput.setOnAction(e -> handleUserInput());

        assert dialogContainer != null : "Dialog container should be initialized";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getBotDialog("Hello, I'm Sengernest. What can I do for you today?", botImage)
        );
    }

    /**
     * Handles user input when the send button or Enter key is pressed.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";
        if (input.isBlank()) {
            return;
        }

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        if (input.trim().equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().add(DialogBox.getBotDialog("Goodbye!", botImage));
            userInput.clear();

            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            return;
        }

        String response = sengernest.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getBotDialog(response, botImage));

        userInput.clear();
    }
}
