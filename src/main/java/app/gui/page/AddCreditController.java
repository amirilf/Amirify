package app.gui.page;

import app.controller.auth.CurrentUser;
import app.gui.base.BodyController;
import app.model.Listener;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AddCreditController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label errorMessage;

    @FXML
    private Label currentCreditLabel;

    @FXML
    private void initialize() {
        comboBox.setEditable(true);
        comboBox.getItems().addAll("5$", "10$", "20$", "50$", "100$");
        comboBox.getSelectionModel().selectFirst();

        comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue))
                valueValidation(newValue);
        });

        // set credit value
        Listener listener = (Listener) CurrentUser.getUser();
        currentCreditLabel.setText(listener.getCredit() + "$");
    }

    @FXML
    private void handlePayButton() {
        String input = comboBox.getEditor().getText();
        if (valueValidation(input)) {

            float value = Float.parseFloat(input.replace("$", ""));

            Listener listener = (Listener) CurrentUser.getUser();
            listener.setCredit(listener.getCredit() + value);

            // clear error message
            showError("");

            // Navigate back to premium page
            BodyController.setFxmlPath("Premium");

        } else {
            showError("Select or enter a valid value!");
        }
    }

    private boolean valueValidation(String input) {
        boolean isValid = false;
        if (input.matches("^\\d+(\\.\\d+)?\\$$")) {
            float value = Float.parseFloat(input.substring(0, input.length() - 1));
            if (value >= 5 && value <= 1000) {
                isValid = true;
            }
        } else if (input.matches("^\\d+(\\.\\d+)?$")) {
            float value = Float.parseFloat(input);
            if (value >= 5 && value <= 1000) {
                isValid = true;
            }
        }

        if (isValid) {
            comboBox.setStyle("-fx-border-color: transparent;");
        } else {
            comboBox.setStyle("-fx-border-color: red;");
        }

        return isValid;
    }

    private void showError(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(!message.isEmpty());
    }
}
