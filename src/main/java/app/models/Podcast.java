package app.models;

public class Podcast extends Audio {

    private String transcript;

    public Podcast(String title, String userID, Genre genre, String link,
            String cover, String transcript) {
        super(title, userID, genre, link, cover);
        this.transcript = transcript;
    }

    // GETTER
    public String getTranscript() {
        return this.transcript;
    }

    // SETTER
    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

}
