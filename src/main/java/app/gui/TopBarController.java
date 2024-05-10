package app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.geometry.Bounds;

public class TopBarController {

    @FXML
    private Label nameLabel;

    @FXML
    private void initialize() {

        // ============= creating contextmenu and its items
        MenuItem profileItem = new MenuItem("Profile");
        MenuItem logoutItem = new MenuItem("Logout");

        profileItem.setOnAction(this::handleProfile);
        logoutItem.setOnAction(this::handleLogout);

        ContextMenu contextMenu = new ContextMenu(profileItem, logoutItem);

        nameLabel.setOnMouseClicked(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            } else {
                Bounds labelBounds = nameLabel.localToScreen(nameLabel.getBoundsInLocal());
                contextMenu.show(nameLabel, (labelBounds.getCenterX()),
                        labelBounds.getCenterY());
            }

        });
        // =============
    }

    private void handleProfile(ActionEvent event) {
        System.out.println("Profile");
    }

    private void handleLogout(ActionEvent event) {
        System.out.println("Logout");
    }
}
