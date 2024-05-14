package app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class LibraryController {

    @FXML
    private MenuButton menuButton;

    @FXML
    private void initialize() {

        menuButton.getItems().forEach(item -> item.setOnAction(event -> handleMenuItemAction(item)));
        MenuItem defaultItem = (MenuItem) menuButton.getItems().get(0);
        handleMenuItemAction(defaultItem);
    }

    private void handleMenuItemAction(MenuItem selected) {
        String selectedText = selected.getText();
        menuButton.setText(selectedText);
        System.out.println("Selected item: " + selectedText);
    }
}