package app.gui.page;

import java.util.List;

import app.controller.AdminController;
import app.controller.ListenterController;
import app.controller.auth.CurrentData;
import app.gui.base.BodyController;
import app.model.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ReportController {

    public static String artistID;

    @FXML
    private Label artistName;

    @FXML
    private TextArea reportText;

    @FXML
    private Button submit;

    @FXML
    private void initialize() {

        artistID = CurrentData.getCurrentPage().get(1);

        Artist artist = AdminController.getArtistByUserID(artistID);
        artistName.setText(artist.getFullName());
    }

    @FXML
    private void handleArtistClick() {
        BodyController.setFxmlPath(List.of("page/Artist", artistID));
    }

    @FXML
    private void handleSubmitReport() {
        if (reportText.getText().isEmpty()) {
            reportText.setStyle("-fx-border-color: red;");
        } else {
            ListenterController.addReport(artistID, reportText.getText());
            System.out.println("Report successfuly added!");
            BodyController.setFxmlPath(List.of("page/Artist", artistID));

        }
    }
}
