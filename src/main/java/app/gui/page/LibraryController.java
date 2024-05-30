package app.gui.page;

import java.io.IOException;
import java.util.List;

import app.controller.ListenterController;
import app.controller.SingerController;
import app.controller.auth.CurrentUser;
import app.gui.base.BodyController;
import app.gui.partials.CircleItemController;
import app.gui.partials.RectangleItemController;
import app.model.Album;
import app.model.Artist;
import app.model.Listener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LibraryController {

    @FXML
    private VBox mainVBox;

    @FXML
    private VBox likedSongs;

    @FXML
    private Label numberOfSongs;

    @FXML
    private ImageView playMediaIcon;

    @FXML
    private MenuButton menuButton;

    @FXML
    private void initialize() {

        // handle menu
        menuButton.getItems().forEach(item -> item.setOnAction(event -> handleMenuItemAction(item)));
        MenuItem defaultItem = (MenuItem) menuButton.getItems().get(0);
        handleMenuItemAction(defaultItem);

        // handle items in VBOX
        loadItems(SelectedType.ALL);

    }

    private void handleMenuItemAction(MenuItem selected) {
        String selectedText = selected.getText();
        menuButton.setText(selectedText);

        switch (selectedText) {
            case "All":
                loadItems(SelectedType.ALL);
                break;
            case "Albums":
                loadItems(SelectedType.ALBUMS);
                break;
            case "Artists":
                loadItems(SelectedType.ARTISTS);
                break;

            // not enable yet
            case "Playlists":
                loadItems(SelectedType.PLAYLISTS);
                break;
        }
    }

    private void loadItems(SelectedType type) {

        // init stuff
        mainVBox.getChildren().clear();
        likedSongs.setVisible(false);

        Listener listener = (Listener) CurrentUser.getUser();

        HBox hbox = new HBox(40);
        int counter = 1;

        if (type == SelectedType.ALL) {

            List<Album> albums = SingerController.getSavedAlbums(listener.getSavedAlbums());
            List<Artist> artists = ListenterController.getFollowings();

            // TODO : for now, sorry playlists ):
            // List<Playlist> playlists = listener.getPlaylists();

            if (listener.getLikedAudios().size() > 0) {
                System.out.println("HERE!");
                likedSongs.setVisible(true);
                numberOfSongs.setText(listener.getLikedAudios().size() + "");
                hbox.getChildren().add(likedSongs);
                counter++;
            }

            for (Album album : albums) {
                try {

                    // TODO : variable the path
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/app/fxml/partials/rectangle-item.fxml"));
                    VBox albumVBox = loader.load();
                    RectangleItemController controller = loader.getController();
                    String albumType = album.getMusics().size() == 1 ? "Single" : "Album";
                    controller.setArtist(album.getCover(), album.getName(), album.getDatePublished().getYear() + "",
                            albumType, album.getUserID(), album.getAlbumID());
                    hbox.getChildren().add(albumVBox);

                    if (counter % 4 == 0) {
                        mainVBox.getChildren().add(hbox);
                        hbox = new HBox(40);
                    }
                    counter++;

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            for (Artist artist : artists) {

                System.out.println("artist :" + artist.getFullName());
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/circle-item.fxml"));
                    VBox artistVbox = loader.load();
                    CircleItemController controller = loader.getController();

                    controller.setArtist(artist);

                    hbox.getChildren().add(artistVbox);

                    if (counter % 4 == 0) {
                        mainVBox.getChildren().add(hbox);
                        hbox = new HBox(40);
                    }
                    counter++;

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } else if (type == SelectedType.ALBUMS) {

            List<Album> albums = SingerController.getSavedAlbums(listener.getSavedAlbums());

            if (listener.getLikedAudios().size() > 0) {
                System.out.println("HERE!");
                likedSongs.setVisible(true);
                numberOfSongs.setText(listener.getLikedAudios().size() + "");
                hbox.getChildren().add(likedSongs);
                counter++;
            }

            for (Album album : albums) {
                try {

                    // TODO : variable the path
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/app/fxml/partials/rectangle-item.fxml"));
                    VBox albumVBox = loader.load();
                    RectangleItemController controller = loader.getController();
                    String albumType = album.getMusics().size() == 1 ? "Single" : "Album";
                    controller.setArtist(album.getCover(), album.getName(), album.getDatePublished().getYear() + "",
                            albumType, album.getUserID(), album.getAlbumID());
                    hbox.getChildren().add(albumVBox);

                    if (counter % 4 == 0) {
                        mainVBox.getChildren().add(hbox);
                        hbox = new HBox(40);
                    }
                    counter++;

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } else if (type == SelectedType.ARTISTS) {

            List<Artist> artists = ListenterController.getFollowings();

            for (Artist artist : artists) {

                System.out.println("artist :" + artist.getFullName());
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fxml/partials/circle-item.fxml"));
                    VBox artistVbox = loader.load();
                    CircleItemController controller = loader.getController();

                    controller.setArtist(artist);

                    hbox.getChildren().add(artistVbox);

                    if (counter % 4 == 0) {
                        mainVBox.getChildren().add(hbox);
                        hbox = new HBox(40);
                    }
                    counter++;

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } else if (type == SelectedType.PLAYLISTS) {

            System.out.println("PLAYLISTS!");

        }

        // Add the last HBox if it contains any items
        if (!hbox.getChildren().isEmpty()) {
            mainVBox.getChildren().add(hbox);
        }

    }

    enum SelectedType {
        ALL, ARTISTS, ALBUMS, PLAYLISTS
    }

    @FXML
    private void handleBoxClick() {
        BodyController.setFxmlPath(List.of("page/Playlist", "-1", "-1"));
        System.out.println("box clicked");
    }

    @FXML
    private void handlePlayBoxClick(MouseEvent event) {
        PlaylistController.play = true;
        System.out.println("Play clicked!!");
    }

    @FXML
    private void showPlayMedia(MouseEvent event) {
        playMediaIcon.setVisible(true);
    }

    @FXML
    private void hidePlayMedia(MouseEvent event) {
        playMediaIcon.setVisible(false);
    }

}