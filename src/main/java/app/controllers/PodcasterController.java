package app.controllers;

import app.controllers.auth.CurrentUser;
import app.models.Database;
import app.models.Genre;
import app.models.Podcast;
import app.models.Podcaster;
import app.utility.validators.LinkValidator;

public class PodcasterController {

    private static Podcaster getPodcaster() {
        return (Podcaster) CurrentUser.getUser();
    }

    public static String addPodcast(String title, String genre, String transcript, String link, String cover) {

        Podcaster podcaster = getPodcaster();

        Genre genreOBJ;
        try {
            genreOBJ = Genre.valueOf(genre);
        } catch (Exception e) {
            return "Gener is not valid! see Help for more";
        }

        if (!LinkValidator.isValid(link))
            return LinkValidator.ERROR;

        Podcast podcast = new Podcast(title, podcaster.getUserID(), genreOBJ,
                link, cover, transcript);
        podcaster.getPodcasts().add(podcast);
        Database.getDB().getAudios().add(podcast);
        return "Podcast " + podcast.getTitle() + " (ID: " + podcast.getAudioID() + ")"
                + " successfuly uploaded by you!";
    }

}
