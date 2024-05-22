package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.controller.auth.CurrentUser;
import app.model.Album;
import app.model.Artist;
import app.model.Audio;
import app.model.Database;
import app.model.Music;
import app.model.Podcast;
import app.model.Podcaster;
import app.model.Singer;
import app.model.User;

public class ArtistController {

    private static Artist getArtist() {
        return (Artist) CurrentUser.getUser();
    }

    public static ArrayList<Audio> getAudios() {
        ArrayList<Audio> audios = new ArrayList<>();
        for (Audio audio : Database.getDB().getAudios())
            if (audio.getUserID().equals(getArtist().getUserID()))
                audios.add(audio);
        return (audios.size() == 0) ? null : audios;
    }

    public static List<Audio> getTopAudios(Artist artist, int number) {
        String artistID = artist.getUserID();
        ArrayList<Audio> audios = new ArrayList<>();

        for (Audio audio : Database.getDB().getAudios()) {
            if (audio.getUserID().equals(artistID)) {
                audios.add(audio);
            }
        }

        // return top {number} audios based on likes
        return audios.stream()
                .sorted((a1, a2) -> Integer.compare(a2.getLikes(), a1.getLikes()))
                .limit(number)
                .collect(Collectors.toList());
    }

    public static String getAudiosPlayedTimesString() {
        ArrayList<Audio> audios = getAudios();
        if (audios == null)
            return "There is no recorded audio by you!";

        int length = audios.size();
        StringBuilder sb = new StringBuilder("Audios pleayed times: (AudioID | Title | PlayedTimes)\n");
        sb.ensureCapacity(length * 50);
        int counter = 1;
        int digits = String.valueOf(length).length();

        for (Audio audio : audios)
            sb.append("    " + String.format("%0" + digits + "d", counter++) + ") "
                    + audio.getAudioID() + " | " + audio.getTitle()
                    + " | "
                    + audio.getPlayedTimes() + "\n");

        return sb.toString();
    }

    public static String getFollowersString() {
        ArrayList<User> followers = getArtist().getFollowers();
        int length = followers.size();
        if (length == 0)
            return "You have no follower right now!";

        StringBuilder sb = new StringBuilder("Followers: (Username | Name)\n");
        sb.ensureCapacity(length * 40);
        int counter = 1;
        int digits = String.valueOf(counter).length();
        for (User user : followers) {
            sb.append("    " + String.format("%0" + digits + "d", counter) + ") " + user.getUsername() + " | "
                    + user.getFullName() + "\n");
        }
        return sb.toString();
    }

    private static float CalculateEarnings() {
        float result = 0;
        if (CurrentUser.getUser() instanceof Singer) {
            Singer singer = (Singer) CurrentUser.getUser();
            for (Album album : singer.getAlbums()) {
                for (Music music : album.getMusics()) {
                    result += music.getPlayedTimes() * 0.4;
                }
            }
        } else {
            Podcaster podcaster = (Podcaster) CurrentUser.getUser();
            for (Podcast podcast : podcaster.getPodcasts())
                result += podcast.getPlayedTimes() * 0.5;
        }
        return result;
    }

    public static String CalculateEarningsString() {
        float value = CalculateEarnings();
        return (value == 0) ? "You have not earned any income yet ):" : "Your income: " + value;
    }
}
