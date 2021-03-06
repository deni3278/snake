package snake.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Models a game's state.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
 * @version 0.0.1
 * @since 14.03.2021
 */
public class State implements ScoreHandler {
    private final IntegerProperty scoreProperty = new SimpleIntegerProperty(0); // The score property, which the score label is bound to
    private final Snake snake;
    private final List<Food> foodObjects = new ArrayList<>();

    private boolean isGameOver; // If true, the next frame will not be rendered and the loop will be stopped

    /**
     * Creates a new State with an implementation of the callback interface.
     *
     * @param gameSpeedHandler the implementation of the callback interface that acts a delegate method.
     */
    public State(GameSpeedHandler gameSpeedHandler) {
        snake = new Snake(this);
        initFoodObjects(gameSpeedHandler);
    }

    /**
     * Called every frame to update the snake's movement and check for collisions.
     */
    public void update() {
        isGameOver = snake.checkCollisions(foodObjects);

        if (!isGameOver) {
            snake.move();
        }
    }

    /**
     * Increments the score.
     */
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

    /**
     * Initializes the three food objects and provides implementations to the powerups.
     *
     * @param callback the callback implementation that allows the caller to change the game speed.
     */
    private void initFoodObjects(GameSpeedHandler callback) {
        final int MOVE = 10;    // Amount of seconds before the food moves on the canvas
        final int SPEED = 5;    // Amount of seconds that the speed food lasts for
        final int BIG = 8;      // Amount of seconds that the big food lasts for

        Food food = new Food(Game.getImage("food.png"), snake, foodObjects);
        foodObjects.add(food);

        Food speedFood = new Powerup(Game.getImage("pepper.png"), snake, foodObjects) {
            @Override
            public void eat() {
                if (!isActive()) {
                    callback.startLoop(GameSpeed.FAST);

                    setActive(true);

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            callback.startLoop(GameSpeed.NORMAL);

                            setActive(false);
                        }
                    }, Duration.ofSeconds(SPEED).toMillis());
                }
            }
        };
        foodObjects.add(speedFood);

        Food bigFood = new Powerup(Game.getImage("peanut.png"), snake, foodObjects) {
            @Override
            public void eat() {
                if (!isActive()) {
                    snake.setBig(true);
                    setActive(true);

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            snake.setBig(false);
                            setActive(false);
                        }
                    }, Duration.ofSeconds(BIG).toMillis());
                }
            }
        };
        foodObjects.add(bigFood);

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
