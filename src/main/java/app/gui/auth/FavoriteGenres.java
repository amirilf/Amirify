package app.gui.auth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.stream.Collectors;

import app.model.Genre;

public class FavoriteGenres {

    private int maxGenreLimit = 10;
    private int minGenreLimit = 3;
    public static ArrayList<String> selectedGenres = new ArrayList<>();

    @FXML
    private TextField searchField;

    @FXML
    private ListView<CheckBox> listView;

    @FXML
    private Button confirmButton;

    @FXML
    private Label errorMessage;

    private ArrayList<String> genreItems = Genre.getGenreNames();
    private ObservableList<CheckBox> allItems = FXCollections
            .observableArrayList(genreItems.stream().map(CheckBox::new).collect(Collectors.toList()));

    @FXML
    private void initialize() {

        // fill the listView
        listView.setItems(allItems);

        // search listener
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterItems(newValue));

        // add max genre selection (for min we only need to check list after submition)
        listView.getItems()
                .forEach(checkBox -> checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        long selectedCount = listView.getItems().stream().filter(CheckBox::isSelected).count();
                        if (selectedCount > maxGenreLimit) {
                            checkBox.setSelected(false);
                        }
                    }
                }));
    }

    // filters genres based on user input (sensitive to the addition of letters)
    private void filterItems(String searchText) {

        if (searchText == null || searchText.isEmpty()) {
            listView.setItems(allItems);
        } else {

            // find matching genres
            ObservableList<CheckBox> filteredItems = FXCollections.observableArrayList();
            for (CheckBox item : allItems) {
                if (item.getText().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredItems.add(item);
                }
            }

            // update ListView based on new matching genres
            listView.setItems(filteredItems);
        }
    }

    @FXML
    private void handleSelectButton() {

        // check if the minimum genre limit is met
        long selectedCount = listView.getItems().stream().filter(CheckBox::isSelected).count();
        if (selectedCount < minGenreLimit) {
            errorMessage.setText("Please select at least " + minGenreLimit + " genres!");
            errorMessage.setVisible(true);
            return;
        }

        // clear the array to avoid counting old items
        selectedGenres.clear();

        // add selected genres to the arraylist
        for (CheckBox item : listView.getItems()) {
            if (item.isSelected()) {
                selectedGenres.add(item.getText());
            }
        }

        // TODO: remove this line
        System.out.println("Selected Items: " + selectedGenres);

        // close modal stage
        Stage stage = (Stage) errorMessage.getScene().getWindow();
        stage.close();
    }
}
