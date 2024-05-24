package app.gui.page;

import app.controller.AdminController;
import app.controller.ListenterController;
import app.gui.base.BodyController;
import app.model.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ReportController {

    public static String artistID = "";

    @FXML
    private Label artistName;

    @FXML
    private TextArea reportText;

    @FXML
    private Button submit;

    @FXML
    private void initialize() {
        Artist artist = AdminController.getArtistByUserID(artistID);
        artistName.setText(artist.getFullName());
    }

    @FXML
    private void handleArtistClick() {
        ArtistController.artistID = artistID;
        BodyController.setFxmlPath("Artist");
    }

    @FXML
    private void handleSubmitReport() {
        if (reportText.getText().isEmpty()) {
            reportText.setStyle("-fx-border-color: red;");
        } else {
            ListenterController.addReport(artistID, reportText.getText());
            System.out.println("Report successfuly added!");
            BodyController.setFxmlPath("Artist");
        }
    }
}
