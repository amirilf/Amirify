package app.gui.page;

import app.controller.AdminController;
import app.model.Audio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AudioController {

    public static String audioID = "";

    @FXML
    private Label lbl;

    @FXML
    private void initialize() {

        Audio audio = AdminController.getAudio(audioID);
        lbl.setText(audio.getTitle());
    }

}
