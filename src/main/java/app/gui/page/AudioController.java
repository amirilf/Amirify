package app.gui.page;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AudioController {

    public static String name = "";

    @FXML
    private Label lbl;

    @FXML
    private void initialize() {
        lbl.setText(name);
    }

}
