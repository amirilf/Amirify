package app.controller.auth;

import app.models.User;
import app.models.Database;

public class Login {

    private Login() {
    };

    public static String login(String username, String password) {

        if (CurrentUser.isThereUser())
            return "First logout from this account!";

        for (User u : Database.getDB().getUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                CurrentUser.login(u);
                return "You have successfully logged in";
            }
        }
        return "Username or Password is wrong!";
    }
}
