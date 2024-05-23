package app.gui.page;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomeController {

    // TODO : remove all these shits
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
