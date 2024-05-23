package app.util;

import java.util.Locale;

public class Plays {

    public static String playTimesToString(int plays) {
        return String.format(Locale.US, "%,d", plays);
    }
}
