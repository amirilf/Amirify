package app.gui.page;

import app.controller.auth.CurrentData;
import app.gui.base.BodyController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ResultController {

    @FXML
    private TextField searchInput;

    @FXML
    private void initialize() {
        searchInput.setText(CurrentData.getSearch());
    }

    // TODO : remove these shits
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

    @FXML
    private void handleSearchButtonClick() {
        String searchText = searchInput.getText();

        if (!searchText.equals(CurrentData.getSearch())) {
            if (searchText.equals("")) {
                // it's empty
                BodyController.setFxmlPath("Search");
            } else {
                searchInput.setStyle("-fx-border-color: white;");
                CurrentData.setSearch(searchText);
                searchInput.setText(searchText);

                System.out.println("Search is changed");
            }

        }

    }

}
