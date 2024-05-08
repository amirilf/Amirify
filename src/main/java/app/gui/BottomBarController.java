package app.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class BottomBarController {

    String playIconPath = "/app/images/icon-play.png";
    String pauseIconPath = "/app/images/icon-pause.png";

    @FXML
    private Label startTimeLabel;

    @FXML
    private Slider progressSlider;

    @FXML
    private Label endTimeLabel;

    @FXML
    private ImageView nextButton;

    @FXML
    private ImageView playPauseButton;

    private boolean isPlaying = false;

    private DoubleProperty currentDurationProperty = new SimpleDoubleProperty(0);
    private DoubleProperty totalDurationProperty = new SimpleDoubleProperty(200);

    @FXML
    private void initialize() {
        playPauseButton.setOnMouseClicked(event -> handlePlayPauseClicked());

        progressSlider.valueProperty().bindBidirectional(currentDurationProperty);
        progressSlider.maxProperty().bind(totalDurationProperty);

        startTimeLabel.textProperty().bind(Bindings
                .createStringBinding(() -> formatDuration(currentDurationProperty.get()), currentDurationProperty));
        endTimeLabel.textProperty().bind(
                Bindings.createStringBinding(() -> formatDuration(totalDurationProperty.get()), totalDurationProperty));

        simulateMusicPlayback();
    }

    private String formatDuration(double seconds) {
        int minutes = (int) (seconds / 60);
        int remainingSeconds = (int) (seconds % 60);
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private void handlePlayPauseClicked() {
        if (isPlaying) {
            setImage(playPauseButton, playIconPath);
        } else {
            setImage(playPauseButton, pauseIconPath);
        }
        isPlaying = !isPlaying;
    }

    private void setImage(ImageView imageView, String imageUrl) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        imageView.setImage(image);
    }

    private void simulateMusicPlayback() {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            if (isPlaying) {
                                currentDurationProperty.set(currentDurationProperty.get() + 1);
                            }
                        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}