package app.model;

import java.util.ArrayList;
// import utility.RandomGenerator;

public class Album {

    private static int id = 1; // is used to generate unique albumID everytime
    private final String albumID;

    private String name;
    private String description = ""; // optional
    private final String userID;
    private ArrayList<Music> musics = new ArrayList<Music>();

    public Album(String name, String userID) {
        this.name = name;
        this.userID = userID;
        this.albumID = String.valueOf(id++);
        // this.albumID = RandomGenerator.randomIDGenerator(4, id++, 3);
    }

    // GETTER
    public String getName() {
        return this.name;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getAlbumID() {
        return this.albumID;
    }

    public ArrayList<Music> getMusics() {
        return this.musics;
    }

    public String getDescription() {
        return this.description;
    }

    // SETTER

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
