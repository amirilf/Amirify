package app.gui.partials;

import app.gui.base.BodyController;
import app.gui.page.AudioController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AudioItemController {

    private String artistID;
    private String audioID;

    @FXML
    private Label labelCounter;

    @FXML
    private ImageView coverImage;

    @FXML
    private Label songTitle;

    @FXML
    private Label songAuthor;

    @FXML
    private Label songDate;

    public void setAudioData(String artistID, String audioID, String title, String author, String duration,
            String coverUrl,
            int number) {

        songTitle.setText(title);
        songAuthor.setText(author);
        songDate.setText(duration);
        coverImage.setImage(new Image(coverUrl));
        labelCounter.setText(String.format("%03d", number));

        this.artistID = artistID;
        this.audioID = audioID;

    }

    @FXML
    private void handlePlayButton() {
        System.out.println("Play clicked " + artistID);
    }

    @FXML
    private void handleAudioClick() {
        AudioController.audioID = audioID;
        BodyController.setFxmlPath("Audio");
    }

    @FXML
    private void handleArtistClick() {
        app.gui.page.ArtistController.artistID = artistID;
        BodyController.setFxmlPath("Artist");
    }

    @FXML
    private void handleOptionsClick() {
        System.out.println("Options btn is clicked!" + artistID);
    }
}
