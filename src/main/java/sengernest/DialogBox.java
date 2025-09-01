package sengernest;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a single dialog box used in the Sengernest GUI.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor that initializes the dialog box with text and image.
     * Loads the FXML layout and sets the message and image.
     *
     * @param text the message text to display
     * @param img  the avatar image for this dialog box
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            HBox root = fxmlLoader.load();
            this.getChildren().addAll(root.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box horizontally so that the image appears on the right.
     * Used for user messages to distinguish them from bot messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Creates a DialogBox representing a user message.
     * The dialog box is flipped so the image appears on the right.
     *
     * @param text the message text
     * @param img  the user's avatar image
     * @return a DialogBox configured for the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-background-color: #d1ffd1; -fx-padding: 5; -fx-background-radius: 5;");
        return db;
    }

    /**
     * Creates a DialogBox representing a bot message.
     * The image remains on the left.
     *
     * @param text the message text
     * @param img  the bot's avatar image
     * @return a DialogBox configured for the bot
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #e6e6e6; -fx-padding: 5; -fx-background-radius: 5;");
        return db;
    }
}
