package app.gui;

import java.io.IOException;

import app.controller.auth.CurrentUser;
import app.util.Variables;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

        // TODO : remove this
        System.out.println("Logout");

        CurrentUser.logout();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.loginFXMLPath));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handlePremiumClick() {
        BodyController.setFxmlPath("Premium");
    }
}
