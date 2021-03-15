package snake.game;

import javafx.scene.image.Image;

import java.util.List;

/**
 * Models a food object with an additional effect.
 *
 * <p>The effect of the object is specified by the implementation of the {@link #eat()} method.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
 * @version 0.0.1
 * @since 15.03.2021
 */
public abstract class Powerup extends Food {
    private boolean isActive;

    /**
     * {@inheritDoc}
     */
    public Powerup(Image image, Snake snake, List<Food> foodObjects) {
        super(image, snake, foodObjects);
    }

    /**
     * Executes the effect of the food.
     */
    public abstract void eat();

    /**
     * Returns whether the food already has been eaten and the effects are active.
     *
     * @return true if it's been eaten already.
     */
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
