package app.gui.page;

import app.controller.AdminController;
import app.model.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ArtistController {

    public static String artistID = "";

    @FXML
    private Label lbl;

    @FXML
    private void initialize() {
        System.out.println("ArtistID : " + artistID);
        Artist artist = AdminController.getArtistByUserID(artistID);
        lbl.setText(artist.getFullName());
    }
}
