package app.gui.page;

import java.io.IOException;
import java.util.List;

import app.controller.ListenterController;
import app.gui.partials.RectangleItemController;
import app.model.Album;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeController {

    @FXML
    private HBox mostListenedHBox;
    @FXML
    private HBox randomHBox;
    @FXML
    private HBox lastReleasedHBox;

    @FXML
    private void initialize() {
        loadItems();
    }

    private void loadItems() {

        mostListenedHBox.getChildren().clear();
        randomHBox.getChildren().clear();
        lastReleasedHBox.getChildren().clear();

        // ( last release , most played , random instead of favorite items )
        List<List<Album>> feed = ListenterController.feedAlbums();

        // last released
        for (Album album : feed.get(0)) {
            try {

                // TODO : variable path
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/app/fxml/partials/rectangle-item.fxml"));
                VBox albumVBox = loader.load();
                RectangleItemController controller = loader.getController();
                String albumType = album.getMusics().size() == 1 ? "Single" : "Album";
                controller.setArtist(album.getCover(), album.getName(), album.getDatePublished().getYear() + "",
                        albumType, album.getUserID(), album.getAlbumID());
                lastReleasedHBox.getChildren().add(albumVBox);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // most listened
        for (Album album : feed.get(1)) {
            try {

                // TODO : variable path
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/app/fxml/partials/rectangle-item.fxml"));
                VBox albumVBox = loader.load();
                RectangleItemController controller = loader.getController();
                String albumType = album.getMusics().size() == 1 ? "Single" : "Album";
                controller.setArtist(album.getCover(), album.getName(), album.getDatePublished().getYear() + "",
                        albumType, album.getUserID(), album.getAlbumID());
                mostListenedHBox.getChildren().add(albumVBox);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // random items
        for (Album album : feed.get(2)) {
            try {

                // TODO : variable path
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/app/fxml/partials/rectangle-item.fxml"));
                VBox albumVBox = loader.load();
                RectangleItemController controller = loader.getController();
                String albumType = album.getMusics().size() == 1 ? "Single" : "Album";
                controller.setArtist(album.getCover(), album.getName(), album.getDatePublished().getYear() + "",
                        albumType, album.getUserID(), album.getAlbumID());
                randomHBox.getChildren().add(albumVBox);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
