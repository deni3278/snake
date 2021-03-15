package snake.game;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static javafx.animation.Animation.INDEFINITE;
import static javafx.animation.Animation.Status.RUNNING;

/**
 * Models a single Snake game.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
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

    /**
     * Initializes a new Game instance with a specified canvas to draw onto and a specified difficulty.
     *
     * @param canvas the canvas that is being drawn onto.
     * @param view the image view that is layered behind the canvas for insane difficulty.
     * @param insane whether the game should be set to insane difficulty.
     */
    public Game(Canvas canvas, ImageView view, boolean insane) {
        width = canvas.getWidth();
        height = canvas.getWidth();

        state = new State(this);
        renderer = new Renderer(canvas.getGraphicsContext2D());

        canvas.setFocusTraversable(true);   // Allows the KeyHandler to listen for input on the canvas
        canvas.setOnKeyPressed(new KeyHandler(state.getSnake()));

        loop.setCycleCount(INDEFINITE);

        if (insane) {
            setInsane(canvas, view);
        }
    }

    /**
     * Starts the game loop with normal speed.
     */
    public void startLoop() {
        startLoop(GameSpeed.NORMAL);
    }

    /**
     * Starts the game loop with a specified speed.
     *
     * @param speed the speed the game loop should run at.
     */
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

    /**
     * Returns an image from the resources folder with the specified name and extension.
     *
     * @param name the name (including extension) of the image.
     * @return an {@code Image} object with the specified image.
     */
    public static Image getImage(String name) {
        return new Image(Game.class.getResource(IMAGES_PATH + name).toExternalForm());
    }

    /**
     * Returns the width of the canvas that is being drawn onto.
     *
     * @return the width of the canvas as a double.
     */
    public static double getWidth() {
        return width;
    }

    /**
     * Returns the height of the canvas that is being drawn onto.
     *
     * @return the height of the canvas as a double.
     */
    public static double getHeight() {
        return height;
    }

    /**
     * Returns the score property, which is incremented every time the player snake eats a food object.
     *
     * @return the score property.
     */
    public IntegerProperty getScoreProperty() {
        return state.getScoreProperty();
    }

    private void setInsane(Canvas canvas, ImageView view) {
        final int ROTATE_SCORE = 5;             // The amount of points the player needs before the canvas starts rotating
        final double ROTATE_DURATION = 0.5;     // The duration in seconds that the rotation transition takes
        final double ROTATE_ANGLE = 90.0;       // The rotation angle of the canvas' rotation

        getScoreProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() % ROTATE_SCORE == 0) {
                RotateTransition transition = new RotateTransition(Duration.seconds(ROTATE_DURATION));
                transition.setNode(canvas);
                transition.setFromAngle(canvas.getRotate());
                transition.setToAngle(canvas.getRotate() + ROTATE_ANGLE);
                transition.play();

                if (view.getImage() == null) {
                    view.setImage(getImage("insane.gif"));
                }
            }
        });
    }
}