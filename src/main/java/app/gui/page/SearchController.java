package app.gui.page;

import app.controller.auth.CurrentData;
import app.gui.base.BodyController;
import app.gui.partials.GenreItemController;
import app.model.Genre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SearchController {

    @FXML
    private TextField searchInput;

    @FXML
    private VBox contentVBox;

    @FXML
    private void initialize() {
        loadGenres();
    }

    private void loadGenres() {
        HBox currentHBox = new HBox();
        addParametersToHBox(currentHBox);

        Genre[] genres = Genre.values();

        for (int i = 0; i < genres.length; i++) {
            Genre genre = genres[i];
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/genre-item.fxml"));
                StackPane genreItem = loader.load();

                GenreItemController controller = loader.getController();
                controller.setGenre(genre.name(), genre.getImagePath(), genre.getColorId());

                currentHBox.getChildren().add(genreItem);

                if ((i + 1) % 5 == 0) {
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

    @FXML
    private void handleSearchButtonClick() {
        String searchText = searchInput.getText();

        if (searchText.equals("")) {
            // it's empty
            searchInput.setStyle("-fx-border-color: red;");
            System.out.println("empty");
        } else {
            searchInput.setStyle("-fx-border-color: white;");
            CurrentData.setSearch(searchText);
            BodyController.setFxmlPath("Result");
        }
    }
}