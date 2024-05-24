package app.gui.page;

import java.util.List;

import app.controller.ListenterController;
import app.controller.auth.CurrentUser;
import app.gui.base.BodyController;
import app.model.Listener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SettingController {

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_followings;

    @FXML
    private ImageView image_profile;

    @FXML
    private Label label_followings;

    @FXML
    private Label lbl_fullName;

    @FXML
    private Label lbl_email;

    @FXML
    private Label lbl_phone;

    @FXML
    private Label lbl_birthDate;

    @FXML
    private Label lbl_username;

    @FXML
    private Label lbl_password;

    @FXML
    private Label lbl_status;

    @FXML
    private Label lbl_genres;

    @FXML
    private void initialize() {

        Listener listener = (Listener) CurrentUser.getUser();

        lbl_title.setText(listener.getFirstName());

        int followings = listener.getFollowings().size();

        if (followings > 0) {
            lbl_followings.setText(listener.getFollowings().size() + " followings");
        } else {
            lbl_followings.setText("No followings yet!");
            lbl_followings.setDisable(true);
        }

        lbl_fullName.setText(listener.getFullName());
        lbl_email.setText(listener.getEmail());
        lbl_phone.setText(listener.getPhone());
        lbl_birthDate.setText(listener.getBirthDate().toString());
        lbl_username.setText("@" + listener.getUsername());
        lbl_password.setText(listener.getPassword());
        lbl_status.setText(ListenterController.getSubscription());
        lbl_genres.setText(listener.getFavoriteGenres().toString());
    }

    @FXML
    void handleFollowingLabelClick() {
        BodyController.setFxmlPath(List.of("page/Following"));
    }
}
