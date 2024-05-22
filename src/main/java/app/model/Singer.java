package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Singer extends Artist {

    private ArrayList<Album> albums = new ArrayList<Album>();

    public Singer(String username, String password, String firstName, String lastName, String email, String phone,
            LocalDate birthDate, String bg, String profile) {
        super(username, password, firstName, lastName, email, phone, birthDate, bg, profile);
    }

    // GETTER
    public ArrayList<Album> getAlbums() {
        return this.albums;
    }

}
