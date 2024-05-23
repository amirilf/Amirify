package app.gui.page;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import app.controller.AdminController;
import app.controller.SingerController;
import app.gui.partials.AudioItemController;
import app.gui.partials.RectangleItemController;
import app.model.Album;
import app.model.Artist;
import app.model.Audio;
import app.model.Singer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ArtistController {

    public static String artistID = "";

    @FXML
    private ImageView bgImage;

    @FXML
    private VBox contentVBox;

    @FXML
    private Label artistName;

    @FXML
    private HBox artistType;

    @FXML
    private HBox verified;

    @FXML
    private Label statistics;

    @FXML
    private Label seemoreAudios;

    @FXML
    private Label seemoreAlbums;

    @FXML
    private VBox audiosVBox;

    @FXML
    private HBox albumsHBox;

    @FXML
    private Pane albumsPane;

    @FXML
    private void initialize() {
        System.out.println("ArtistID : " + artistID);

        Artist artist = AdminController.getArtistByUserID(artistID);
        boolean isSinger = artist instanceof Singer;

        int top = isSinger ? 5 : 4; // 5 for singer 4 for podcaster

        // top in plays
        List<Audio> topAudios = app.controller.ArtistController.getTopAudios(artist, top, 'p');

        // set bg image
        bgImage.setImage(new Image(getClass().getResource(artist.getBackGround()).toString()));

        artistName.setText(artist.getFullName());

        // number of followers
        statistics.setText("" + artist.getFollowers().size());

        // checking verifed label
        if (!artist.isVerified()) {
            artistType.getChildren().remove(verified);
        }

        // set popular items
        int counter = 1;
        for (Audio audio : topAudios) {
            try {
                // TODO : variable the path
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/audio-item.fxml"));
                Pane audioPane = loader.load();
                AudioItemController controller = loader.getController();
                controller.setAudioData(audio.getUserID(), audio.getAudioID(), audio.getTitle(), artist.getFullName(),
                        String.format(Locale.US, "%,d", audio.getPlayedTimes()) + " Plays", audio.getCover(),
                        counter++);

                audiosVBox.getChildren().add(audioPane);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // here we check if it's a singer or podcaster
        if (isSinger) {
            List<Album> albums = SingerController.getAlbums(artistID, 4);

            for (Album album : albums) {
                try {
                    // TODO : variable the path
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/app/fxml/partials/rectangle-item.fxml"));
                    VBox albumVBox = loader.load();
                    RectangleItemController controller = loader.getController();
                    String albumType = album.getMusics().size() == 1 ? "Single" : "Album";
                    controller.setArtist(album.getCover(), album.getName(), album.getDatePublished().getYear() + "",
                            albumType, artistID, album.getAlbumID());
                    albumsHBox.getChildren().add(albumVBox);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        } else {
            contentVBox.getChildren().remove(albumsPane);
        }
    }
}
