package app.model;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import app.util.Duration;
import app.util.Plays;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;

public class Audio {

    private static int id = 1; // is used to generate unique albumID everytime
    private final String audioID;
    private String title;
    private final String userID;
    private int playedTimes;
    private int likes;
    private final LocalDate publishDate;
    private Genre genre;
    private String link;
    private String cover;
    private String caption = "";
    private long duration;

    public Audio(String title, String userID, Genre genre, String link,
            String cover) {

        this.audioID = String.valueOf(id++);
        this.title = title;
        this.userID = userID;
        this.playedTimes = 0;
        this.publishDate = LocalDate.now();
        this.genre = genre;
        this.link = link;
        this.cover = cover;
        this.likes = 0;

        try {
            this.duration = calculateDuration(link);
        } catch (IOException | JavaLayerException e) {
            System.out.println(link);
            System.out.println(e.getMessage());
        }

    }

    // GETTER
    public int getLikes() {
        return this.likes;
    }

    public String getCaption() {
        return this.caption;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLink() {
        return this.link;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public String getCover() {
        return this.cover;
    }

    public String getAudioID() {
        return this.audioID;
    }

    public String getUserID() {
        return this.userID;
    }

    public int getPlayedTimes() {
        return this.playedTimes;
    }

    public LocalDate getPublishDate() {
        return this.publishDate;
    }

    public long getDuration() {
        return duration;
    }

    // SETTER
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    // we can have a method to increase it by one
    public void setPlayedTimes(int playedTimes) {
        this.playedTimes = playedTimes;
    }

    // OTHER
    public void increaseLike() {
        this.likes++;
    }

    public void increasePlayTime() {
        this.playedTimes++;
    }

    private int calculateDuration(String filePath) throws IOException, JavaLayerException {

        InputStream fileStream = getClass().getResourceAsStream(filePath);
        if (fileStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        BufferedInputStream buffer = new BufferedInputStream(fileStream);
        Bitstream bitstream = new Bitstream(buffer);
        int time = 0; // milli seconds!
        Header frame;
        try {
            while ((frame = bitstream.readFrame()) != null) {
                time += frame.ms_per_frame();
                bitstream.closeFrame();
            }
        } finally {
            bitstream.close();
            buffer.close();
            fileStream.close();
        }

        return time;
    }

    public String getHumanReadableDuration() {
        return Duration.durationToString(this.duration);
    }

    public String getHumanReadablePlays() {
        return Plays.playTimesToString(this.playedTimes);
    }

    public String getStandardDuration() {
        return Duration.durationToStandardFormat(this.duration);
    }

}
