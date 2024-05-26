package app.gui.page;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

import app.controller.AdminController;
import app.controller.ListenterController;
import app.controller.auth.CurrentData;
import app.gui.base.BodyController;
import app.gui.partials.AudioItemController;
import app.gui.partials.CircleItemController;
import app.model.Artist;
import app.model.Audio;

public class ResultController {

    @FXML
    private Label seemoreAudios;

    @FXML
    private Label seemoreArtists;

    @FXML
    private Label seemoreAlbums;

    @FXML
    private VBox audiosVBox;

    @FXML
    private HBox artistsHBox;

    @FXML
    private HBox albumsHBox;

    @FXML
    private TextField searchInput;

    private final PauseTransition pause = new PauseTransition(Duration.seconds(1));

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            searchInput.requestFocus();
            searchInput.positionCaret(searchInput.getText().length());
        });

        searchInput.setText(CurrentData.getSearch());

        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                backToSearch();
            } else {
                resetPauseTransition(newValue);
            }
        });

        setupPauseTransition();
    }

    private void setupPauseTransition() {
        pause.setOnFinished(event -> {
            String currentSearchText = searchInput.getText();
            Platform.runLater(() -> injectData(currentSearchText));
        });
    }

    private void resetPauseTransition(String newValue) {
        pause.stop();
        pause.playFromStart();
    }

    private void injectData(String query) {
        if (!query.isEmpty()) {
            System.out.println("Search input changed: " + query);

            // we actually show 4 items but the extra item is for
            // disabling or enabling see all label in each category
            List<Audio> audios = ListenterController.searchAudios(query, 5);
            List<Artist> artists = ListenterController.searchArtists(query, 5);

            System.out.println(audios.size());
            System.out.println(artists.size());

            // TODO : show smth like there is no result for each category
            // if there is no item
            loadAudios(audios);
            loadArtists(artists);
        }
    }

    private void backToSearch() {
        SearchController.focusCursor = true;
        BodyController.setFxmlPath(List.of("page/Search"));
    }

    private void loadAudios(List<Audio> audios) {

        int size = audios.size();

        if (size == 5) {
            seemoreAudios.setVisible(true);
            size--;
        }

        audiosVBox.getChildren().clear();

        for (int i = 1; i <= size; i++) {
            Audio audio = audios.get(i - 1);
            Artist artist = AdminController.getArtistByUserID(audio.getUserID());
            try {
                // TODO : variable the path
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/audio-item.fxml"));
                Pane audioPane = loader.load();
                AudioItemController controller = loader.getController();
                controller.setAudioData(audio.getUserID(), audio.getAudioID(), audio.getTitle(), artist.getFullName(),
                        audio.getHumanReadablePlays(), audio.getCover(),
                        i);

                audiosVBox.getChildren().add(audioPane);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    private void loadArtists(List<Artist> artists) {

        int size = artists.size();

        if (size == 5) {
            seemoreArtists.setVisible(true);
            size--;
        }

        artistsHBox.getChildren().clear();

        for (Artist artist : artists) {
            try {
                // TODO : path variables
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/circle-item.fxml"));
                VBox artistVbox = loader.load();
                CircleItemController controller = loader.getController();
                controller.setArtist(artist);
                artistsHBox.getChildren().add(artistVbox);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void handleSeeAllAudios() {
        System.out.println("see all audios clicked!");
    }

    @FXML
    private void handleSeeAllAlbums() {
        System.out.println("see all albums clicked!");
    }

    @FXML
    private void handleSeeAllArtists() {
        System.out.println("see all artists clicked!");
    }
}