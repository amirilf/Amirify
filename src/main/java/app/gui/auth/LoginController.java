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
import java.time.LocalDate;

import app.controller.auth.CurrentData;
import app.controller.auth.CurrentUser;
import app.controller.auth.Login;
import app.controller.auth.SignUp;
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
        if (Login.login(username, password)) {

            System.out.println("Login");

            // we go to the home page since user is already logged in
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.baseFXMLPath));

            Parent root = loader.load();
            Scene scene = new Scene(root);

            // no creating new one since we care about System perfomance 🤡
            Stage stage = (Stage) errorMessage.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Amirify | " + CurrentUser.getUser().getFullName());

            // TODO : it could be by default Home and not setting every time ):
            // change default body to Home page
            BodyController.setFxmlPath("Home");

            // TODO: remove these test lines later
            SignUp.signUpSinger("adel", "adel1234", "Adel", "Adelian", "adel@email.com", "09966337929",
                    LocalDate.of(1990, 10, 2));
            CurrentUser.logout();
            SignUp.signUpSinger("justin", "justin1234", "Justin", "Bieber", "justin@email.com", "09966337929",
                    LocalDate.of(2010, 10, 2));
            CurrentUser.logout();
            SignUp.signUpSinger("trevor", "trevor1234", "Daniel", "Trevor", "daniel@email.com", "09966337929",
                    LocalDate.of(2000, 10, 2));
            CurrentUser.logout();
            Audio audio1 = new Audio("Hello", "3", null, "/app/media/Adele - Hello.mp3", "/app/media/cover2.png");
            Audio audio2 = new Audio("Love Yourself", "4", null, "/app/media/Love Yourself.mp3",
                    "/app/media/cover3.png");
            Audio audio3 = new Audio("Falling", "5", null, "/app/media/Trevor Daniel - Falling.mp3",
                    "/app/media/cover4.png");

            CurrentData.playlist.add(audio1);
            CurrentData.playlist.add(audio2);
            CurrentData.playlist.add(audio3);
            CurrentData.setSelectedIndex(0);

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
