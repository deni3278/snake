package snake.game;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

import static javafx.animation.Animation.INDEFINITE;
import static javafx.animation.Animation.Status.RUNNING;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 12.03.2021
 */
public class Game implements GameSpeedHandler {
    private static double width;
    private static double height;

    private final State state;
    private final Renderer renderer;
    private final Timeline loop = new Timeline();

    public Game(Canvas canvas, boolean insane) {
        width = canvas.getWidth();
        height = canvas.getWidth();

        state = new State(this);
        renderer = new Renderer(canvas.getGraphicsContext2D());

        loop.setCycleCount(INDEFINITE);

        if (insane) {
            setInsane(canvas);
        }
    }

    public void startLoop() {
        startLoop(GameSpeed.NORMAL);
    }

    @Override
    public void startLoop(GameSpeed speed) {
        if (loop.getStatus().equals(RUNNING)) {
            loop.stop();
        }

        if (loop.getKeyFrames().size() > 0) {
            loop.getKeyFrames().removeAll(loop.getKeyFrames());
        }

        loop.getKeyFrames().add(new KeyFrame(Duration.seconds(speed.value), e -> {
            state.update();
            renderer.draw();
        }));
        loop.play();
    }

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public IntegerProperty getScoreProperty() {
        return state.getScoreProperty();
    }

    private void setInsane(Canvas canvas) {
        final int ROTATE_SCORE = 5;
        final double ROTATE_DURATION = 0.5;
        final double ROTATE_ANGLE = 90.0;

        getScoreProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() % ROTATE_SCORE == 0) {
                RotateTransition transition = new RotateTransition(Duration.seconds(ROTATE_DURATION));
                transition.setFromAngle(canvas.getRotate());
                transition.setToAngle(canvas.getRotate() + ROTATE_ANGLE);
                transition.play();
            }
        });
    }
}