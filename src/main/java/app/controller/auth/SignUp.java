package app.controller.auth;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.model.BasicListener;
import app.model.Database;
import app.model.Podcaster;
import app.model.Singer;
import app.model.User;
import app.utility.validators.DateValidator;
import app.utility.validators.EmailValidator;
import app.utility.validators.PasswordValidator;
import app.utility.validators.PhoneValidator;

public class SignUp {

    private SignUp() {
    };

    private static String validator(String username, String password, String email, String phone, String date) {

        if (!PasswordValidator.isValid(password))
            return PasswordValidator.ERROR;
        if (!PhoneValidator.isValid(phone))
            return PhoneValidator.ERROR;
        if (!EmailValidator.isValid(email))
            return EmailValidator.ERROR;

        String dateError = DateValidator.isValid(date);
        if (dateError != null)
            return dateError;

        if (!validUsername(username))
            return "This username is already taken!";

        return null;
    }

    public static String signUpListener(String username, String password, String firstName, String lastName,
            String email, String phone,
            String stringDate) {

        if (CurrentUser.isThereUser())
            return "First logout from this account!";

        String validator = validator(username, password, email, phone, stringDate);
        if (validator != null)
            return validator;

        // everything is fine, we're gonna create the listener
        LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        BasicListener user = new BasicListener(username, password, firstName, lastName, email, phone, date);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);

        return "Welcome new listener!";
    }

    public static String signUpSinger(String username, String password, String firstName, String lastName, String email,
            String phone,
            String stringDate, String bio) {

        if (CurrentUser.isThereUser())
            return "First logout from this account!";

        String validator = validator(username, password, email, phone, stringDate);
        if (validator != null)
            return validator;

        // everything is fine, we're gonna create the singer
        LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Singer user = new Singer(username, password, firstName, lastName, email, phone, date, bio);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);

        return "Welcome new singer!";
    }

    public static String signUpPodcaster(String username, String password, String firstName, String lastName,
            String email, String phone,
            String stringDate, String bio) {

        if (CurrentUser.isThereUser())
            return "First logout from this account!";

        String validator = validator(username, password, email, phone, stringDate);
        if (validator != null)
            return validator;

        // everything is fine, we're gonna create the podcaster
        LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Podcaster user = new Podcaster(username, password, firstName, lastName, email, phone, date, bio);
        Database.getDB().getUsers().add(user);
        CurrentUser.login(user);

        return "Welcome new podcaster!";
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
