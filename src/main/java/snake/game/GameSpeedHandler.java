package snake.game;

/**
 * A faux callback method interface, which allows a has-a relationship to call methods upstream.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
 * @version 0.0.1
 * @since 14.03.2021
 */
public interface GameSpeedHandler {
    void startLoop(GameSpeed speed);
}
