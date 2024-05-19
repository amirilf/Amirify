package app.gui;

import app.controller.ListenterController;
import app.controller.auth.CurrentUser;
import app.gui.base.BodyController;
import app.model.Listener;
import app.model.PremiumPackage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;

public class PremiumController {

    @FXML
    private Label lbl_status;

    @FXML
    private Label lbl_credit;

    @FXML
    private Label errorMessage;

    @FXML
    private MenuButton menuButton;

    @FXML
    private void initialize() {

        for (String description : PremiumPackage.getPackageNames()) {
            MenuItem item = new MenuItem(description);
            item.setOnAction(event -> handleMenuItemAction(item));
            menuButton.getItems().add(item);
        }

        Listener listener = (Listener) CurrentUser.getUser();
        // TODO: remove increament by one when getting status of premium
        lbl_status.setText(ListenterController.getSubscription());
        lbl_credit.setText(listener.getCredit() + "$");
    }

    @FXML
    private void handleAddCreditButton() {
        BodyController.setFxmlPath("AddCredit");
    }

    @FXML
    private void handleBuyButton() {

        // get choosed plan
        String selectedItem = menuButton.getText();

        // check if a plan is selected
        if ("Choose item".equals(selectedItem)) {
            showError("First choose an item!");
            return;
        }

        Listener listener = (Listener) CurrentUser.getUser();
        PremiumPackage selectedPackage = PremiumPackage.getPackageByName(selectedItem);

        if (selectedPackage != null) {
            double packageValue = selectedPackage.getValue();
            if (listener.getCredit() >= packageValue) {
                // user has enough money to buy it 0_0
                ListenterController.getPremium(selectedPackage);
                lbl_status.setText(ListenterController.getSubscription());
                lbl_credit.setText(listener.getCredit() + "$");
                showError("");
            } else {
                showError("Insufficient credit to buy this package!");
            }
        } else {
            System.out.println("Smth is wrong... package not found.");
        }
    }

    private void handleMenuItemAction(MenuItem selected) {
        String selectedText = selected.getText();
        menuButton.setText(selectedText);
    }

    private void showError(String message) {
        errorMessage.setText(message);
        if (message.isEmpty()) {
            errorMessage.setVisible(false);
        } else {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setVisible(true);
        }
    }
}
