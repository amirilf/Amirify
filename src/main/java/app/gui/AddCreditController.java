package app.gui;

import app.gui.base.BodyController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AddCreditController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private void initialize() {
        comboBox.setEditable(true);
        comboBox.getItems().addAll("5$", "10$", "20$", "50$", "100$");
        comboBox.getSelectionModel().selectFirst();

        comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue)
                valueValidation(newValue);
        });

    }

    @FXML
    private void handlePayButton() {
        // checking possible errors if it is ok then back to premium page
        // or we can back to prev page later
        BodyController.setFxmlPath("Premium");
    }

    private void valueValidation(String input) {

        boolean isOK = false;
        if (input.matches("^\\d+(\\.\\d+)?\\$$")) {

            float value = Float.parseFloat(input.substring(0, input.length() - 1));

            if (value >= 5 && value <= 1000)
                isOK = true;

        } else if (input.matches("^\\d+(\\.\\d+)?$")) {

            float value = Float.parseFloat(input);

            if (value >= 5 && value <= 1000)
                isOK = true;
        }

        if (isOK)
            comboBox.setStyle("-fx-border-color: transparent;");
        else
            comboBox.setStyle("-fx-border-color: red;");

    }
}
