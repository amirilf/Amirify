package app.gui.page;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlaylistController {

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
    private ImageView playButton;

}
