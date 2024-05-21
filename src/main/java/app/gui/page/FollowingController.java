package app.gui.page;

import java.io.IOException;
import java.util.ArrayList;

import app.controller.ListenterController;
import app.gui.partials.CircleItemController;
import app.model.Artist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FollowingController {

    @FXML
    private VBox contentVBox;

    @FXML
    private void initialize() {
        loadArtists();
    }

    private void loadArtists() {

        ArrayList<Artist> artists = ListenterController.getFollowings();

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

}
