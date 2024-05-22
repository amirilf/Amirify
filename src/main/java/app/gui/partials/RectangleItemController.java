package app.gui.partials;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RectangleItemController {

    private String userID;
    private String listID;

    @FXML
    private ImageView cover;

    @FXML
    private Label title;

    @FXML
    private Label first;

    @FXML
    private Label second;

    // type => Audio Album Episode Podcast or Playlist
    public void setArtist(String coverPath, String titleText, String firstText, String secondText, String userID,
            String listID) {

        this.listID = listID;
        this.userID = userID;

        cover.setImage(new Image(getClass().getResource(coverPath).toString()));

        title.setText(titleText);

        first.setText(firstText);
        second.setText(secondText);

    }

    @FXML
    private void handleBoxClick() {
        // we will open list
        System.out.println("Clicked on box");
    }

    @FXML
    private void handlePlayBoxClick(MouseEvent event) {
        // we will open list and run
        System.out.println("Clicked on play");
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
