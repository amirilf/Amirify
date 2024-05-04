package app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.model.Artist;
import app.model.Audio;
import app.model.Database;
import app.model.Music;
import app.model.Report;
import app.model.User;
import app.utility.validators.DateValidator;

public class AdminController {

    private AdminController() {
    };

    private static ArrayList<Audio> getAudios() {
        return Database.getDB().getAudios();
    }

    public static String getAudiosString() {
        ArrayList<Audio> audios = getAudios();
        int length = audios.size();
        if (length == 0)
            return "There is no Audio to show!";

        StringBuilder sb = new StringBuilder("Audios: (ID | Title | Likes | Played Times)\n");
        sb.ensureCapacity(length * 50);
        int counter = 1;
        int digits = String.valueOf(length).length();
        for (Audio audio : audios) {
            sb.append("    " + String.format("%0" + digits + "d", counter++) + ") "
                    + audio.getAudioID() + " | " + audio.getTitle() + " | " + audio.getLikes() + " | "
                    + audio.getPlayedTimes() + ")\n");
        }
        return sb.toString();
    }

    private static ArrayList<Artist> getArtists() {
        ArrayList<Artist> artists = new ArrayList<>();
        for (User user : Database.getDB().getUsers()) {
            if (user instanceof Artist) {
                artists.add((Artist) user);
            }
        }

        return artists;
    }

    public static String getArtistsString() {
        ArrayList<Artist> artists = getArtists();
        int length = artists.size();
        if (length == 0)
            return "There is no Artist to show!";

        StringBuilder sb = new StringBuilder("Artists: (Username | Full Name)\n");
        sb.ensureCapacity(length * 50);
        int counter = 1;
        int digits = String.valueOf(length).length();
        for (Artist artist : artists) {
            sb.append("    " + String.format("%0" + digits + "d", counter++) + ") "
                    + artist.getUsername() + " | " + artist.getFullName() + "\n");
        }
        return sb.toString();
    }

    public static Artist getArtist(String username) {

        for (User user : Database.getDB().getUsers()) {
            if (user.getUsername().equals(username)) {
                if (user instanceof Artist)
                    return (Artist) user;
                break;
            }
        }
        // returns null if there is no match with given username
        return null;
    }

    public static String getArtistString(String username) {
        Artist artist = getArtist(username);
        if (artist == null)
            return "There is no Artist with this username!";
        return "Artist found with username :" + username + "\n   * Full Name : " + artist.getFullName()
                + "\n   * Email : " + artist.getEmail() + "\n   * Phone : " + artist.getPhone() + "\n   * BirthDate :"
                + DateValidator.dateToString(artist.getBirthDate()) + "\n   * Date joined : "
                + DateValidator.dateToString(artist.getJoinDate());
    }

    public static Audio getAudio(String audioID) {
        for (Audio audio : Database.getDB().getAudios()) {
            if (audio.getAudioID().equals(audioID)) {
                return audio;
            }
        }
        // returns null if there is no match with given audioID
        return null;
    }

    public static String getAudioString(String audioID) {
        Audio audio = getAudio(audioID);
        if (audio == null)
            return "There is no Audio with this ID";
        String type = (audio instanceof Music) ? "Music" : "Podcast";
        return "Audio found with ID " + audioID + "\n   * Type : " + type + "\n   * Title : " + audio.getTitle()
                + "\n   * Played Times : " + audio.getPlayedTimes() + "\n   * Likes : " + audio.getLikes()
                + "\n   * Genre : " + audio.getGenre().name();
    }

    private static ArrayList<Report> getReports() {
        return Database.getDB().getReports();
    }

    public static String getReportsString() {
        ArrayList<Report> reports = getReports();
        int length = reports.size();
        if (length == 0)
            return "There is no Report!";

        StringBuilder sb = new StringBuilder("Reports: (Reporter name | Reported name | Description)\n");
        sb.ensureCapacity(length * 50);
        int counter = 1;
        int digits = String.valueOf(length).length();
        for (Report report : reports) {
            sb.append("-----\n    " + String.format("%0" + digits + "d", counter++) + ") "
                    + report.getUser().getFullName() + " | " + report.getArtist().getFullName() + "\n     Description: "
                    + report.getDescription() + "\n-----\n");
        }
        return sb.toString();
    }

    private static List<Audio> getMostLikedAudios(int n) {

        ArrayList<Audio> audios = Database.getDB().getAudios();
        Collections.sort(audios, (a1, a2) -> Integer.compare(a2.getLikes(), a1.getLikes()));
        return new ArrayList<>(audios.subList(0, Math.min(n, audios.size())));
    }

    public static String getMostLikedAudiosString(String number) {

        int n;
        try {
            n = Integer.parseInt(number);
        } catch (Exception e) {
            return "The parameter must be an Integer!";
        }

        List<Audio> audios = getMostLikedAudios(n);
        int length = audios.size();
        if (length == 0)
            return "There is no Audio to find most liked ones!";

        StringBuilder sb = new StringBuilder("Most Liked Audios: (ID | Title | Likes\n");
        sb.ensureCapacity(length * 50);
        int counter = 1;
        int digits = String.valueOf(length).length();
        for (Audio audio : audios) {
            sb.append("    " + String.format("%0" + digits + "d", counter++) + ") "
                    + audio.getAudioID() + " | " + audio.getTitle() + " | "
                    + audio.getLikes() + "\n");
        }
        return sb.toString();
    }

}
