package app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        openNewStage("Login");
    }

    @FXML
    private void handleSignupButtonAction(ActionEvent event) {
        openNewStage("Signup");
    }

    private void openNewStage(String title) {

        try {

            // will open LoginPage.fxml and SignupPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/" + title + "Page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(loginButton.getScene().getWindow());
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
