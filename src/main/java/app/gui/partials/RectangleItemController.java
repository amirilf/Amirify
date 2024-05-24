package app.gui.partials;

import java.util.List;

import app.gui.base.BodyController;
import app.gui.page.PlaylistController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RectangleItemController {

    private String userID;
    private String listID;

    @FXML
    private ImageView cover;

    @FXML
    private ImageView playMediaIcon;

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
        BodyController.setFxmlPath(List.of("page/Playlist", userID, listID));
    }

    @FXML
    private void handlePlayBoxClick(MouseEvent event) {
        PlaylistController.play = true;
        System.out.println("Play clicked!!");
    }

    @FXML
    private void showPlayMedia(MouseEvent event) {
        playMediaIcon.setVisible(true);
    }

    @FXML
    private void hidePlayMedia(MouseEvent event) {
        playMediaIcon.setVisible(false);
    }

}
