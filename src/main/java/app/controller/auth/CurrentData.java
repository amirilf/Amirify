package app.controller.auth;

import java.util.ArrayList;
import java.util.List;

import app.model.Artist;
import app.model.Audio;
import app.model.Database;
import app.model.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CurrentData {

    // ===================== playlist

    public static ArrayList<Audio> playlist = new ArrayList<>();
    private static int selectedIndex = -1; // no selected item now
    private static ObjectProperty<Audio> currentAudio = new SimpleObjectProperty<>();

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

    // TODO: later make <String> instead of <Audio> in playlist
    // TODO: and don't save the Audio objects!
    public static void setNewPlaylist(ArrayList<Audio> newPlaylist, int index) {
        playlist.clear();
        playlist.addAll(newPlaylist);
        setSelectedIndex(index);
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
        currentAudio.set(null);
    }

    public static Artist getArtist(String userID) {
        for (User user : Database.getDB().getUsers()) {
            if (user.getUserID().equals(userID)) {
                return (Artist) user;
            }
        }
        return null;
    }

    // ===================== history

    /*
     * Pages that have meta data
     * 1) Artist -> artistID
     * 2) Audio -> audioID
     * 3) Report -> artistID
     * 4) Playlist -> artistID - albumID (listenerID - playlistID)
     * 5) Genre -> genreName
     * 6) Result -> query
     */

    private static List<List<String>> history = new ArrayList<>();
    public static IntegerProperty pageIndex = new SimpleIntegerProperty(-1);
    public static List<String> currentPage = new ArrayList<>();

    public static void updatePageIndex(int number) {
        pageIndex.set(number);
        currentPage = history.get(pageIndex.get());
    }

    public static void addHistory(List<String> page) {

        if (history.size() > pageIndex.get() + 1) {

            history.set(pageIndex.get() + 1, page);

            List<List<String>> sub = new ArrayList<>(history.subList(0, pageIndex.get() + 2));
            history.clear();
            history.addAll(sub);

            System.out.println(history.toString());

            // 1 -> 2 -> 3 -> 4 -> 5 -> 6
            // ..........3 <- 4 <- 5 <- |
            // ..........| -> 7
            // Then the new list would be 1,2,3,7 and not 1,2,3,7,5,6! and that's what we do
            // here, if user clicks on back button so many times and again goes to a new
            // page, then all the next pages must be removed since he chose a new journey (:
        } else {
            System.out.println("Added " + page);
            history.add(page);
        }

        updatePageIndex(pageIndex.get() + 1);
    }

    public static List<String> getCurrentPage() {
        return history.get(pageIndex.get());
    }

    public static List<String> getPrevHistory() {
        updatePageIndex(pageIndex.get() - 1);
        return history.get(pageIndex.get());
    }

    public static List<String> getNextHistory() {
        // we assume that we already checked (isNextPage) in our gui and
        // made the next button disable if there is no next page! and now we are certain
        // if this method is called then of course isNextPage is true!
        updatePageIndex(pageIndex.get() + 1);
        return history.get(pageIndex.get());
    }

    public static boolean isNextPage() {
        return pageIndex.get() < history.size() - 1;
    }

    public static boolean isPrevPage() {
        return pageIndex.get() > 0;
    }

}
