package app.models;

import java.time.LocalDate;

public class Report {

    private final User user;
    private final Artist artist;
    private final LocalDate date;
    private String description; // only thing that can be changed

    public Report(User user, Artist artist, String description) {
        this.user = user;
        this.artist = artist;
        this.description = description;
        this.date = LocalDate.now();
    }

    // GETTER
    public Artist getArtist() {
        return this.artist;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public User getUser() {
        return this.user;
    }

    public String getDescription() {
        return this.description;
    }

    // SETTER
    public void setDescription(String description) {
        this.description = description;
    }

}
