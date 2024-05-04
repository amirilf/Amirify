package app.controller;

import app.controller.auth.CurrentUser;
import app.model.User;
import app.utility.validators.DateValidator;

public class UserController {

    private UserController() {
    };

    public static String getUserInfo() {
        User user = CurrentUser.getUser();
        if (user == null)
            return "First login!";
        StringBuilder sb = new StringBuilder("-----\n  * Username: " + user.getUsername()
                + "\n  * Full Name: " + user.getFullName()
                + "\n  * Email: " + user.getEmail()
                + "\n  * Phone: " + user.getPhone()
                + "\n  * Birth Date: " + DateValidator.dateToString(user.getBirthDate())
                + "\n  * Date joined: " + DateValidator.dateToString(user.getJoinDate()));

        sb.append("\n-----");
        return sb.toString();
    }
}
