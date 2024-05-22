package app.util;

import java.io.File;
import java.time.LocalDate;

import app.controller.SingerController;
import app.controller.auth.CurrentUser;
import app.controller.auth.SignUp;

public class LoadAudios {

    public static void loadAudios() {

        // TODO: make path relative and also by using Variables file
        String rootDirPath = "/home/amir/Desktop/code/projects/java/uni/amirify/target/classes/app/media/Songs";
        System.out.println(rootDirPath);
        File rootDir = new File(rootDirPath);

        if (!rootDir.exists() || !rootDir.isDirectory()) {
            System.out.println("Doesn't exists");
            return;
        }

        // get all the dirs inside of Songs
        File[] artistDirs = rootDir.listFiles(File::isDirectory);

        // there is no artist
        if (artistDirs == null) {
            System.out.println("No artist directories found.");
            return;
        }

        int album_counter = 1;
        for (File artistDir : artistDirs) {
            String artistName = artistDir.getName();
            String[] nameParts = artistName.split(" ", 2);
            String firstName = nameParts[0];

            // like for NF he only has first name of "NF" and doesn't have last name 0-0
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            System.out.println("Artist: " + firstName + " " + lastName);

            // it will also login the singer
            SignUp.signUpSinger((firstName + lastName).toLowerCase(), "1234", firstName, lastName,
                    "email@email.com",
                    "09123456789",
                    LocalDate.of(2010, 10, 10));

            // again looking for dirs (albums)

            File[] albumDirs = artistDir.listFiles(File::isDirectory);

            if (albumDirs == null) {
                System.out.println("No albums for the artist: " + artistName);
                continue;
            }

            for (File albumDir : albumDirs) {

                String albumName = albumDir.getName();

                // TODO : variable this
                String coverPath = "/app/media/Songs/" + artistName + "/" + albumName + "/cover.jpg";

                System.out.println("  Album: " + albumName);

                SingerController.addAlbum(albumName, coverPath);

                // get all mp3 files
                File[] audioFiles = albumDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));

                if (audioFiles == null) {
                    System.out.println("  No audio files found in album " + albumName);
                    continue;
                }

                for (File audioFile : audioFiles) {

                    String audioFileName = audioFile.getName();

                    String audioPath = "/app/media/Songs/" + artistName + "/" + albumName + "/"
                            + audioFileName;

                    // audio name are like "Mohsen Chavoshi - Man Bayad Miraftam.mp3"
                    // but we only need the part "Man Bayad Miraftam"
                    String audioName = audioFileName.substring(0, audioFileName.lastIndexOf('.')).split(" - ")[1];

                    System.out.println("    Album id : " + album_counter);
                    SingerController.addMusic(audioName, "Pop", "test", audioPath, (album_counter + ""));

                    System.out.println("    Audio: " + audioName + " added");
                }

                album_counter++;
            }

            // logout the artist
            CurrentUser.logout();
        }
    }
}
