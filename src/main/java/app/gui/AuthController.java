package app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import app.util.Variables;

public class AuthController {

    @FXML
    private ImageView bg;
    // It's just testing, should be updated late in Variables
    private Image img_bg = new Image(getClass().getResource(Variables.bgAuthPath).toString());

    @FXML
    private ImageView logo;
    private Image img_logo = new Image(getClass().getResource(Variables.logoPath).toString());

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private void initialize() {

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.authCSSPath).toString());
        // =============

        // ============= Adding images
        bg.setImage(img_bg);
        logo.setImage(img_logo);
        // =============

    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        openPage(Variables.loginFXMLPath);
    }

    @FXML
    private void handleSignupButtonAction(ActionEvent event) {
        openPage(Variables.signupFXMLPath);
    }

    private void openPage(String path) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            mainAnchorPane.getChildren().clear();
            mainAnchorPane.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
