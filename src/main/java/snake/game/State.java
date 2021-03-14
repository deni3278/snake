package snake.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public class State {
    private final GameSpeedHandler callback;
    private final IntegerProperty scoreProperty = new SimpleIntegerProperty(0);

    public State(GameSpeedHandler callback) {
        this.callback = callback;
    }

    public void update() {

    }

    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }
}
