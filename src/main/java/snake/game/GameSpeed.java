package snake.game;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public enum GameSpeed {
    NORMAL(0.25), FAST(0.15);

    double value;

    GameSpeed(double value) {
        this.value = value;
    }
}
