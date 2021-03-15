package snake.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import snake.game.Game;

import static javafx.scene.media.AudioClip.INDEFINITE;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 12.03.2021
 */
public class GameController {
    @FXML
    public BorderPane root;

    @FXML
    public Canvas canvas;

    @FXML
    public ImageView viewBackground;

    @FXML
    public Label labelScore;

    public void startGame(boolean insane) {
        Game game = new Game(canvas, viewBackground, insane);
        game.startLoop();

        final double VOLUME = 0.1;

        AudioClip clip;

        if (insane) {
            setInsane();

            clip = new AudioClip(getClass().getResource("../../audio/insane.mp3").toExternalForm());
        } else {
            clip = new AudioClip(getClass().getResource("../../audio/normal.mp3").toExternalForm());
        }

        clip.setCycleCount(INDEFINITE);
        clip.setVolume(VOLUME);
        clip.play();

        labelScore.textProperty().bind(Bindings.format("Score: %-10d", game.getScoreProperty()));
    }

    private void setInsane() {
        ColorInput color = new ColorInput();
        color.setPaint(Color.WHITE);
        color.setWidth(Double.MAX_VALUE);
        color.setHeight(Double.MAX_VALUE);

        Blend blend = new Blend(BlendMode.DIFFERENCE);
        blend.setBottomInput(color);

        root.setEffect(blend);
    }
}
