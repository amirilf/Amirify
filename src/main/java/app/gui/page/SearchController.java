package app.gui.page;

import app.gui.base.BodyController;
import app.gui.partials.GenreItemController;
import app.model.Genre;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class SearchController {

    public static boolean focusCursor = false;

    @FXML
    private TextField searchInput;

    @FXML
    private VBox contentVBox;

    @FXML
    private void initialize() {

        if (focusCursor) {
            // place cursor in right place
            // only when backing from result to the page!
            Platform.runLater(() -> {
                searchInput.requestFocus();
            });

            focusCursor = false;
        }

        // set listener for searchInput
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> openResultPage(newValue));

        loadGenres();
    }

    private void loadGenres() {
        HBox currentHBox = new HBox();
        addParametersToHBox(currentHBox);

        // add artists and audios to the Hbox (first items)
        addAudiosnArtistsToHBox(currentHBox);

        Genre[] genres = Genre.values();

        for (int i = 0; i < genres.length; i++) {
            Genre genre = genres[i];
            try {

                // TODO : make path variable
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/genre-item.fxml"));
                StackPane genreItem = loader.load();

                GenreItemController controller = loader.getController();
                controller.setGenre(genre.name(), genre.getImagePath(), genre.getColorId());

                currentHBox.getChildren().add(genreItem);

                if ((i + 3) % 5 == 0) {
                    contentVBox.getChildren().add(currentHBox);
                    currentHBox = new HBox();
                    addParametersToHBox(currentHBox);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!currentHBox.getChildren().isEmpty()) {
            contentVBox.getChildren().add(currentHBox);
        }
    }

    private void addParametersToHBox(HBox hBox) {
        hBox.setPrefHeight(170.0);
        hBox.setPrefWidth(925.0);
        hBox.setSpacing(15.0);
        hBox.setPadding(new Insets(0, 0, 0, 10));
    }

    private void addAudiosnArtistsToHBox(HBox hBox) {
        // TODO: make path variables
        try {
            FXMLLoader artistsLoader = new FXMLLoader(getClass().getResource("/app/fxml/partials/genre-item.fxml"));
            FXMLLoader audiosLoader = new FXMLLoader(getClass().getResource("/app/fxml/partials/genre-item.fxml"));

            StackPane artistsItem = artistsLoader.load();
            StackPane audiosItem = audiosLoader.load();

            GenreItemController artistsController = artistsLoader.getController();
            GenreItemController audiosController = audiosLoader.getController();

            artistsController.setGenre("All Artists", "/app/images/genres/Artists.jpg", "#FF1493");
            audiosController.setGenre("All Audios", "/app/images/genres/Audios.jpg", "#1E90FF");

            hBox.getChildren().add(artistsItem);
            hBox.getChildren().add(audiosItem);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openResultPage(String query) {
        BodyController.setFxmlPath(List.of("page/Result", query));
    }
}