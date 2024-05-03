package app.controllers;

import app.controllers.auth.CurrentUser;
import app.models.Album;
import app.models.Database;
import app.models.Genre;
import app.models.Music;
import app.models.Singer;
import app.utility.validators.LinkValidator;

public class SingerController {

    private static Singer getSinger() {
        return (Singer) CurrentUser.getUser();
    }

    private static Album getAlbum(String albumID) {
        for (Album album : getSinger().getAlbums())
            if (album.getAlbumID().equals(albumID))
                return album;
        return null;
    }

    public static String getAlbumsString() {
        int length = getSinger().getAlbums().size();
        if (length == 0)
            return "You have no albums!";

        StringBuilder sb = new StringBuilder("Albums: (ID | Name)\n");
        sb.ensureCapacity(length * 30);
        int counter = 1;
        int digits = String.valueOf(length).length();
        for (Album album : getSinger().getAlbums())
            sb.append("    " + String.format("%0" + digits + "d", counter) + ") " + album.getAlbumID() + " | "
                    + album.getName() + "\n");

        return sb.toString();
    }

    public static String addAlbum(String name) {
        Singer singer = getSinger();
        Album album = new Album(name, singer.getUserID());
        singer.getAlbums().add(album);
        return "Album " + name + " (ID: " + album.getAlbumID() + ") successfully created";
    }

    public static String addMusic(String title, String genre, String lyrics, String link, String cover,
            String albumID) {

        Genre genreOBJ;
        try {
            genreOBJ = Genre.valueOf(genre);
        } catch (Exception e) {
            return "Gener is not valid! see Help for more";
        }

        Album album = getAlbum(albumID);
        if (album == null)
            return "There is no Album with this albumID";

        if (!LinkValidator.isValid(link))
            return LinkValidator.ERROR;

        Music music = new Music(title, getSinger().getUserID(), genreOBJ, link,
                cover, lyrics);
        album.getMusics().add(music);
        Database.getDB().getAudios().add(music);
        return "Music " + music.getTitle() + "(ID : " + music.getAudioID() + ") successfuly added in Album "
                + album.getName() + " by you!";

    }

}
