package app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class AuthController {

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private AnchorPane rootLayout;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        openPage("Login");
    }

    @FXML
    private void handleSignupButtonAction(ActionEvent event) {
        openPage("Signup");
    }

    private void openPage(String title) {

        try {

            // will open LoginPage.fxml and SignupPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/" + title + "Page.fxml"));
            Parent root = loader.load();

            rootLayout.getChildren().clear();
            rootLayout.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
