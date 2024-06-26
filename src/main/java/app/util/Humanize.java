package app.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Humanize {

    public static String intToHumanFormat(int plays) {
        return String.format(Locale.US, "%,d", plays);
    }

    public static int humanFormatToInt(String humanFormat) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        Number number = numberFormat.parse(humanFormat);
        return number.intValue();
    }

    // converts the duration time to a human readable string
    // ex => 136 seconds => 2min 16sec
    // duration is in mili seconds
    public static String durationToString(long duration) {
        long totalSeconds = duration / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        StringBuilder sb = new StringBuilder();
        if (hours > 0)
            sb.append(hours).append("h ");
        if (minutes > 0)
            sb.append(minutes).append("min ");
        if (seconds > 0)
            sb.append(seconds).append("sec");

        return sb.toString().trim();
    }

    // duration is in mili seconds
    public static String durationToStandardFormat(long duration) {
        long totalSec = duration / 1000;
        long hours = totalSec / 3600;
        long minutes = (totalSec % 3600) / 60;
        long seconds = totalSec % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        if (hours == 0) {
            formattedTime = formattedTime.substring(3);
        }

        return formattedTime;
    }

}
