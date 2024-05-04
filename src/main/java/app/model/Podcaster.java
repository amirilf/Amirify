package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Podcaster extends Artist {

    private ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

    public Podcaster(String username, String password, String firstName, String lastName, String email, String phone,
            LocalDate birthDate,
            String bio) {
        super(username, password, firstName, lastName, email, phone, birthDate, bio);
    }

    // GETTER
    public ArrayList<Podcast> getPodcasts() {
        return this.podcasts;
    }
}