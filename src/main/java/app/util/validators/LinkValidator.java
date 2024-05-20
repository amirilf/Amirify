package app.util.validators;

import java.util.regex.Pattern;

import app.exceptions.InvalidFormatException;

public class LinkValidator {

    private static final String LINK_REGEX = "^(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})$";
    public static final String ERROR = "The Link you entered is not valid!";

    public static void check(String link) throws InvalidFormatException {
        if (!Pattern.compile(LINK_REGEX).matcher(link).matches()) {
            throw new InvalidFormatException(ERROR);
        }
    }
}
