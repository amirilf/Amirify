package app.controller.auth;

import app.exceptions.UserNotFoundException;
import app.exceptions.WrongPasswordException;
import app.model.Database;
import app.model.User;

public class Login {

    private Login() {
    };

    public static void login(String username, String password) throws UserNotFoundException, WrongPasswordException {

        boolean found = false;

        for (User u : Database.getDB().getUsers()) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    found = true;
                    CurrentUser.login(u);
                    return;
                }
                throw new WrongPasswordException();
            }
        }

        if (!found)
            throw new UserNotFoundException();
    }
}
