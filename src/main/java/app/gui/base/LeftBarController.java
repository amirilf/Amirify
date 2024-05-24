package app.gui.base;

import java.util.List;

import app.util.Variables;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LeftBarController {

    @FXML
    private ImageView home;
    private Image img_home = new Image(getClass().getResource(Variables.homeIconPath).toString());

    @FXML
    private ImageView search;
    private Image img_search = new Image(getClass().getResource(Variables.searchIconPath).toString());

    @FXML
    private ImageView library;
    private Image img_library = new Image(getClass().getResource(Variables.libraryIconPath).toString());

    @FXML
    private ImageView copyleft;
    private Image img_copyleft = new Image(getClass().getResource(Variables.copyleftIconPath).toString());

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label home_label;

    @FXML
    private Label search_label;

    @FXML
    private Label library_label;

    @FXML
    private void initialize() {

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.leftbarCSSPath).toString());
        // =============

        // ============= Adding images
        home.setImage(img_home);
        search.setImage(img_search);
        library.setImage(img_library);
        copyleft.setImage(img_copyleft);
        // =============

        BodyController.getContentPath().addListener((observable, oldValue, newValue) -> tabStyles(newValue));

    }

    private void tabStyles(String newValue) {

        // this method changes the color of tab items [Home,Search,Library] based on the
        // body page like when user is in Home page the Home label style must be changed

        // reset last styles
        home_label.setStyle("");
        search_label.setStyle("");
        library_label.setStyle("");

        // TODO : need to add sub pages like when user is in category page it's also a
        // TODO : part of search page so again search page should have the style
        // set style if the main page is related to them
        switch (newValue) {
            case "Home":
                home_label.setStyle("-fx-text-fill: #c8c5c5;"); // Example style
                break;
            case "Search":
                search_label.setStyle("-fx-text-fill: #c8c5c5;"); // Example style
                break;
            case "Library":
                library_label.setStyle("-fx-text-fill: #c8c5c5;"); // Example style
                break;
        }
    }

    @FXML
    private void handleHomeClick(MouseEvent event) {
        BodyController.setFxmlPath(List.of("page/Home"));
    }

    @FXML
    private void handleSearchClick(MouseEvent event) {
        BodyController.setFxmlPath(List.of("page/Search"));
    }

    @FXML
    private void handleLibraryClick(MouseEvent event) {
        BodyController.setFxmlPath(List.of("page/Library"));
    }
}
