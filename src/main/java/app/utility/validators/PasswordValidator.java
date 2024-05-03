package app.utility.validators;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
    public static final String ERROR = "The Password is not Strong, choose a better one!\n(Include a mix of uppercase letters, lowercase letters, numbers, and special characters and make sure its length is at least 8)";

    public static boolean isValid(String password) {
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }

}