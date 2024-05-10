package app.gui;

import java.io.IOException;

import app.util.Variables;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ImageView bg;
    // It's just testing, should be updated late in Variables
    private Image img_bg = new Image(getClass().getResource(Variables.bgAuthPath).toString());

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
    private DatePicker dateField;

    @FXML
    private ChoiceBox<String> typeField;

    @FXML
    private Label errorMessage;

    @FXML
    public void initialize() {
        typeField.setValue("Listener");
        typeField.setItems(FXCollections.observableArrayList("Listener", "Singer", "Podcaster"));

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.authCSSPath).toString());
        // =============

        // ============= Adding images
        bg.setImage(img_bg);
        // =============

    }

    @FXML
    public void handleLoginLink(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.loginFXMLPath));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(scene);
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