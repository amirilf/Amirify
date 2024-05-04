package app.model;

import java.time.LocalDate;

public class BasicListener extends Listener {

    // define limits here?
    private final static int musicLimit = 10;
    private final static int playlistLimit = 3;

    public BasicListener(String username, String password, String firstName, String lastName, String email,
            String phone,
            LocalDate birthDate) {
        super(username, password, firstName, lastName, email, phone, birthDate);
    }

    // GETTER
    public static int getMusicLimit() {
        return musicLimit;
    }

    public static int getPlaylistLimit() {
        return playlistLimit;
    }
}
