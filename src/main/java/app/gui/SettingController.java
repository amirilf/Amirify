package app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SettingController {

    @FXML
    private Label following_lbl;

    @FXML
    private void initialize() {

    }

    @FXML
    void handleFollowingLabelClick() {
        BodyController.setFxmlPath("Following");
    }
}
