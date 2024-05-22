package app.controller.auth;

import java.time.LocalDate;
import app.model.BasicListener;
import app.model.Database;
import app.model.Podcaster;
import app.model.Singer;
import app.model.User;

public class SignUp {

    private SignUp() {
    };

    public static BasicListener signUpListener(String username, String password, String firstName, String lastName,
            String email, String phone,
            LocalDate birthDate) {

        BasicListener user = new BasicListener(username, password, firstName, lastName, email, phone, birthDate);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);

        return user;
    }

    public static Singer signUpSinger(String username, String password, String firstName, String lastName, String email,
            String phone,
            LocalDate birthDate) {

        Singer user = new Singer(username, password, firstName, lastName, email, phone, birthDate);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);

        return user;
    }

    public static Podcaster signUpPodcaster(String username, String password, String firstName, String lastName,
            String email, String phone,
            LocalDate birthDate) {

        Podcaster user = new Podcaster(username, password, firstName, lastName, email, phone, birthDate);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);

        return user;
    }

    public static boolean validUsername(String username) {
        for (User users : Database.getDB().getUsers()) {
            if (users.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
