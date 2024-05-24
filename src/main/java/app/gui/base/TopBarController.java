package app.gui.base;

import java.io.IOException;
import java.util.List;

import app.controller.auth.CurrentData;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Bounds;

public class TopBarController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Text nextPage;

    @FXML
    private Text prevPage;

    @FXML
    private void initialize() {

        // add listener for next and prev page buttons
        CurrentData.pageIndex.addListener((observable, oldValue, newValue) -> {
            checkPrevNextStatus();
        });

        // ============= creating contextmenu and its items
        MenuItem settingItem = new MenuItem("Setting");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem logoutItem = new MenuItem("Logout");

        settingItem.setOnAction(this::handleSetting);
        aboutItem.setOnAction(this::handleAbout);
        logoutItem.setOnAction(this::handleLogout);

        ContextMenu contextMenu = new ContextMenu(settingItem, aboutItem, logoutItem);

        // TODO : create a method for this and seprate it
        nameLabel.setOnMouseClicked(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            } else {
                Bounds labelBounds = nameLabel.localToScreen(nameLabel.getBoundsInLocal());
                contextMenu.show(nameLabel, (labelBounds.getCenterX()),
                        labelBounds.getCenterY());
            }

        });

        // set full name in topbar (we know user is already logged in!)
        nameLabel.setText(CurrentUser.getUser().getFullName());

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.topbarCSSPath).toString());
        // =============
    }

    private void handleSetting(ActionEvent event) {
        BodyController.setFxmlPath(List.of("page/Setting"));
    }

    private void handleAbout(ActionEvent event) {
        BodyController.setFxmlPath(List.of("page/About"));
    }

    private void handleLogout(ActionEvent event) {

        // TODO : remove this
        System.out.println("Logout");

        CurrentUser.logout();

        // also clear the playlist! and stop playing audio (if it was playing)
        CurrentData.clearPlaylist();

        // make the body path null bcs if not, after loggin out and again loggin in, if
        // the last path was Home since we set Home again after loggin in, then there is
        // no change in FXMLPath and it doesn't notice changes so... it will show
        // default body 0-0
        BodyController.setFxmlPath(null);

        // opening Login page after loggin out
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
        BodyController.setFxmlPath(List.of("page/Premium"));
    }

    @FXML
    private void handleNextPage() {
        handleNewPage(CurrentData.getNextHistory());
    }

    @FXML
    private void handlePrevPage() {
        handleNewPage(CurrentData.getPrevHistory());
    }

    private void handleNewPage(List<String> page) {

        /*
         * Pages that have meta data
         * 1) Artist -> artistID
         * 2) Audio -> audioID
         * 3) Report -> artistID
         * 4) Playlist -> artistID - albumID (listenerID - playlistID)
         * 5) Genre -> genreName
         * 6) {add later}
         */

        // make sure it will recognize new path update
        BodyController.setFxmlPath(null);

        String pageName = page.get(0);
        switch (pageName) {
            case "page/Artist":
                BodyController.setFxmlPath(List.of(pageName, page.get(1)), false);
                break;
            case "page/Audio":
                BodyController.setFxmlPath(List.of(pageName, page.get(1)), false);
                break;
            case "page/Report":
                BodyController.setFxmlPath(List.of(pageName, page.get(1)), false);
                break;
            case "page/Playlist":
                // first userID and then listID
                BodyController.setFxmlPath(List.of(pageName, page.get(1), page.get(2)), false);
                break;
            case "page/Genre":
                BodyController.setFxmlPath(List.of(pageName, page.get(1)), false);
                break;
            default:
                // other pages which don't have meta data
                BodyController.setFxmlPath(List.of(pageName), false);
                break;
        }
    }

    public void checkPrevNextStatus() {
        nextPage.setDisable(!CurrentData.isNextPage());
        prevPage.setDisable(!CurrentData.isPrevPage());
    }

}
