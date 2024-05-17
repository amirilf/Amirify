package app.util.validators;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final String PHONE_REGEX = "^09\\d{9}$";
    public static final String ERROR = "Phone is not valid! (should start with 09 & have length of 11)";

    public static boolean isValid(String password) {
        return Pattern.compile(PHONE_REGEX).matcher(password).matches();
    }

}
