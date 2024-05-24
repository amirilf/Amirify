package app.gui.base;

import java.io.IOException;
import java.util.List;

import app.controller.auth.CurrentData;
import app.util.Variables;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/*
 * in this controller we have a contentPath which shows current content fxml file path
 * then in other controllers if user clicks on smth like Home button or Profile or anything else
 * then in that controller we will update contentPath by its setter, and here we have a listener
 * which calls loadPage method after realizing that contentPath is updated, then we achieve the goal :D
 * 
 * we also don't need to set path like => "/app/..." we just send "page/Home" or ... {without .fxml}
 * ex => "page/Home", "auth/Auth" or ...
 * 
 * all contents should have the same size as body anchor size which is 1000,700 if not then میفتد مشکل ها
 */
public class BodyController {

    @FXML
    private AnchorPane mainAnchorPane;

    private static StringProperty contentPath = new SimpleStringProperty();

    public static void setFxmlPath(List<String> list, boolean addToHistory) {
        if (list == null) {
            contentPath.set("");
        } else {
            if (addToHistory && !CurrentData.currentPage.equals(list)) {
                CurrentData.addHistory(list);
            }
            contentPath.set(list.get(0));
        }
    }

    public static void setFxmlPath(List<String> list) {
        setFxmlPath(list, true);
    }

    public static StringProperty getContentPath() {
        return contentPath;
    }

    @FXML
    private void initialize() {

        contentPath.addListener((observable, oldValue, newValue) -> loadPage());

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.bodyCSSPath).toString());
        // =============

    }

    private void loadPage() {
        // if we make fxml path empty ("") then it غلط میکنه حرکت خاصی بزنه /:
        if (!contentPath.get().equals("")) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(Variables.FXMLBasePath + contentPath.get() + ".fxml"));
                Node page = loader.load();
                mainAnchorPane.getChildren().setAll(page);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
