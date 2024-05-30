package app.gui.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.controller.AdminController;
import app.controller.ListenterController;
import app.controller.SingerController;
import app.controller.UserController;
import app.controller.auth.CurrentData;
import app.controller.auth.CurrentUser;
import app.gui.base.BodyController;
import app.gui.base.BottomBarController;
import app.gui.partials.AudioItemController;
import app.model.Album;
import app.model.Artist;
import app.model.Audio;
import app.model.Listener;
import app.model.Music;
import app.model.Singer;
import app.util.Humanize;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PlaylistController {

    public static boolean play = false;
    public static String artistID;
    public static String listID;
    private static boolean isSaved = false;
    private static ArrayList<Audio> currentAudios;

    @FXML
    private Label type;

    @FXML
    private Label title;

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
    private ImageView saveIcon;

    @FXML
    private ImageView ownerCover;

    @FXML
    private ImageView playButton;

    @FXML
    private VBox contentVBox;

    @FXML
    private VBox detailVBox;

    @FXML
    private HBox bottomDetailHBox;

    @FXML
    private void initialize() {

        artistID = CurrentData.getCurrentPage().get(1);
        listID = CurrentData.getCurrentPage().get(2);

        // liked songs
        if (artistID.equals("-1")) {

            // a convension to understand it's a liked songs playlist!
            // listID is not important
            Listener currentListener = (Listener) CurrentUser.getUser();

            type.setText("Playlist");
            title.setText("Liked Songs");
            datelike.setText(currentListener.getFirstName());
            numberOfAudios.setText(currentListener.getLikedAudios().size() + " songs");

            // TODO : path variable
            cover.setImage(new Image(getClass().getResource("/app/images/liked-songs.png").toString()));

            detailVBox.getChildren().removeFirst();
            bottomDetailHBox.getChildren().remove(0, 6);

            List<Audio> audios = ListenterController.getLikedSongs();
            currentAudios = new ArrayList<>(audios);

            int counter = 1;
            long total_time = 0;

            for (Audio audio : audios) {

                Artist artist = AdminController.getArtistByUserID(audio.getUserID());

                // add musics to the page one by one
                try {
                    // TODO : variable the path
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/audio-item.fxml"));
                    Pane audioPane = loader.load();
                    AudioItemController controller = loader.getController();
                    controller.setAudioData(audio.getUserID(), audio.getAudioID(), audio.getTitle(),
                            artist.getFullName(),
                            audio.getStandardDuration(), audio.getCover(), counter++);

                    contentVBox.getChildren().add(audioPane);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                // add music time to total time
                total_time += audio.getDuration();
            }

            totalTime.setText(Humanize.durationToString(total_time));

            if (play) {
                handlePlayList();
                BottomBarController.playFromOutside();
                play = false;
            }

            return;

        }

        Artist artist = (Artist) UserController.getUser(artistID);

        Listener currentListener = (Listener) CurrentUser.getUser();

        if (artist instanceof Singer) {

            // this is an album from a singer (podcasters don't have albums, only episodes)

            Album album = SingerController.getAlbum(listID, artistID);
            isSaved = currentListener.getSavedAlbums().contains(listID);

            type.setText("Album");
            title.setText(album.getName());
            detailVBox.getChildren().removeFirst();
            owner.setText(artist.getUsername());
            datelike.setText(album.getDatePublished().getYear() + "");

            int albumSize = album.getMusics().size();
            numberOfAudios.setText(albumSize == 1 ? albumSize + " Song" : albumSize + " Songs");

            cover.setImage(new Image(getClass().getResource(album.getCover()).toString()));
            ownerCover.setImage(new Image(getClass().getResource(artist.getProfile()).toString()));
            saveIcon.setImage(new Image(getClass().getResource(saveIconPath()).toString()));

            long total_time = 0;
            int counter = 1;

            currentAudios = new ArrayList<>(album.getMusics());

            for (Music music : album.getMusics()) {

                // add musics to the page one by one
                try {
                    // TODO : variable the path
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/audio-item.fxml"));
                    Pane audioPane = loader.load();
                    AudioItemController controller = loader.getController();
                    controller.setAudioData(music.getUserID(), music.getAudioID(), music.getTitle(),
                            artist.getFullName(),
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

        if (play) {
            handlePlayList();
            BottomBarController.playFromOutside();
            play = false;
        }

    }

    @FXML
    private void handleOwnerClicked() {
        BodyController.setFxmlPath(List.of("page/Artist", artistID));
    }

    @FXML
    private void handlePlayList() {

        if (CurrentData.playlist.equals(currentAudios)) {
            System.out.println("Same list!");
        } else {
            CurrentData.setNewPlaylist(currentAudios, 0);
        }
        BottomBarController.playFromOutside();
    }

    @FXML
    private void handleSavePlaylist() {

        Listener currentListener = (Listener) CurrentUser.getUser();

        if (isSaved)
            currentListener.getSavedAlbums().remove(listID);
        else
            currentListener.getSavedAlbums().add(listID);

        isSaved = !isSaved;

        saveIcon.setImage(new Image(getClass().getResource(saveIconPath()).toString()));

        System.out.println("IS THIS SAVED? :" + isSaved);
    }

    // TODO : path variables
    private String saveIconPath() {
        if (isSaved)
            return "/app/images/icon/icon-check.png";
        return "/app/images/icon/icon-save.png";
    }

}
