package snake.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public class KeyHandler implements EventHandler<KeyEvent> {
    private final Snake snake;

    public KeyHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void handle(KeyEvent event) {
        if (!snake.isMoving()) {
            switch (event.getCode()) {
                case W:
                case KP_UP:
                case UP:
                    if (snake.getDirection() == Direction.LEFT || snake.getDirection() == Direction.RIGHT) {
                        snake.setDirection(Direction.UP);
                    }

                    break;
                case D:
                case KP_RIGHT:
                case RIGHT:
                    if (snake.getDirection() == Direction.UP || snake.getDirection() == Direction.DOWN) {
                        snake.setDirection(Direction.RIGHT);
                    }

                    break;
                case S:
                case KP_DOWN:
                case DOWN:
                    if (snake.getDirection() == Direction.LEFT || snake.getDirection() == Direction.RIGHT) {
                        snake.setDirection(Direction.DOWN);
                    }

                    break;
                case A:
                case KP_LEFT:
                case LEFT:
                    if (snake.getDirection() == Direction.UP || snake.getDirection() == Direction.DOWN) {
                        snake.setDirection(Direction.LEFT);
                    }

                    break;
            }

            snake.setMoving(true);
        }
    }
}
