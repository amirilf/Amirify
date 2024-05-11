package app.gui;

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

    }

    @FXML
    private void handleHomeClick(MouseEvent event) {
        BodyController.setFxmlPath("Home");
    }

    @FXML
    private void handleSearchClick(MouseEvent event) {
        BodyController.setFxmlPath("Search");
    }

    @FXML
    private void handleLibraryClick(MouseEvent event) {
        BodyController.setFxmlPath("Library");
    }
}
