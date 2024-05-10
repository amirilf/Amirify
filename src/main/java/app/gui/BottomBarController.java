package app.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.Timeline;

import java.util.Random;

import app.util.Variables;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class BottomBarController {

    // icon objects
    @FXML
    private ImageView cover;
    // later we remove this & instead we add media cover from db, it's just testing
    private Image img_cover = new Image(getClass().getResource("/app/media/cover.png").toString());

    @FXML
    private ImageView playPause;
    private Image img_playPause = new Image(getClass().getResource(Variables.playIconPath).toString());

    @FXML
    private ImageView repeat;
    private Image img_repeat = new Image(getClass().getResource(Variables.repeatOffIconPath).toString());

    @FXML
    private ImageView shuffle;
    private Image img_shuffle = new Image(getClass().getResource(Variables.shuffleOffIconPath).toString());

    @FXML
    private ImageView next;
    @FXML
    private ImageView previous;

    // same for both next and previous, one of them is rotated
    private Image img_next = new Image(getClass().getResource(Variables.nextIconPath).toString());

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label startTimeLabel;

    @FXML
    private Slider progressSlider;

    @FXML
    private Label endTimeLabel;

    MediaPlayer mediaPlayer;
    private Timeline timeline = new Timeline();
    private boolean isPlaying = false;
    private boolean isRepeat = false;
    private boolean isShuffle = false;
    private DoubleProperty currentDurationProperty = new SimpleDoubleProperty(0);
    private DoubleProperty totalDurationProperty = new SimpleDoubleProperty(0);

    // just testing items similar to playlist situation
    String link1 = "/app/media/Love Yourself.mp3";
    String link2 = "/app/media/Adele - Hello.mp3";
    String link3 = "/app/media/Jawny - adios.mp3";
    String link4 = "/app/media/Trevor Daniel - Falling.mp3";
    String links[] = { link1, link2, link3, link4 };
    int index = 0;
    int mediaLength = links.length;

    @FXML
    private void initialize() {

        // create the first media object
        setMediaSource(links[index]);

        // set current and max properties of the progress slider
        progressSlider.valueProperty().bindBidirectional(currentDurationProperty);
        progressSlider.maxProperty().bind(totalDurationProperty);

        // sync slider cursor and media play time in these two functions
        // first one works when user select and move the cursor
        // second one works when user only clicks somewhere else in the proggres slider
        // check isPlaying bcs we shouldn't play media when it's paused!
        progressSlider.setOnMouseDragged(event -> {
            if (isPlaying) {
                playMedia(currentDurationProperty.get());
            }
        });

        progressSlider.setOnMouseClicked(event -> {
            if (!progressSlider.isValueChanging() && isPlaying) {
                playMedia(currentDurationProperty.get());
            }
        });

        // add bindings for start and end time labels
        startTimeLabel.textProperty().bind(Bindings
                .createStringBinding(() -> formatDuration(currentDurationProperty.get()), currentDurationProperty));
        endTimeLabel.textProperty().bind(
                Bindings.createStringBinding(() -> formatDuration(totalDurationProperty.get()), totalDurationProperty));

        // starting playback method
        mediaPlayBack();

        // ============= Adding CSS
        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.bottombarCSSPath).toString());
        // =============

        // ============= Adding images
        cover.setImage(img_cover);
        playPause.setImage(img_playPause);
        next.setImage(img_next);
        previous.setImage(img_next);
        repeat.setImage(img_repeat);
        shuffle.setImage(img_shuffle);
        // =============
    }

    private String formatDuration(double seconds) {

        // create well duration format for using in media duration time labels

        int minutes = (int) (seconds / 60);
        int remainingSeconds = (int) (seconds % 60);
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    @FXML
    private void handlePlayPauseClicked() {

        // change icons, play and pause media

        if (isPlaying) {
            setImage(playPause, Variables.playIconPath);
            mediaPlayer.pause();
        } else {
            setImage(playPause, Variables.pauseIconPath);
            if (currentDurationProperty.get() >= totalDurationProperty.get()) {
                currentDurationProperty.set(0);
            }
            playMedia(currentDurationProperty.get());
            timeline.play();
        }
        isPlaying = !isPlaying;
    }

    @FXML
    private void handleNextClick() {

        if (isShuffle) {
            setMediaSource(shufflePath(mediaLength));
        } else {

            // if the list is done then start again from the first item!

            if (index == links.length - 1) {
                index = 0;
            } else {
                index++;
            }
            setMediaSource(links[index]);
        }

    }

    @FXML
    private void handlePreviousClick() {

        if (isShuffle) {
            setMediaSource(shufflePath(mediaLength));
        } else {

            // if it's the first item then start from current time zero of that item!

            if (index == 0) {
                currentDurationProperty.set(0);
                playMedia(0d);
            } else {
                index--;
                setMediaSource(links[index]);
            }
        }

    }

    @FXML
    private void handleRepeatClick() {

        // enable and disable the repeat feature and also change the icon

        isRepeat = !isRepeat;
        if (isRepeat) {
            repeat.setImage(new Image(getClass().getResourceAsStream(Variables.repeatOnIconPath)));
        } else {
            repeat.setImage(new Image(getClass().getResourceAsStream(Variables.repeatOffIconPath)));
        }
    }

    @FXML
    private void handleShuffleClick() {

        // enable and disable the shuffle feature and also change the icon

        isShuffle = !isShuffle;
        if (isShuffle) {
            shuffle.setImage(new Image(getClass().getResourceAsStream(Variables.shuffleOnIconPath)));
        } else {
            shuffle.setImage(new Image(getClass().getResourceAsStream(Variables.shuffleOffIconPath)));
        }
    }

    private String shufflePath(int size) {

        // size => size of playlist

        index = new Random().nextInt(size);
        return links[index];

    }

    private void setImage(ImageView imageView, String imageUrl) {

        // get imageView obj and change the icon

        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        imageView.setImage(image);
    }

    private void mediaPlayBack() {

        // start main timeline

        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            if (isPlaying) {
                                currentDurationProperty.set(currentDurationProperty.get() + 1);

                                if (currentDurationProperty.get() >= totalDurationProperty.get()) {
                                    if (isRepeat) {
                                        currentDurationProperty.set(0);
                                        playMedia(0d);
                                    } else {

                                        // no repeating

                                        if (index == mediaLength - 1) {

                                            // this was the last media in list so we pause it
                                            isPlaying = false;
                                            setImage(playPause, Variables.playIconPath);
                                            timeline.pause();
                                            mediaPlayer.pause();

                                        } else {
                                            // we have still media in list so we play next one
                                            handleNextClick();
                                        }
                                    }
                                }
                            }
                        }));

        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void playMedia(Double time) {

        // first seeking the desired time in media and play it

        mediaPlayer.seek(Duration.seconds(time));
        mediaPlayer.play();
    }

    private void setMediaSource(String path) {

        // first stop the last one if it's not null
        // get the resource and replace (or place if it's already null) the source
        // update total time and current time
        // play if isPlaying is true and do nothing if it's not

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer(
                new Media(getClass().getResource(path).toString()));
        mediaPlayer.setOnReady(() -> {
            totalDurationProperty.set(mediaPlayer.getTotalDuration().toSeconds());
            currentDurationProperty.set(0);
        });

        if (isPlaying) {
            mediaPlayer.play();
        }
    }
}
