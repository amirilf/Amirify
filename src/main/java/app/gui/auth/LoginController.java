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
import java.util.ArrayList;
import java.util.List;

import app.controller.AdminController;
import app.controller.auth.CurrentData;
import app.controller.auth.CurrentUser;
import app.controller.auth.Login;
import app.exceptions.UserNotFoundException;
import app.exceptions.WrongPasswordException;
import app.gui.base.BodyController;
import app.model.Audio;
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

        // get data from form
        String username = usernameField.getText();
        String password = passwordField.getText();

        // if it's True we know user is already logged in

        try {

            Login.login(username, password);

            System.out.println("Login");

            // we go to the home page since user is already logged in
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.baseFXMLPath));

            Parent root = loader.load();
            Scene scene = new Scene(root);

            // no creating new one since we care about System perfomance 🤡
            Stage stage = (Stage) errorMessage.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Amirify | " + CurrentUser.getUser().getFullName());

            // change default body to Home page
            CurrentData.clearHistory();
            BodyController.setFxmlPath(List.of("page/Home"));

            // TODO : remove these test
            ArrayList<Audio> audios = AdminController.getAudios();
            CurrentData.playlist.addAll(audios);
            CurrentData.setSelectedIndex(0);

            System.out.println(CurrentUser.getUser().getFullName());

        } catch (UserNotFoundException | WrongPasswordException e) {

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
