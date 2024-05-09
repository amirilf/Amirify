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

    private String signupFxmlPage = "/app/fxml/SignupPage.fxml";
    private String loginFxmlPage = "/app/fxml/LoginPage.fxml";

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
        openPage(loginFxmlPage);
    }

    @FXML
    private void handleSignupButtonAction(ActionEvent event) {
        openPage(signupFxmlPage);
    }

    private void openPage(String path) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            rootLayout.getChildren().clear();
            rootLayout.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
