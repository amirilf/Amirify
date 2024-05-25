package app.gui.partials;

import java.util.List;

import app.gui.base.BodyController;
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
        BodyController.setFxmlPath(List.of("page/Artist", artistID));
    }

    @FXML
    private void handlePlayArtistClick(MouseEvent event) {
        app.gui.page.ArtistController.play = true;
        handleArtistClick();
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
