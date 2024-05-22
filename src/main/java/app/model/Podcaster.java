package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Podcaster extends Artist {

    private ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

    public Podcaster(String username, String password, String firstName, String lastName, String email, String phone,
            LocalDate birthDate, String bg, String profile) {
        super(username, password, firstName, lastName, email, phone, birthDate, bg, profile);
    }

    // GETTER
    public ArrayList<Podcast> getPodcasts() {
        return this.podcasts;
    }
}
