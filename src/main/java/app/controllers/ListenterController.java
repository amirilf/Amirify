package app.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import app.controllers.auth.CurrentUser;
import app.models.Artist;
import app.models.Audio;
import app.models.BasicListener;
import app.models.Database;
import app.models.Genre;
import app.models.Listener;
import app.models.Music;
import app.models.Playlist;
import app.models.Podcast;
import app.models.PremiumListener;
import app.models.PremiumPackage;
import app.models.Report;
import app.models.User;
import app.utility.validators.DateValidator;

public class ListenterController {

    private static Listener getListener() {
        return (Listener) CurrentUser.getUser();
    }

    public static String getArtistsString() {
        return AdminController.getArtistsString();
    }

    public static String getArtistString(String username) {
        return AdminController.getArtistString(username);
    }

    private static ArrayList<Audio> sort(char basedOn) {

        // basedOn must be checked before being sent to this method

        ArrayList<Audio> audios = Database.getDB().getAudios();
        if (basedOn == 'L')
            Collections.sort(audios, (a1, a2) -> Integer.compare(a2.getLikes(), a1.getLikes()));
        else // P
            Collections.sort(audios, (a1, a2) -> Integer.compare(a2.getPlayedTimes(), a1.getPlayedTimes()));
        return audios;
    }

    public static String sortString(char basedOn) {
        if (basedOn == 'P' || basedOn == 'L') {
            ArrayList<Audio> audios = sort(basedOn);
            int length = audios.size();
            int counter = 1;
            int digits = String.valueOf(length).length();

            StringBuilder sb;
            if (basedOn == 'P')
                sb = new StringBuilder("All audios based on played times\n");
            else
                sb = new StringBuilder("All audios based on likes (Title -> <ID | Likes | PlayedTimes>)\n");

            for (Audio audio : audios) {
                sb.append("  " + String.format("%0" + digits + "d", counter++) + ") " + audio.getTitle()
                        + "\n    - ID: " + audio.getAudioID() + "\n    - Likes: " + audio.getLikes() + "\n    - Plays: "
                        + audio.getPlayedTimes() + "\n");
            }
            return sb.toString();
        }
        return "You entered a wrong based on choice! (L or P for likes or plays)";
    }

    private static String setFavoriteGenres(ArrayList<Genre> genres) {
        if (getListener().getFavoriteGenres().size() == 0) {
            for (Genre genre : genres)
                getListener().getFavoriteGenres().add(genre);
            return "Your favorite genres have been successfully added!";
        }
        return "You already added your favorite genres before!";
    }

    public static String setFavoriteGenresString(String line) {

        String[] genres = line.split(",");
        ArrayList<Genre> genresArray = new ArrayList<>();

        for (int i = 1; i < genres.length; i++) {
            try {
                genresArray.add(Genre.valueOf(genres[i]));
            } catch (Exception e) {
                return "Incorrect genre found! see Help if you need.";
            }
        }
        return setFavoriteGenres(genresArray);
    }

    private static ArrayList<Audio> suggestions(int n) {

        ArrayList<Audio> audios = new ArrayList<>();
        ArrayList<Genre> genres = getListener().getFavoriteGenres();

        for (Audio audio : Database.getDB().getAudios()) {
            if (genres.contains(audio.getGenre())) {
                audios.add(audio);
                --n;
                if (n == 0)
                    break;
            }
        }

        return audios;
    }

    public static String suggestionsString(int n) {
        ArrayList<Audio> audios = suggestions(n);
        int length = audios.size();

        if (length == 0)
            return "There is no matching audio to suggest you!";

        int counter = 1;
        int digits = String.valueOf(length).length();

        StringBuilder sb = new StringBuilder("Suggestions for you: (AudioID | Type | Title | Genre)\n");

        for (Audio audio : audios) {
            String type = (audio instanceof Music) ? "Music" : "Podcast";
            sb.append("    " + String.format("%0" + digits + "d", counter++) + ") " + audio.getAudioID() + " | " + type
                    + " | " + audio.getTitle() + " | " + audio.getGenre().name()
                    + "\n");
        }

        if (n > length)
            sb.insert(0, "The amount of available suggestions is less than the requested amount\n");

        return sb.toString();
    }

    public static String follow(String username) {

        Artist artist = AdminController.getArtist(username);

        if (artist == null)
            return "There is no Artist with that username!";

        if (artist.getFollowers().contains(getListener()))
            return "You already followed this artist before!";

        artist.getFollowers().add(getListener());
        getListener().getFollowings().add(artist.getUserID());

        return "Now you are following Artist (Name: " + artist.getFullName() + " | username: " + artist.getUsername()
                + ")";
    }

    private static ArrayList<Audio> filter(String userID) {

        // username supposed to be correct
        // and need to be ckecked before being sent to this method

        ArrayList<Audio> audios = new ArrayList<>();
        for (Audio audio : Database.getDB().getAudios())
            if (audio.getUserID().equals(userID))
                audios.add(audio);
        return audios;
    }

    private static ArrayList<Audio> filter(Genre genre) {

        // genre is supposed to be correct
        // and need to be ckecked before being sent to this method

        ArrayList<Audio> audios = new ArrayList<>();
        for (Audio audio : Database.getDB().getAudios())
            if (audio.getGenre().equals(genre))
                audios.add(audio);
        return audios;
    }

    private static ArrayList<Audio> filter(LocalDate date) {

        // date is supposed to be correct
        // and need to be ckecked before being sent to this method

        ArrayList<Audio> audios = new ArrayList<>();
        for (Audio audio : Database.getDB().getAudios())
            if (audio.getPublishDate().equals(date))
                audios.add(audio);
        return audios;
    }

    private static List<Audio> filter(LocalDate begin, LocalDate end) {

        // dates are supposed to be correct
        // and need to be ckecked before being sent to this method

        return Database.getDB().getAudios().stream()
                .filter(audio -> audio.getPublishDate().isAfter(begin) && audio.getPublishDate().isBefore(end))
                .sorted(Comparator.comparing(Audio::getPublishDate).thenComparing(Audio::getTitle))
                .collect(Collectors.toList());
    }

    public static String filterString(char filterBy, String key) {

        ArrayList<Audio> audios;

        switch (filterBy) {
            case 'D':
                String error = DateValidator.isValid(key);
                if (error != null)
                    return error;
                audios = filter(DateValidator.stringToDate(key));
                break;
            case 'A':
                Artist artist = AdminController.getArtist(key);
                if (artist == null)
                    return "There is no Artist with that username!";
                audios = filter(artist.getUserID());
                break;
            case 'G':
                try {
                    Genre genre = Genre.valueOf(key);
                    audios = filter(genre);
                    break;
                } catch (IllegalArgumentException e) {
                    return "There is no such genre! see Help for more.";
                }
            default:
                return "The filter option is not correct (should be one of A, D or G)";
        }

        int length = audios.size();

        if (length == 0)
            return "There is no any audio with this filter!";

        int counter = 1;
        int digits = String.valueOf(length).length();
        StringBuilder sb = new StringBuilder();

        switch (filterBy) {
            case 'A':
                sb.append("Filter results by Artist:" + key + " (AudioID | Title | Genre | Date)\n");
                for (Audio audio : audios)
                    sb.append("    " + String.format("%0" + digits + "d", counter++) + ") " + audio.getAudioID() + " | "
                            + audio.getTitle() + " | " + audio.getGenre().name() + " | "
                            + DateValidator.dateToString(audio.getPublishDate()) + "\n");
                break;
            case 'G':
                sb.append("Filter results by Gener:" + key + " (AudioID | Title | Date)\n");
                for (Audio audio : audios)
                    sb.append("    " + String.format("%0" + digits + "d", counter++) + ") " + audio.getAudioID() + " | "
                            + audio.getTitle() + " | " + DateValidator.dateToString(audio.getPublishDate()) + "\n");
                break;
            case 'D':
                sb.append("Filter results by Date:" + key + " (AudioID | Title | Gener)\n");
                for (Audio audio : audios)
                    sb.append("    " + String.format("%0" + digits + "d", counter++) + ") " + audio.getAudioID() + " | "
                            + audio.getTitle() + " | " + audio.getGenre().name() + "\n");
                break;
        }

        return sb.toString();

    }

    public static String filterInGivenDateRangeString(String begin, String end) {
        String error1 = DateValidator.isValid(begin);
        String error2 = DateValidator.isValid(end);

        if (error1 != null)
            return error1;
        else if (error2 != null)
            return error2;

        List<Audio> audios = filter(DateValidator.stringToDate(begin), DateValidator.stringToDate(end));
        int length = audios.size();

        if (length == 0)
            return "There is no published audio in the given range " + begin + " - " + end;

        int counter = 1;
        int digits = String.valueOf(length).length();
        StringBuilder sb = new StringBuilder();
        sb.append("Filter results between given range:" + begin + " - " + end
                + " (AudioID | Publish Date | Title | Gener)\n");
        for (Audio audio : audios)
            sb.append("    " + String.format("%0" + digits + "d", counter++) + ") " + audio.getAudioID() + " | "
                    + " | " + DateValidator.dateToString(audio.getPublishDate()) + " | " + audio.getTitle() + " | "
                    + audio.getGenre().name() + " | " + "\n");

        return sb.toString();
    }

    private static Playlist getPlaylist(String name) {
        for (Playlist playlist : getListener().getPlaylists()) {
            if (playlist.getName().equals(name)) {
                return playlist;
            }
        }
        return null;
    }

    private static Playlist getOrCreatePlaylist(String name) {

        Playlist playlist = getPlaylist(name);
        if (playlist != null)
            return playlist;

        Listener listener = getListener();

        if (listener instanceof BasicListener && listener.getPlaylists().size() >= 3)
            return null; // in case Basic user has no permission to create more that 3 playlists

        playlist = new Playlist(name, getListener().getUserID());
        listener.getPlaylists().add(playlist);
        return playlist;
    }

    public static String AddMusicToPlaylist(String playlistName, String audioID) {

        Audio audio = AdminController.getAudio(audioID);
        if (audio == null)
            return "There is no audio with that ID";

        Playlist playlist = getOrCreatePlaylist(playlistName);

        if (playlist == null)
            return "You need to be Premium to have more playlists!";

        if (getListener() instanceof BasicListener && playlist.getAudios().size() >= 10)
            return "You need to be Premium to add more audios in a playlist, current audios: "
                    + playlist.getAudios().size();

        playlist.getAudios().add(audio);
        return "Audio with ID " + audioID + " successfuly added to playlist " + playlistName;
    }

    public static String getPlaylistString(String name) {
        Playlist playlist = getPlaylist(name);
        if (playlist == null)
            return "You have no playlist with this name!";

        int length = playlist.getAudios().size();
        int counter = 1;
        int digits = String.valueOf(length).length();

        StringBuilder sb = new StringBuilder(
                "Playlist " + playlist.getName() + " (AudioID | Name)\n");
        if (!playlist.getDescription().equals(""))
            sb.append("Description: " + playlist.getDescription() + "\n");

        for (Audio audio : playlist.getAudios())
            sb.append("    " + String.format("%0" + digits + "d", counter++) + ") " + audio.getAudioID() + " | "
                    + audio.getTitle() + "\n");

        return sb.toString();
    }

    public static String getPlaylistsString() {

        int length = getListener().getPlaylists().size();
        if (length == 0)
            return "You have no playlist!";

        int counter = 1;
        int digits = String.valueOf(length).length();
        StringBuilder sb = new StringBuilder("Your Playlists:\n");
        for (Playlist playlist : getListener().getPlaylists())
            sb.append("    " + String.format("%0" + digits + "d", counter++) + ") " + playlist.getName() + "\n");

        return sb.toString();
    }

    public static String addPlaylistString(String name) {
        Playlist playlist = new Playlist(name, getListener().getUserID());
        getListener().getPlaylists().add(playlist);
        return "The playlist " + name + " has successfuly created!";
    }

    public static String like(String audioID) {
        Audio audio = AdminController.getAudio(audioID);
        if (audio == null)
            return "There is no Audio with that audioID!";

        if (getListener().getLikedAudios().contains(audio.getAudioID()))
            return "You already liked this audio before!!";

        getListener().getLikedAudios().add(audioID);
        audio.increaseLike();
        return "You liked Audio (" + audioID + ")";
    }

    public static String play(String audioID) {
        Audio audio = AdminController.getAudio(audioID);
        if (audio == null)
            return "There is no Audio with that audioID!";

        int value = getListener().getPlayedTimes().getOrDefault(audio, 0);
        getListener().getPlayedTimes().put(audioID, value + 1);
        audio.increasePlayTime();

        return "You played Audio (" + audioID + ")";
    }

    public static String text(String audioID) {
        Audio audio = AdminController.getAudio(audioID);
        if (audio == null)
            return "There is no Audio with that audioID!";

        if (audio instanceof Music) {
            Music music = (Music) audio;
            return music.getLyrics();
        }

        Podcast podcast = (Podcast) audio;
        return podcast.getTranscript();
    }

    public static String followings() {

        int length = getListener().getFollowings().size();
        if (length == 0)
            return "You are not following any artist!";

        int counter = 1;
        int digits = String.valueOf(length).length();
        StringBuilder sb = new StringBuilder("Your followings (Username | FullName):\n");
        for (User user : Database.getDB().getUsers()) {
            if (getListener().getFollowings().contains(user.getUserID())) {
                sb.append("    " + String.format("%0" + digits + "d", counter++) + ") " + user.getUsername() + " | "
                        + user.getFullName() + "\n");

                if (counter > length)
                    break;
            }
        }
        return sb.toString();
    }

    public static String addReport(String artistID, String description) {

        Artist artist = AdminController.getArtist(artistID);

        if (artist == null)
            return "There is no Artist with that artistID!";

        User user = getListener();
        Report report = new Report(user, artist, description);
        Database.getDB().getReports().add(report);
        return "Your report has been successfully submitted";
    }

    public static String increaseCredit(String n) {
        try {
            double value = Double.parseDouble(n);
            getListener().increaseCredit(value);
            return "Your account has been charged successfully, your new balance: " + getListener().getCredit();

        } catch (Exception e) {
            return "This is not a valid number!";
        }
    }

    private static void createPremium(BasicListener listener, int days) {
        PremiumListener premiumUser = new PremiumListener(listener.getUsername(), listener.getPassword(),
                listener.getFirstName(), listener.getLastName(), listener.getEmail(), listener.getPhone(),
                listener.getBirthDate(), days);
        premiumUser.setUserID(listener.getUserID());
        premiumUser.setJoinDate(listener.getJoinDate());
        premiumUser.setFavoriteGenres(listener.getFavoriteGenres());
        premiumUser.setFollowings(listener.getFollowings());
        premiumUser.setLikedAudios(listener.getLikedAudios());
        premiumUser.setPlayedTimes(listener.getPlayedTimes());

        Database.getDB().getUsers().remove(listener);
        Database.getDB().getUsers().add(premiumUser);
        CurrentUser.login(premiumUser);
    }

    public static String getPremium(String pkg) {

        Listener listener = getListener();

        if (!(pkg.equals("30") || pkg.equals("60") || pkg.equals("180")))
            return "The package type is not correct! (30 | 60 | 180)";

        double price = PremiumPackage.valueOf("D" + pkg).getValue();
        double credit = listener.getCredit();
        if (credit < price)
            return "Your account balance is insufficient to purchase a " + pkg + "-day premium account!";

        listener.setCredit(credit - price);
        int days = Integer.parseInt(pkg);
        if (listener instanceof PremiumListener) {
            PremiumListener premium = (PremiumListener) listener;
            premium.setLeftDays(premium.getLeftDays() + days);
            return "Your subscription has been increased to " + premium.getLeftDays() + " days!";
        } else {
            createPremium((BasicListener) listener, days);
            return "Your account has been successfully converted to premium for " + days + " days!";
        }
    }

    public static String getSubscription() {
        if (getListener() instanceof BasicListener)
            return "You do not have a subscription";
        PremiumListener premium = (PremiumListener) getListener();
        int daysLeft = premium.getLeftDays();
        premium.setLeftDays(daysLeft - 1);
        return "Your premium subscription has " + (daysLeft - 1) + " days left!";
    }

    private static List<?> search(String query, boolean is_audio) {

        // assuming query at least has two letters to find best results!
        // boolean => True for audios and False for Artists

        return (is_audio) ? Database.getDB().getAudios().stream()
                .filter(audio -> audio.getTitle().contains(query))
                .sorted(Comparator.comparing(Audio::getTitle))
                .collect(Collectors.toList())
                : Database.getDB().getUsers().stream()
                        .filter(user -> user instanceof Artist && user.getFullName().contains(query))
                        .sorted(Comparator.comparing(User::getFullName))
                        .collect(Collectors.toList());
    }

    public static String search(String query) {

        if (query.trim().length() < 2)
            return "Query must have at least 2 length!";

        @SuppressWarnings("unchecked")
        List<Audio> audios = (List<Audio>) search(query, true);
        @SuppressWarnings("unchecked")
        List<Artist> artists = (List<Artist>) search(query, false);

        int len_audios = audios.size();
        int len_artists = artists.size();

        if (len_artists == 0 && len_audios == 0)
            return "There is no matching objects with the query: " + query;

        StringBuilder sb = new StringBuilder("Search results of query: " + query + "\n");

        int counter, digits;

        if (len_audios != 0) {
            sb.append("\n   * Audio results (ID | Title) :\n");
            counter = 0;
            digits = String.valueOf(len_audios).length();
            for (Audio audio : audios)
                sb.append("        " + String.format("%0" + digits + "d", counter++) + ") " + audio.getAudioID() + " | "
                        + audio.getTitle() + "\n");
        }

        if (len_artists != 0) {
            sb.append("\n   * Artist results (Username | FullName) :\n");
            counter = 0;
            digits = String.valueOf(len_artists).length();
            for (Artist artist : artists)
                sb.append("        " + String.format("%0" + digits + "d", counter++) + ") " + artist.getUsername()
                        + " | " + artist.getFullName() + "\n");
        }

        return sb.toString();

    }
}
