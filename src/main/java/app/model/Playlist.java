package app.model;

import java.time.LocalDate;
import java.util.ArrayList;
// import utility.RandomGenerator;

public class Playlist {

    private static int id = 1; // is used to generate unique playlistID everytime
    private final String playlistID;
    private String name;
    private final String userID;
    private ArrayList<Audio> audios = new ArrayList<Audio>();

    // extra
    private final LocalDate createdDate;
    private String description = ""; // optional

    public Playlist(String name, String userID) {
        // this.playlistID = RandomGenerator.randomIDGenerator(4, id++, 3);
        this.playlistID = String.valueOf(id++);
        this.name = name;
        this.userID = userID;
        this.createdDate = LocalDate.now();
    }

    // GETTER

    public String getName() {
        return this.name;
    }

    public ArrayList<Audio> getAudios() {
        return this.audios;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPlaylistID() {
        return this.playlistID;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    // SETTER
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
