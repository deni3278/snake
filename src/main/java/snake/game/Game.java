package snake.game;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static javafx.animation.Animation.INDEFINITE;
import static javafx.animation.Animation.Status.RUNNING;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 12.03.2021
 */
public class Game implements GameSpeedHandler {
    public static final double CELL = 40.0;                     // Width and height of a cell on the canvas

    private static double width;                                // Width of the canvas
    private static double height;                               // Height of the canvas
    private static final String IMAGES_PATH = "../../images/";  // Relative path to the images resource directory

    private final State state;
    private final Renderer renderer;
    private final Timeline loop = new Timeline();

    public Game(Canvas canvas, boolean insane) {
        width = canvas.getWidth();
        height = canvas.getWidth();

        state = new State(this);
        renderer = new Renderer(canvas.getGraphicsContext2D());

        canvas.setFocusTraversable(true);   // Allows the KeyHandler to listen for input on the canvas
        canvas.setOnKeyPressed(new KeyHandler(state.getSnake()));

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

            if (!state.isGameOver()) {
                renderer.draw(state.getSnake(), state.getFoodObjects());
            } else {
                loop.stop();
            }
        }));
        loop.play();
    }

    public static Image getImage(String name) {
        return new Image(Game.class.getResource(IMAGES_PATH + name).toExternalForm());
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
                transition.setNode(canvas);
                transition.setFromAngle(canvas.getRotate());
                transition.setToAngle(canvas.getRotate() + ROTATE_ANGLE);
                transition.play();
            }
        });
    }
}