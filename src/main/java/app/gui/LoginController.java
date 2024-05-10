package app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

import app.util.Variables;

public class LoginController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ImageView bg;
    // It's just testing, should be updated late in Variables
    private Image img_bg = new Image(getClass().getResource(Variables.bgAuthPath).toString());

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorMessage;

    @FXML
    private void initialize() {

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.authCSSPath).toString());
        // =============

        // ============= Adding images
        bg.setImage(img_bg);
        // =============
    }

    @FXML
    public void handleSignupLink(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.signupFXMLPath));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleLoginButtonAction(ActionEvent event) throws IOException {

        if (true) {
            // showing error message
            errorMessage.setVisible(true);

            // changing borders to red!
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
        }
    }
}
