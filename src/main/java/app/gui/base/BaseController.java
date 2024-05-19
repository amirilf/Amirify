package app.gui.base;

import app.util.Variables;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BaseController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private void initialize() {

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.baseCSSPath).toString());
        // =============
    }

}
