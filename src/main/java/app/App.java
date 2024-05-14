package app;

import java.io.IOException;

import app.util.Variables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource(Variables.baseFXMLPath));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/page/Library.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(Variables.logoPath)));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Amirify");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}