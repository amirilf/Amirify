package app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract public class Listener extends User {

    // key is Audio unique ID
    private Map<String, Integer> playedTimes = new HashMap<>();
    private ArrayList<String> likedAudios = new ArrayList<>();
    private ArrayList<String> followings = new ArrayList<>();
    private double credit;
    private ArrayList<Genre> favoriteGenres = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList<>();

    Listener(String username, String password, String firstName, String lastName, String email, String phone,
            LocalDate birthDate) {
        super(username, password, firstName, lastName, email, phone, birthDate);
        this.credit = 50;
    }

    // GETTER
    public Map<String, Integer> getPlayedTimes() {
        return this.playedTimes;
    }

    public ArrayList<String> getFollowings() {
        return this.followings;
    }

    public double getCredit() {
        return this.credit;
    }

    public ArrayList<Genre> getFavoriteGenres() {
        return this.favoriteGenres;
    }

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }

    public ArrayList<String> getLikedAudios() {
        return this.likedAudios;
    }

    // SETTER
    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setFavoriteGenres(ArrayList<Genre> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public void setFollowings(ArrayList<String> followings) {
        this.followings = followings;
    }

    public void setLikedAudios(ArrayList<String> likedAudios) {
        this.likedAudios = likedAudios;
    }

    public void setPlayedTimes(Map<String, Integer> playedTimes) {
        this.playedTimes = playedTimes;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    // OTHER
    public void increaseCredit(double value) {
        this.credit += value;
    }

}
