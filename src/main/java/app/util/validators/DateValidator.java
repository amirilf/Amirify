package app.util.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    public static String isValid(String dateString) {

        if (!dateString.matches("\\d{4}/\\d{2}/\\d{2}")) {
            return "Date format is not valid!, Accepted format is (YYYY/MM/DD)";
        }

        try {
            LocalDate date = LocalDate.parse(dateString,
                    DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            // check range of date => (100 years ago until now)
            LocalDate now = LocalDate.now();
            if (date.isBefore(now.minusYears(100)) || date.isAfter(now))
                return "Date is not within the range (100 years ago to now).";

        } catch (DateTimeParseException e) {
            return "Invalid date format."; // like 2022/14/12 => 14 will give us this error
        }

        return null;
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public static LocalDate stringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
