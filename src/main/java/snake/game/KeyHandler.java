package snake.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Handles the player's keyboard input.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
 * @version 0.0.1
 * @since 14.03.2021
 */
public class KeyHandler implements EventHandler<KeyEvent> {
    private final Snake snake;

    /**
     * Initializes a new KeyHandler that alters the specified Snake object.
     *
     * @param snake the Snake object to change direction of.
     */
    public KeyHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case KP_UP:
            case UP:
                if (snake.getLastDirection() != Direction.DOWN) {
                    snake.setCurrentDirection(Direction.UP);
                }

                break;
            case D:
            case KP_RIGHT:
            case RIGHT:
                if (snake.getLastDirection() != Direction.LEFT) {
                    snake.setCurrentDirection(Direction.RIGHT);
                }

                break;
            case S:
            case KP_DOWN:
            case DOWN:
                if (snake.getLastDirection() != Direction.UP) {
                    snake.setCurrentDirection(Direction.DOWN);
                }

                break;
            case A:
            case KP_LEFT:
            case LEFT:
                if (snake.getLastDirection() != Direction.RIGHT) {
                    snake.setCurrentDirection(Direction.LEFT);
                }

                break;
        }
    }
}
