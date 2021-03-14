package snake.game;

import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public class Food extends Entity {
    private static final Random random = new Random();

    private final Snake snake;
    private final List<Food> foodObjects;

    public Food(Image image, Snake snake, List<Food> foodObjects) {
        super(image, -Game.CELL, -Game.CELL);

        this.snake = snake;
        this.foodObjects = foodObjects;

        move();
    }

    public void move() {
        boolean isEmpty;
        double x, y;

        do {
            isEmpty = true;
            x = random.nextInt((int) Math.floor(Game.getWidth() / Game.CELL)) * Game.CELL;
            y = random.nextInt((int) Math.floor(Game.getHeight() / Game.CELL)) * Game.CELL;

            for (Entity segment : snake.getSegments()) {
                if (intersects(segment)) {
                    isEmpty = false;

                    break;
                }
            }

            for (Food food : foodObjects) {
                if (food != this && intersects(food)) {
                    isEmpty = false;

                    break;
                }
            }
        } while (!isEmpty);

        setX(x);
        setY(y);
    }
}
