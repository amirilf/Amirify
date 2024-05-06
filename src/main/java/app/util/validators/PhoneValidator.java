package app.util.validators;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final String PHONE_REGEX = "^\\+(?:[0-9]){6,14}[0-9]$";
    public static final String ERROR = "The phone is not valid! it should be started with a `+` and your country code plus the rest of your number.";

    public static boolean isValid(String password) {
        return Pattern.compile(PHONE_REGEX).matcher(password).matches();
    }

}
