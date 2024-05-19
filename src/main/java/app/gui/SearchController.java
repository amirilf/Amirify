package app.gui;

import app.controller.auth.CurrentData;
import app.gui.base.BodyController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchController {

    @FXML
    private TextField searchInput;

    @FXML
    private void handleSearchButtonClick() {
        String searchText = searchInput.getText();

        if (searchText.equals("")) {
            // it's empty
            searchInput.setStyle("-fx-border-color: red;");
            System.out.println("empty");
        } else {
            searchInput.setStyle("-fx-border-color: white;");
            CurrentData.setSearch(searchText);
            BodyController.setFxmlPath("Result");
        }
    }
}