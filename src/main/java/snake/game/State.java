package snake.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public class State implements ScoreHandler {
    private final IntegerProperty scoreProperty = new SimpleIntegerProperty(0);
    private final Snake snake;
    private final List<Food> foodObjects = new ArrayList<>();

    private boolean isGameOver;

    public State(GameSpeedHandler gameSpeedHandler) {
        snake = new Snake(this);
        initFoodObjects(gameSpeedHandler);
    }

    public void update() {
        isGameOver = snake.checkCollisions(foodObjects);

        if (!isGameOver) {
            snake.move();
        }
    }

    @Override
    public void increment() {
        scoreProperty.set(scoreProperty.get() + 1);
    }

    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Food> getFoodObjects() {
        return foodObjects;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void initFoodObjects(GameSpeedHandler callback) {
        final int MOVE = 10;  // Amount of seconds before the food moves on the canvas

        Food food = new Food(Game.getImage("food.png"), snake, foodObjects);
        foodObjects.add(food);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Food food : foodObjects) {
                    food.move();
                }
            }
        }, 0, Duration.ofSeconds(MOVE).toMillis());
    }
}
