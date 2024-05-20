package app.util.validators;

import java.util.regex.Pattern;

import app.exceptions.InvalidFormatException;

public class EmailValidator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_]+(?:\\.[a-zA-Z0-9_]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String ERROR = "The Email format is not valid!";

    public static void check(String email) throws InvalidFormatException {
        if (!Pattern.compile(EMAIL_REGEX).matcher(email).matches()) {
            throw new InvalidFormatException("Email");
        }
    }
}
