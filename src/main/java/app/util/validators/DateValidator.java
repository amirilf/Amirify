package app.util.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import app.exceptions.InvalidFormatException;

public class DateValidator {

    private static String dateFormat = "yyyy-MM-dd";

    public static void check(String dateObject) throws InvalidFormatException {
        check(stringToDate(dateObject));
    }

    public static void check(LocalDate dateObject) throws InvalidFormatException {

        String dateString = dateToString(dateObject);

        if (!dateString.matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new InvalidFormatException("Date format is not valid!, correct format is " + dateFormat);

        LocalDate date = LocalDate.parse(dateString,
                DateTimeFormatter.ofPattern(dateFormat));

        // check range of date => (100 years ago until now)
        LocalDate now = LocalDate.now();
        if (date.isBefore(now.minusYears(100)) || date.isAfter(now))
            throw new InvalidFormatException("Date is not within the range (100 years ago to now).");
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public static LocalDate stringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
    }

}
