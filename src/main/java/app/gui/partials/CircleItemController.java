package app.gui.partials;

import app.gui.base.BodyController;
import app.gui.page.ArtistController;
import app.model.Artist;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CircleItemController {

    private String artistID;

    @FXML
    private ImageView cover;

    @FXML
    private Label lbl_name;

    public void setArtist(Artist artist) {
        lbl_name.setText(artist.getFullName());
        cover.setImage(new Image(getClass().getResource(artist.getProfile()).toString()));

        this.artistID = artist.getUserID();
    }

    @FXML
    private void handleArtistClick() {
        ArtistController.artistID = artistID;
        BodyController.setFxmlPath("Artist");
    }

    @FXML
    private void handlePlayArtistClick(MouseEvent event) {
        System.out.println("CLICKER PLAY");
        event.consume();

    }

    @FXML
    private void showPlayMedia(MouseEvent event) {
        if (event.getSource() instanceof VBox) {
            VBox vbox = (VBox) event.getSource();
            ImageView playMediaImageView = findPlayMediaImageView(vbox);
            if (playMediaImageView != null) {
                playMediaImageView.setVisible(true);
            }

        }
    }

    @FXML
    private void hidePlayMedia(MouseEvent event) {
        if (event.getSource() instanceof VBox) {
            VBox vbox = (VBox) event.getSource();
            ImageView playMediaImageView = findPlayMediaImageView(vbox);
            if (playMediaImageView != null) {
                playMediaImageView.setVisible(false);
            }
        }
    }

    private ImageView findPlayMediaImageView(VBox vbox) {
        for (Node node : vbox.getChildren()) {
            if (node instanceof StackPane) {
                StackPane stackPane = (StackPane) node;
                for (Node child : stackPane.getChildren()) {
                    if (child instanceof ImageView && child.getStyleClass().contains("playMediaImage")) {
                        return (ImageView) child;
                    }
                }
            }
        }
        return null;
    }

}
