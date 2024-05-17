package app;

import java.io.IOException;

import app.model.Admin;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Variables.authFXMLPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(Variables.logoPath)));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Amirify");
        stage.show();
    }

    public static void main(String[] args) {
        Admin.getAdmin("admin", "1234", "mohammad admin", "adminian", "admin@gmail.com", "09999999999", "2000-01-01");
        launch();
    }
}