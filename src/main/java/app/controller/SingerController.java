package app.controller;

import java.util.List;

import app.controller.auth.CurrentUser;
import app.model.Album;
import app.model.Database;
import app.model.Genre;
import app.model.Music;
import app.model.Singer;

public class SingerController {

    private static Singer getSinger() {
        return (Singer) CurrentUser.getUser();
    }

    public static Album getAlbum(String albumID) {
        for (Album album : getSinger().getAlbums())
            if (album.getAlbumID().equals(albumID))
                return album;
        return null;
    }

    public static List<Album> getAlbums(String singerID) {
        Singer singer = (Singer) AdminController.getArtistByUserID(singerID);
        return singer.getAlbums();
    }

    public static List<Album> getAlbums(String singerID, int number) {

        // return n last album

        List<Album> lastAlbums = getAlbums(singerID);
        int size = lastAlbums.size();
        return size <= number ? lastAlbums : lastAlbums.subList(size - number, size);
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

    public static Album addAlbum(String name, String cover) {
        Singer singer = getSinger();
        Album album = new Album(name, singer.getUserID(), cover);
        singer.getAlbums().add(album);
        return album;
    }

    public static Music addMusic(String title, String genre, String lyrics, String link,
            String albumID) {

        // check genre exists
        Genre genreOBJ = Genre.valueOf(genre);

        // check album exists
        Album album = getAlbum(albumID);

        // TODO : make validation ok using exceptions
        // if (!LinkValidator.isValid(link))
        // return LinkValidator.ERROR;

        Music music = new Music(title, getSinger().getUserID(), genreOBJ, link,
                album.getCover(), lyrics);
        album.getMusics().add(music);
        Database.getDB().getAudios().add(music);
        return music;

    }

}
