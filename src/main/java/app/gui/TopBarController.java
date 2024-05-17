package app.gui;

import app.util.Variables;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.geometry.Bounds;

public class TopBarController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label nameLabel;

    @FXML
    private void initialize() {

        // ============= creating contextmenu and its items
        MenuItem settingItem = new MenuItem("Setting");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem logoutItem = new MenuItem("Logout");

        settingItem.setOnAction(this::handleSetting);
        aboutItem.setOnAction(this::handleAbout);
        logoutItem.setOnAction(this::handleLogout);

        ContextMenu contextMenu = new ContextMenu(settingItem, aboutItem, logoutItem);

        nameLabel.setOnMouseClicked(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            } else {
                Bounds labelBounds = nameLabel.localToScreen(nameLabel.getBoundsInLocal());
                contextMenu.show(nameLabel, (labelBounds.getCenterX()),
                        labelBounds.getCenterY());
            }

        });

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.topbarCSSPath).toString());
        // =============
    }

    private void handleSetting(ActionEvent event) {
        BodyController.setFxmlPath("Setting");
    }

    private void handleAbout(ActionEvent event) {
        BodyController.setFxmlPath("About");
    }

    private void handleLogout(ActionEvent event) {
        System.out.println("Logout");
    }

    @FXML
    private void handlePremiumClick() {
        BodyController.setFxmlPath("Premium");
    }
}
