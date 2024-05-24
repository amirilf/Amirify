package app.gui.page;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

import app.controller.AdminController;
import app.controller.auth.CurrentData;
import app.gui.partials.AudioItemController;
import app.gui.partials.CircleItemController;
import app.model.Artist;
import app.model.Audio;
import app.model.Genre;

public class GenreController {

    public static String genreName;

    @FXML
    private Label lbl;

    @FXML
    private VBox contentVBox;

    @FXML
    private void initialize() {

        genreName = CurrentData.getCurrentPage().get(1);

        lbl.setText(genreName);
        checkGenreName();
    }

    private void checkGenreName() {

        if (genreName.equals("All Artists"))
            loadArtists();
        else
            loadAudios();
    }

    private void loadArtists() {

        ArrayList<Artist> artists = AdminController.getArtists();

        if (artists.size() == 0) {
            System.out.println("NO ITEMS");
            return;
        }

        int count = 0;
        HBox hbox = new HBox(40);

        for (Artist artist : artists) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/circle-item.fxml"));
                VBox artistVbox = loader.load();
                CircleItemController controller = loader.getController();
                controller.setArtist(artist);

                hbox.getChildren().add(artistVbox);
                count++;

                if (count % 4 == 0) {
                    contentVBox.getChildren().add(hbox);
                    hbox = new HBox(40);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Add the last HBox if it contains any items
        if (!hbox.getChildren().isEmpty()) {
            contentVBox.getChildren().add(hbox);
        }
    }

    private void loadAudios() {

        ArrayList<Audio> audios;

        if (genreName.equals("All Audios"))
            audios = AdminController.getAudios();
        else
            audios = AdminController.getAudios(Genre.valueOf(genreName));

        if (audios.size() == 0) {
            System.out.println("NO ITEMS");
            return;
        }

        int i = 1;
        for (Audio audio : audios) {

            Artist artist = (Artist) AdminController.getArtistByUserID(audio.getUserID());

            try {
                // TODO: variable path
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/audio-item.fxml"));
                Pane audioPane = loader.load();
                AudioItemController controller = loader.getController();
                controller.setAudioData(artist.getUserID(), audio.getAudioID(), audio.getTitle(), artist.getFullName(),
                        audio.getPublishDate().toString(),
                        audio.getCover(), i++);
                contentVBox.getChildren().add(audioPane);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
