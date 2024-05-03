package app.utility.validators;

import java.util.regex.Pattern;

public class LinkValidator {

    private static final String LINK_REGEX = "^(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})$";
    public static final String ERROR = "The Link you entered is not valid, make it correct!";

    public static boolean isValid(String link) {
        return Pattern.compile(LINK_REGEX).matcher(link).matches();
    }
}
