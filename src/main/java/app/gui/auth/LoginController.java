package app.gui.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

import app.controller.auth.Login;
import app.util.Variables;

public class LoginController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorMessage;

    @FXML
    private void initialize() {
        // TODO: add css and image addresses in init methods not in fxml files
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
    public void handleLoginButton(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (Login.login(username, password)) {

            System.out.println("Login");

            // we go to the home page since user is already logged in
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.baseFXMLPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) errorMessage.getScene().getWindow();
            stage.setScene(scene);

            // TODO : set title like Amirify | Username or smth like that
            stage.setTitle(username + "  Welcome!");
        } else {
            // showing error message
            errorMessage.setVisible(true);

            // changing borders to red!
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    private void handleBackToAuth(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.authFXMLPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
