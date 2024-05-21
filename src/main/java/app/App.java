package app;

import java.io.IOException;
import java.time.LocalDate;

import app.controller.ArtistController;
import app.controller.SingerController;
import app.controller.auth.CurrentUser;
import app.controller.auth.SignUp;
import app.model.Admin;
import app.model.Artist;
import app.model.Audio;
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
                Admin.getAdmin("admin", "1234", "mohammad admin", "adminian", "admin@gmail.com", "09999999999",
                                LocalDate.of(2010, 4, 12));

                SignUp.signUpListener("amirilf", "amir", "Amir", "Khorasani", "amirilf@protonmail.com", "09999999999",
                                LocalDate.of(2000, 10, 10));

                CurrentUser.logout();

                // TODO: remove these test lines later

                SignUp.signUpSinger("adel", "adel1234", "Adel", "Adelian", "adel@email.com", "09966337929",
                                LocalDate.of(1990, 10, 2));
                SingerController.addAlbum("Hello Album");
                SingerController.addMusic("Hello", "Pop", "This is sample lyrics", "/app/media/Adele - Hello.mp3",
                                "/app/media/cover2.png", "1");
                CurrentUser.logout();

                SignUp.signUpSinger("justin", "justin1234", "Justin", "Bieber", "justin@email.com", "09966337929",
                                LocalDate.of(2010, 10, 2));
                SingerController.addAlbum("Purpose");
                SingerController.addMusic("Love Yourself", "Pop", "This is sample lyrics",
                                "/app/media/Love Yourself.mp3", "/app/media/cover3.png", "2");
                CurrentUser.logout();

                SignUp.signUpSinger("trevor", "trevor1234", "Daniel", "Trevor", "daniel@email.com", "09966337929",
                                LocalDate.of(2000, 10, 2));
                SingerController.addAlbum("Fight");
                SingerController.addMusic("Falling", "HipHop", "This is sample lyrics",
                                "/app/media/Trevor Daniel - Falling.mp3", "/app/media/cover4.png", "3");
                CurrentUser.logout();

                launch();
        }
}