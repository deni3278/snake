package snake.game;

import javafx.scene.image.Image;

import java.util.List;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 15.03.2021
 */
public abstract class Powerup extends Food {
    private boolean isActive;

    public Powerup(Image image, Snake snake, List<Food> foodObjects) {
        super(image, snake, foodObjects);
    }

    public abstract void eat();

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
