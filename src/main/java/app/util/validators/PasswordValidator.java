package app.util.validators;

import java.util.regex.Pattern;

import app.exceptions.InvalidFormatException;

public class PasswordValidator {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
    public static final String ERROR = "Password is not Strong! (uppercase | lowercase | numbers | special | 8 length)";

    public static void check(String password) throws InvalidFormatException {
        if (!Pattern.compile(PASSWORD_REGEX).matcher(password).matches()) {
            throw new InvalidFormatException(ERROR);
        }
    }

}