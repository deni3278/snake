package snake.game;

import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

/**
 * Models a simple food object.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
 * @version 0.0.1
 * @since 14.03.2021
 */
public class Food extends Entity {
    private static final Random random = new Random();  // Used to generate random coordinates

    private final Snake snake;
    private final List<Food> foodObjects;

    /**
     * Initializes a new Food object with a given sprite image.
     *
     * @param image the sprite image of the entity.
     * @param snake the player Snake object.
     * @param foodObjects list of all food objects in the current game.
     */
    public Food(Image image, Snake snake, List<Food> foodObjects) {
        super(image, -Game.CELL, -Game.CELL);

        this.snake = snake;
        this.foodObjects = foodObjects;

        move(); // Set the starting position of the food object by moving it as if it were eaten by the player
    }

    /**
     * Generates new x and y coordinates until they do not intersect with any other game object.
     * The entity's is moved to the new position.
     */
    public void move() {
        boolean isEmpty;
        double x, y;

        do {
            isEmpty = true;
            x = random.nextInt((int) Math.floor(Game.getWidth() / Game.CELL)) * Game.CELL;
            y = random.nextInt((int) Math.floor(Game.getHeight() / Game.CELL)) * Game.CELL;

            if (snake.getBigHead() != null && snake.getBigHead().intersects(x, y, getWidth(), getHeight())) {
                isEmpty = false;
            }

            for (Entity segment : snake.getSegments()) {
                if (segment.intersects(x, y, getWidth(), getHeight())) {
                    isEmpty = false;

                    break;
                }
            }

            for (Food food : foodObjects) {
                if (food != this && food.intersects(x, y, getWidth(), getHeight())) {
                    isEmpty = false;

                    break;
                }
            }
        } while (!isEmpty);

        setX(x);
        setY(y);
    }
}
