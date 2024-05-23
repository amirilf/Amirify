package app.gui.partials;

import app.gui.base.BodyController;
import app.gui.page.ArtistController;
import app.model.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CircleItemController {

    private String artistID;

    @FXML
    private ImageView cover;

    @FXML
    private ImageView playMediaIcon;

    @FXML
    private Label lbl_name;

    public void setArtist(Artist artist) {
        lbl_name.setText(artist.getFullName());
        cover.setImage(new Image(getClass().getResource(artist.getProfile()).toString()));

        this.artistID = artist.getUserID();
    }

    @FXML
    private void handleArtistClick() {
        ArtistController.artistID = artistID;
        BodyController.setFxmlPath("Artist");
    }

    @FXML
    private void handlePlayArtistClick(MouseEvent event) {
        System.out.println("CLICKER PLAY");
        event.consume();

    }

    @FXML
    private void showPlayMedia(MouseEvent event) {
        playMediaIcon.setVisible(true);
    }

    @FXML
    private void hidePlayMedia(MouseEvent event) {
        playMediaIcon.setVisible(false);
    }

}
