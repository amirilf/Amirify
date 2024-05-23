package app.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import app.controller.auth.CurrentUser;
import app.model.Album;
import app.model.Audio;
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

    public static List<Audio> getSimilarAudios(Audio audio, int n) {

        // return n similar audios based on Genre and Artist

        Stream<Audio> audioStream = Database.getDB().getAudios().stream()
                .filter(a -> (a.getGenre().equals(audio.getGenre()) || (a.getUserID().equals(audio.getUserID())))
                        && !a.equals(audio));
        List<Audio> similars = audioStream.collect(Collectors.toList());
        Collections.shuffle(similars);
        return similars.subList(0, Math.min(similars.size(), n));
    }

    public static Album getAlbumByAudioID(String singerID, String audioID) {

        Singer singer = (Singer) AdminController.getArtistByUserID(singerID);

        for (Album album : singer.getAlbums()) {
            for (Music music : album.getMusics()) {
                if (music.getAudioID().equals(audioID)) {
                    return album;
                }
            }
        }

        return null;
    }

    public static Album getAlbum(String albumID, String singerID) {
        Singer singer = (Singer) AdminController.getArtistByUserID(singerID);
        for (Album album : singer.getAlbums())
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
