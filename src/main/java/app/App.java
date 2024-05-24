package app;

import java.io.IOException;
import java.time.LocalDate;

import app.controller.ListenterController;
import app.controller.auth.CurrentUser;
import app.controller.auth.SignUp;
import app.model.Admin;
import app.util.LoadAudios;
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
                Admin.getAdmin("admin", "1234", "mohammad admin", "adminian",
                                "admin@gmail.com", "09999999999",
                                LocalDate.of(2010, 4, 12));

                // load sample data
                LoadAudios.loadAudios();

                SignUp.signUpListener("amirilf", "amir", "Amir", "Khorasani",
                                "amirilf@protonmail.com", "09999999999",
                                LocalDate.of(2000, 10, 10));

                ListenterController.follow("3");
                ListenterController.follow("4");
                ListenterController.follow("5");
                CurrentUser.logout();

                launch();
        }
}