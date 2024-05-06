package app.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private Label errorMessage;

    @FXML
    public void initialize() {
        myChoiceBox.setValue("Listener");
        myChoiceBox.setItems(FXCollections.observableArrayList("Listener", "Singer", "Podcaster"));
    }

    @FXML
    public void handleLoginLink(MouseEvent event) throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/LoginPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    public void handleSignupButtonAction(ActionEvent event) throws IOException {

        if (true) {
            // showing error message
            errorMessage.setVisible(true);

            // changing borders to red!
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
        }
    }

}