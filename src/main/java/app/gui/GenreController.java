package app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GenreController {

    public static String title = "";

    @FXML
    private Label lbl;

    @FXML
    private void initialize() {
        lbl.setText(title);
    }
}
