package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new Pane()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}