package app.gui.auth;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import app.controller.ListenterController;
import app.controller.auth.CurrentUser;
import app.controller.auth.SignUp;
import app.exceptions.InvalidFormatException;
import app.gui.base.BodyController;
import app.util.Variables;
import app.util.validators.DateValidator;
import app.util.validators.EmailValidator;
import app.util.validators.PasswordValidator;
import app.util.validators.PhoneValidator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SignupController {

    private static String redBorderStyle = "-fx-border-color: red;";
    private static String defaultBorderStyle = "-fx-border-color: transparent;";

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private ChoiceBox<String> typeField;

    @FXML
    private Label errorMessage;

    @FXML
    private void initialize() {
        typeField.setItems(FXCollections.observableArrayList("Listener", "Singer", "Podcaster"));
        typeField.setValue("Listener");
    }

    @FXML
    private void handleLoginLink(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.loginFXMLPath));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    @FXML
    private void handleSignupButton(ActionEvent event) throws IOException {

        // get data from form
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        LocalDate birthDate = birthDateField.getValue();
        String type = typeField.getValue();

        String error = null;

        // === check if fields are not null (type can't be null)
        // else state reason (apply deafult border color) => maybe user submits a null
        // field at first and then fills it and sends it again then the last red border
        // must be gone, so that's the reason 0-0

        // first name
        if (firstName.equals("")) {
            firstNameField.setStyle(redBorderStyle);
            error = "Fill the First name field!";
        } else
            firstNameField.setStyle(defaultBorderStyle);

        // last name
        if (lastName.equals("")) {
            lastNameField.setStyle(redBorderStyle);
            if (error == null)
                error = "Fill the Last name field!";
        } else
            lastNameField.setStyle(defaultBorderStyle);

        // email
        if (email.equals("")) {
            emailField.setStyle(redBorderStyle);
            if (error == null)
                error = "Fill the Email field!";
        } else
            emailField.setStyle(defaultBorderStyle);

        // phone
        if (phone.equals("")) {
            phoneField.setStyle(redBorderStyle);
            if (error == null)
                error = "Fill the Phone field!";
        } else
            phoneField.setStyle(defaultBorderStyle);

        // username
        if (username.equals("")) {
            usernameField.setStyle(redBorderStyle);
            if (error == null)
                error = "Fill the Username field!";
        } else
            usernameField.setStyle(defaultBorderStyle);

        // password
        if (password.equals("")) {
            passwordField.setStyle(redBorderStyle);
            if (error == null)
                error = "Fill the Password field!";
        } else
            passwordField.setStyle(defaultBorderStyle);

        // birthdate
        if (birthDate == null) {
            birthDateField.setStyle(redBorderStyle);
            if (error == null)
                error = "Pick your birth date!";
        } else
            birthDateField.setStyle(defaultBorderStyle);

        if (error != null) {
            // we have null fields
            errorMessage.setText(error);
            errorMessage.setVisible(true);
            return;
        } else {
            errorMessage.setVisible(false);
        }

        // TODO: remove these lines
        // No problem with entered data
        // System.out.println(firstName);
        // System.out.println(lastName);
        // System.out.println(phone);
        // System.out.println(email);
        // System.out.println(username);
        // System.out.println(password);
        // System.out.println(birthDate);
        // System.out.println(type);

        // checking basic validation (not related to DB or smth)

        try {
            EmailValidator.check(email);
        } catch (InvalidFormatException e) {
            errorMessage.setText(e.getMessage());
            errorMessage.setVisible(true);
            emailField.setStyle(redBorderStyle);
            return;
        }

        try {
            PhoneValidator.check(phone);
        } catch (InvalidFormatException e) {
            errorMessage.setText(e.getMessage());
            errorMessage.setVisible(true);
            phoneField.setStyle(redBorderStyle);
            return;
        }

        try {
            PasswordValidator.check(password);
        } catch (InvalidFormatException e) {
            errorMessage.setText(e.getMessage());
            errorMessage.setVisible(true);
            passwordField.setStyle(redBorderStyle);
            return;
        }

        try {
            DateValidator.check(birthDate);
        } catch (InvalidFormatException e) {
            errorMessage.setText(e.getMessage());
            errorMessage.setVisible(true);
            birthDateField.setStyle(redBorderStyle);
            return;
        }

        // check if username exists in DB
        if (!SignUp.validUsername(username)) {
            error = "Username is already taken!";
            errorMessage.setText(error);
            errorMessage.setVisible(true);
            usernameField.setStyle(redBorderStyle);
            return;
        }

        // checking user type
        switch (type) {
            case "Listener":

                // show favorite categories
                showFavoriteGenres();

                if (FavoriteGenres.selectedGenres.size() > 0) {

                    // first signup user
                    SignUp.signUpListener(username, password, firstName, lastName, email, phone, birthDate);
                    System.out.println("Listener is created!");
                    // add its favorite genres list
                    ListenterController.setFavoriteGenres(FavoriteGenres.selectedGenres);
                    System.out.println("Favorite genres are added!");

                } else {
                    // user closed the windows and not added any genre (:
                    error = "Choose your favorite genres to complete registration!";
                    errorMessage.setText(error);
                    errorMessage.setVisible(true);

                    // return to avoid opening Base page since user didn't choose favorite genres
                    return;
                }

                break;

            // TODO : bg and profile are null here!
            case "Singer":
                SignUp.signUpSinger(username, password, firstName, lastName, email, phone, birthDate, "", "");
                System.out.println("Singer is created!");
                break;

            case "Podcaster":
                SignUp.signUpPodcaster(username, password, firstName, lastName, email, phone, birthDate, "", "");
                System.out.println("Podcaster is created!");
                break;
        }

        // we go to the home page since user is already logged in
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.baseFXMLPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) errorMessage.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Amirify | " + CurrentUser.getUser().getFullName());

        // TODO : it could be by default Home and not setting every time ):
        // change default body to Home page
        BodyController.setFxmlPath(List.of("page/Home"));

    }

    private void showFavoriteGenres() throws IOException {
        // TODO: change it to variable from Variables
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/auth/FavoriteGenres.fxml"));
        Parent root = loader.load();
        Stage stg = new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setScene(new Scene(root));
        stg.setResizable(false);
        stg.showAndWait();
    }

}