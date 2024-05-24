package app.gui.partials;

import java.util.List;

import app.gui.base.BodyController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class GenreItemController {

    @FXML
    private Rectangle rectangle;

    @FXML
    private Label genreLabel;

    @FXML
    private ImageView coverImageView;

    public void setGenre(String genreName, String imagePath, String colorId) {
        genreLabel.setText(genreName);
        coverImageView.setImage(new Image(getClass().getResource(imagePath).toString()));
        rectangle.setStyle("-fx-fill: " + colorId + ";");
    }

    @FXML
    private void handleGenreClick(MouseEvent event) {
        BodyController.setFxmlPath(List.of("page/Genre", genreLabel.getText()));
    }
}