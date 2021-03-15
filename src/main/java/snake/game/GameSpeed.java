package snake.game;

/**
 * Possible game speeds and respective values.
 *
 * <p>The values are in terms of how long a single frame should last for.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
 * @version 0.0.1
 * @since 14.03.2021
 */
public enum GameSpeed {
    NORMAL(0.3), FAST(0.1);

    double value;

    GameSpeed(double value) {
        this.value = value;
    }
}
