package app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class PremiumController {

    @FXML
    private MenuButton menuButton;

    @FXML
    private void initialize() {
        menuButton.getItems().forEach(item -> item.setOnAction(event -> handleMenuItemAction(item)));
    }

    @FXML
    private void handleAddCreditButton() {
        BodyController.setFxmlPath("AddCredit");
    }

    @FXML
    private void handleBuyButton() {
        System.out.println("button buy is clicked");
    }

    private void handleMenuItemAction(MenuItem selected) {
        String selectedText = selected.getText();
        menuButton.setText(selectedText);
        System.out.println("Selected item: " + selectedText);
    }

}
