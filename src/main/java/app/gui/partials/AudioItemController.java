package app.gui.partials;

import java.util.ArrayList;
import java.util.List;

import app.controller.AdminController;
import app.controller.auth.CurrentData;
import app.gui.base.BodyController;
import app.gui.base.BottomBarController;
import app.gui.page.ArtistController;
import app.model.Audio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AudioItemController {

    private String artistID;
    private String audioID;

    @FXML
    private Label labelCounter;

    @FXML
    private ImageView coverImage;

    @FXML
    private ImageView playMediaIcon;

    @FXML
    private Label songTitle;

    @FXML
    private Label songAuthor;

    @FXML
    private Label songData;

    public void setAudioData(String artistID, String audioID, String title, String author, String metaData,
            String coverUrl,
            int number) {

        songTitle.setText(title);
        songAuthor.setText(author);
        songData.setText(metaData);
        coverImage.setImage(new Image(coverUrl));
        labelCounter.setText("" + number);

        this.artistID = artistID;
        this.audioID = audioID;

    }

    @FXML
    private void handlePlayButton() {
        Audio audio = AdminController.getAudio(audioID);
        CurrentData.setNewPlaylist(new ArrayList<>(List.of(audio)), 0);
        BottomBarController.playFromOutside();
    }

    @FXML
    private void handleAudioClick() {

        // set fxml path to "" to be able to reload the page
        // cuz it will not change since the last path was also "Audio")
        if (BodyController.getContentPath().get().equals("page/Audio")) {
            BodyController.setFxmlPath(null);
        }

        BodyController.setFxmlPath(List.of("page/Audio", audioID));
    }

    @FXML
    private void handleArtistClick() {

        // 🗿 (شاید برادر خودش صفحه ای است که میخواهیم به ان برویم)
        if (!(BodyController.getContentPath().get().equals("Artist") && ArtistController.artistID.equals(artistID))) {
            BodyController.setFxmlPath(List.of("page/Artist", artistID));
        }
    }

    @FXML
    private void handleOptionsClick() {
        System.out.println("Options btn is clicked!" + artistID);
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
