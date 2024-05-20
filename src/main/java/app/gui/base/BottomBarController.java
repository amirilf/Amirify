package app.gui.base;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Random;

import app.controller.auth.CurrentData;
import app.gui.page.ArtistController;
import app.gui.page.AudioController;
import app.model.Audio;
import app.util.Variables;

public class BottomBarController {

    // TODO : after loggin out it's still playling!!!

    @FXML
    private ImageView cover;
    @FXML
    private ImageView playPause;
    @FXML
    private ImageView repeat;
    @FXML
    private ImageView shuffle;
    @FXML
    private ImageView next;
    @FXML
    private ImageView previous;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Slider progressSlider;
    @FXML
    private Label endTimeLabel;
    @FXML
    private Label title;
    @FXML
    private Label artist;

    private MediaPlayer mediaPlayer;
    private Timeline timeline = new Timeline();
    private boolean isPlaying = false;
    private boolean isRepeat = false;
    private boolean isShuffle = false;
    private DoubleProperty currentDurationProperty = new SimpleDoubleProperty(0);
    private DoubleProperty totalDurationProperty = new SimpleDoubleProperty(0);

    private static final String DEFAULT_COVER_PATH = "/app/images/default-cover.png";
    private static final String DEFAULT_LABEL = "-----";

    @FXML
    private void initialize() {

        // default values | clear old playlist
        setDefaultValues();
        CurrentData.getCurrentAudio().addListener(new ChangeListener<Audio>() {
            @Override
            public void changed(ObservableValue<? extends Audio> observable, Audio oldValue, Audio newValue) {
                setMediaSource(newValue);
            }
        });

        progressSlider.valueProperty().bindBidirectional(currentDurationProperty);
        progressSlider.maxProperty().bind(totalDurationProperty);
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

        startTimeLabel.textProperty().bind(Bindings.createStringBinding(
                () -> formatDuration(currentDurationProperty.get()), currentDurationProperty));
        endTimeLabel.textProperty().bind(Bindings.createStringBinding(
                () -> formatDuration(totalDurationProperty.get()), totalDurationProperty));

        mediaPlayBack();

        mainAnchorPane.getStylesheets().addAll(getClass().getResource(Variables.bottombarCSSPath).toString());

    }

    private String formatDuration(double seconds) {
        int minutes = (int) (seconds / 60);
        int remainingSeconds = (int) (seconds % 60);
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    @FXML
    private void handlePlayPauseClicked() {
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
            shuffleIndex();
        } else {
            if (CurrentData.isNext()) {
                CurrentData.setSelectedIndex(CurrentData.getSelectedIndex() + 1);
            } else {
                CurrentData.setSelectedIndex(0);
            }
        }
    }

    @FXML
    private void handlePreviousClick() {
        if (isShuffle) {
            shuffleIndex();
        } else {
            if (CurrentData.isPrev()) {
                CurrentData.setSelectedIndex(CurrentData.getSelectedIndex() - 1);
            } else {
                // start from 00:00 of the current audio
                currentDurationProperty.set(0);
                playMedia(0d);
            }
        }
    }

    @FXML
    private void handleRepeatClick() {
        isRepeat = !isRepeat;
        setImage(repeat, isRepeat ? Variables.repeatOnIconPath : Variables.repeatOffIconPath);
    }

    @FXML
    private void handleShuffleClick() {
        isShuffle = !isShuffle;
        setImage(shuffle, isShuffle ? Variables.shuffleOnIconPath : Variables.shuffleOffIconPath);
    }

    private void shuffleIndex() {
        CurrentData.setSelectedIndex(new Random().nextInt(CurrentData.playlist.size()));
    }

    private void setImage(ImageView imageView, String imageUrl) {
        Image image = new Image(getClass().getResource(imageUrl).toString());
        imageView.setImage(image);
    }

    private void mediaPlayBack() {
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (isPlaying) {
                        currentDurationProperty.set(currentDurationProperty.get() + 1);
                        if (currentDurationProperty.get() >= totalDurationProperty.get()) {
                            if (isRepeat) {
                                currentDurationProperty.set(0);
                                playMedia(0d);
                            } else {
                                handleNextClick();
                            }
                        }
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void playMedia(Double time) {
        mediaPlayer.seek(Duration.seconds(time));
        mediaPlayer.play();
    }

    private void setMediaSource(Audio audio) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (audio != null) {
            mediaPlayer = new MediaPlayer(new Media(getClass().getResource(audio.getLink()).toString()));
            title.setText(audio.getTitle());
            artist.setText(CurrentData.getArtist(audio.getUserID()).getFullName());
            setImage(cover, audio.getCover());

            mediaPlayer.setOnReady(() -> {
                totalDurationProperty.set(mediaPlayer.getTotalDuration().toSeconds());
                currentDurationProperty.set(0);
            });

            if (isPlaying) {
                mediaPlayer.play();
            }

            playPause.setDisable(false);
            next.setDisable(!CurrentData.isNext());
            previous.setDisable(!CurrentData.isPrev());
            title.setDisable(false);
            artist.setDisable(false);

        } else {
            setDefaultValues();
            mediaPlayer.stop();
            isPlaying = false;
        }
    }

    private void setDefaultValues() {

        setImage(cover, DEFAULT_COVER_PATH);
        setImage(playPause, Variables.playIconPath);
        setImage(next, Variables.nextIconPath);
        setImage(previous, Variables.nextIconPath);
        setImage(shuffle, Variables.shuffleOffIconPath);
        setImage(repeat, Variables.repeatOffIconPath);

        title.setText(DEFAULT_LABEL);
        artist.setText(DEFAULT_LABEL);

        playPause.setDisable(true);
        next.setDisable(true);
        previous.setDisable(true);
        title.setDisable(true);
        artist.setDisable(true);
    }

    // mouse clicked on Artist name or Audio name

    @FXML
    private void handleArtistClick() {
        System.out.println("Artist clicked");

        ArtistController.name = artist.getText();

        if (BodyController.getContentPath().get().equals("Artist"))
            // user was already in Artist page and clicked on another Artist in Playin bar
            BodyController.setFxmlPath("");
        BodyController.setFxmlPath("Artist");
    }

    @FXML
    private void handleAudioClick() {
        System.out.println("Audio clicked");

        AudioController.name = title.getText();

        if (BodyController.getContentPath().get().equals("Audio")) {
            // user was already in Audio page and clicked on another Audio in Playin bar
            BodyController.setFxmlPath("");
        }
        BodyController.setFxmlPath("Audio");
    }

}