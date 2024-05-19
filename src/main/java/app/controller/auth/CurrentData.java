package app.controller.auth;

import java.util.ArrayList;
import app.model.Artist;
import app.model.Audio;
import app.model.Database;
import app.model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CurrentData {

    private static String search = "";
    public static ArrayList<Audio> playlist = new ArrayList<>();
    private static int selectedIndex = -1; // no selected item now
    private static ObjectProperty<Audio> currentAudio = new SimpleObjectProperty<>();

    public static void setSearch(String search) {
        CurrentData.search = search;
    }

    public static String getSearch() {
        return search;
    }

    public static int getSelectedIndex() {
        return selectedIndex;
    }

    public static void setSelectedIndex(int index) {
        if (index >= 0 && index < playlist.size()) {
            selectedIndex = index;

            // update audio object
            currentAudio.set(playlist.get(selectedIndex));
        }
    }

    public static ObjectProperty<Audio> getCurrentAudio() {
        return currentAudio;
    }

    public static boolean isPrev() {
        return selectedIndex > 0;
    }

    public static boolean isNext() {
        return selectedIndex < playlist.size() - 1;
    }

    public static void clearPlaylist() {
        playlist.clear();
        selectedIndex = -1;
        currentAudio = null;
    }

    public static Artist getArtist(String userID) {
        for (User user : Database.getDB().getUsers()) {
            if (user.getUserID().equals(userID)) {
                return (Artist) user;
            }
        }
        return null;
    }
}
