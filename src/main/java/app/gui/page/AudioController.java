package app.gui.page;

import java.io.IOException;
import java.util.List;

import app.controller.AdminController;
import app.controller.SingerController;
import app.controller.auth.CurrentData;
import app.gui.base.BodyController;
import app.gui.partials.AudioItemController;
import app.model.Album;
import app.model.Artist;
import app.model.Audio;
import app.model.Singer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AudioController {

    public static String audioID;
    private static String artistID;
    private static String albumID;

    @FXML
    private VBox contentVBox;

    @FXML
    private VBox detailVBox;

    @FXML
    private Label type;

    @FXML
    private Label title;

    @FXML
    private Label desc;

    @FXML
    private Label owner;

    @FXML
    private Label album;

    @FXML
    private Label date;

    @FXML
    private Label duration;

    @FXML
    private Label plays;

    @FXML
    private ImageView cover;

    @FXML
    private ImageView ownerCover;

    @FXML
    private void initialize() {

        audioID = CurrentData.getCurrentPage().get(1);

        Audio audioObj = AdminController.getAudio(audioID);
        artistID = audioObj.getUserID();

        Artist artistObj = AdminController.getArtistByUserID(artistID);

        if (artistObj instanceof Singer) {

            Singer singer = (Singer) artistObj;
            Album albumObj = SingerController.getAlbumByAudioID(artistID, audioID);
            albumID = albumObj.getAlbumID();

            cover.setImage(new Image(getClass().getResource(audioObj.getCover()).toString()));
            ownerCover.setImage(new Image(getClass().getResource(singer.getProfile()).toString()));

            type.setText("Song");
            title.setText(audioObj.getTitle());

            // remove description since song doesn't have description
            detailVBox.getChildren().remove(desc);

            owner.setText(singer.getUsername());

            album.setText(albumObj.getName());
            date.setText(audioObj.getPublishDate().getYear() + "");
            duration.setText(audioObj.getStandardDuration());
            plays.setText(audioObj.getHumanReadablePlays());

            // add recommendation audios (only 5 items)
            List<Audio> recommends = SingerController.getSimilarAudios(audioObj, 5);

            int counter = 1;

            // inject recommend audios
            for (Audio recommend : recommends) {
                try {
                    // TODO : variable the path
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/app/fxml/partials/audio-item.fxml"));
                    Pane audioPane = loader.load();
                    AudioItemController controller = loader.getController();
                    Artist recommendArtist = AdminController.getArtistByUserID(recommend.getUserID());
                    controller.setAudioData(recommend.getUserID(), recommend.getAudioID(), recommend.getTitle(),
                            recommendArtist.getFullName(), recommend.getHumanReadablePlays(), recommend.getCover(),
                            counter++);

                    contentVBox.getChildren().add(audioPane);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }

        } else {
            // it's a podcast!
            System.out.println("Podcaster");
        }
    }

    @FXML
    private void handleAudioClick() {
        System.out.println("Play current audio clicked!");
    }

    @FXML
    private void handleOwnerClicked() {
        BodyController.setFxmlPath(List.of("page/Artist", artistID));
    }

    @FXML
    private void handleAlbumClicked() {
        BodyController.setFxmlPath(List.of("page/Playlist", artistID, albumID));
    }

}
