package app.gui.page;

import java.io.IOException;

import app.controller.SingerController;
import app.controller.UserController;
import app.gui.base.BodyController;
import app.gui.partials.AudioItemController;
import app.model.Album;
import app.model.Artist;
import app.model.Music;
import app.model.Singer;
import app.util.Humanize;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PlaylistController {

    public static boolean play = false;
    public static String userID;
    public static String listID;

    @FXML
    private Label type;

    @FXML
    private Label title;

    @FXML
    private Label desc;

    @FXML
    private Label owner;

    // in albums it's date and in playlists it's like
    @FXML
    private Label datelike;

    @FXML
    private Label numberOfAudios;

    @FXML
    private Label totalTime;

    @FXML
    private ImageView cover;

    @FXML
    private ImageView ownerCover;

    @FXML
    private ImageView playButton;

    @FXML
    private VBox contentVBox;

    @FXML
    private VBox detailVBox;

    @FXML
    private void initialize() {
        Artist user = (Artist) UserController.getUser(userID);

        if (user instanceof Singer) {
            // this is an album from a singer (podcasters don't have albums, only episodes)

            Album album = SingerController.getAlbum(listID, userID);

            type.setText("Album");
            title.setText(album.getName());
            detailVBox.getChildren().remove(desc);
            owner.setText(user.getUsername());
            datelike.setText(album.getDatePublished().getYear() + "");

            int albumSize = album.getMusics().size();
            numberOfAudios.setText(albumSize == 1 ? albumSize + " Song" : albumSize + " Songs");

            cover.setImage(new Image(getClass().getResource(album.getCover()).toString()));
            ownerCover.setImage(new Image(getClass().getResource(user.getProfile()).toString()));

            long total_time = 0;
            int counter = 1;

            for (Music music : album.getMusics()) {

                // add musics to the page one by one
                try {
                    // TODO : variable the path
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/audio-item.fxml"));
                    Pane audioPane = loader.load();
                    AudioItemController controller = loader.getController();
                    controller.setAudioData(music.getUserID(), music.getAudioID(), music.getTitle(), user.getFullName(),
                            music.getStandardDuration(), music.getCover(), counter++);

                    contentVBox.getChildren().add(audioPane);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                // add music time to total time
                total_time += music.getDuration();
            }

            totalTime.setText(Humanize.durationToString(total_time));

        } else {
            // this is a playlist from a user
            System.out.println("THIS IS A PLAYLIST!");
        }

    }

    @FXML
    private void handleOwnerClicked() {
        ArtistController.artistID = userID;
        BodyController.setFxmlPath("Artist");
    }

}
