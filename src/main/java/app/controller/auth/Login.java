package app.controller.auth;

import app.model.Database;
import app.model.User;

public class Login {

    private Login() {
    };

    public static boolean login(String username, String password) {

        for (User u : Database.getDB().getUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                CurrentUser.login(u);
                return true;
            }
        }
        return false;
    }
}
