package app.models;

public class Music extends Audio {

    private String lyrics;

    public Music(String title, String userID, Genre genre, String link,
            String cover,
            String lyrics) {
        super(title, userID, genre, link, cover);
        this.lyrics = lyrics;
    }

    // GETTER
    public String getLyrics() {
        return this.lyrics;
    }

    // SETTER
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
