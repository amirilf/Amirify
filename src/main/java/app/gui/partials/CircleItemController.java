package app.gui.partials;

import app.gui.base.BodyController;
import app.gui.page.ArtistController;
import app.model.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CircleItemController {

    private String artistID;

    @FXML
    private ImageView cover;

    @FXML
    private Label lbl_name;

    public void setArtist(Artist artist) {
        lbl_name.setText(artist.getFullName());

        // TODO: later we should add profile to model
        // cover.setImage(new Image(artist.getProfile()));

        this.artistID = artist.getUserID();
    }

    @FXML
    private void handleArtistClick() {
        ArtistController.artistID = artistID;
        BodyController.setFxmlPath("Artist");
    }
}
