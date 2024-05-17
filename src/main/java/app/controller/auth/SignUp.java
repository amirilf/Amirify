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

    public static void signUpListener(String username, String password, String firstName, String lastName,
            String email, String phone,
            LocalDate date) {

        BasicListener user = new BasicListener(username, password, firstName, lastName, email, phone, date);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);
    }

    public static void signUpSinger(String username, String password, String firstName, String lastName, String email,
            String phone,
            LocalDate date) {

        Singer user = new Singer(username, password, firstName, lastName, email, phone, date);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);
    }

    public static void signUpPodcaster(String username, String password, String firstName, String lastName,
            String email, String phone,
            LocalDate date) {

        Podcaster user = new Podcaster(username, password, firstName, lastName, email, phone, date);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);
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
