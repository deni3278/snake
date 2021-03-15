package snake.game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public class Snake {
    private final ScoreHandler callback;
    private final LinkedList<Entity> segments = new LinkedList<>();

    private Direction currentDirection = Direction.UP;                      // Direction the snake will move in (initialized to start by moving up)
    private Direction lastDirection = currentDirection;                     // Disables the KeyHandler
    private double px, py;                                                  // Coordinates of the tail's last position

    private BooleanProperty isBigProperty = new SimpleBooleanProperty(false);
    private Entity bigHead;

    public Snake(ScoreHandler callback) {
        this.callback = callback;

        double x = Math.floor(Game.getWidth() / Game.CELL / 2) * Game.CELL; // Canvas' center cell's top left x coordinate
        double y = Math.floor(Game.getWidth() / Game.CELL / 2) * Game.CELL; // Canvas' center cell's top left y coordinate
        px = x;
        py = y;

        segments.add(new Entity(Game.getImage("snake.png"), x, y));   // Initialize the head to start in the centermost cell of the canvas

        isBigProperty.addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                bigHead = null;
            }
        });
    }

    public void move() {
        updateSprites();

        px = segments.getLast().getX();
        py = segments.getLast().getX();

        if (segments.size() > 1) {
            for (int i = segments.size() - 1; i > 0; i--) {
                Entity current = segments.get(i);
                Entity next = segments.get(i - 1);

                current.setX(next.getX());
                current.setY(next.getY());
            }
        }

        double[] nextPosition = nextPosition();

        Entity head = segments.getFirst();
        head.setX(nextPosition[0]);
        head.setY(nextPosition[1]);

        if (isBigProperty.get()) {
            Image image = Game.getImage("big.png");
            double x = head.getX() - Game.CELL;
            double y = head.getY() - Game.CELL;

            if (bigHead == null) {
                bigHead = new Entity(image, x, y);
            } else {
                bigHead.setX(x);
                bigHead.setY(y);
            }
        }

        lastDirection = currentDirection;
    }

    public boolean checkCollisions(List<Food> foodObjects) {
        double[] nextPosition = nextPosition();

        if ((nextPosition[0] < 0 || nextPosition[0] > Game.getWidth() - Game.CELL) || (nextPosition[1] < 0 || nextPosition[1] > Game.getHeight() - Game.CELL)) {
            return true;
        }

        for (Entity segment : segments) {
            if (segment.intersects(nextPosition[0], nextPosition[1], Game.CELL, Game.CELL)) {
                return true;
            }
        }

        for (Food food : foodObjects) {
            if (isBig()) {
                if (food.intersects(bigHead)) {
                    grow();

                    if (food instanceof Powerup) {
                        ((Powerup) food).eat();
                    }

                    food.move();

                    break;
                }
            } else {
                if (food.intersects(nextPosition[0], nextPosition[1], Game.CELL, Game.CELL)) {
                    grow();

                    if (food instanceof Powerup) {
                        ((Powerup) food).eat();
                    }

                    food.move();

                    break;
                }
            }
        }

        return false;
    }

    public LinkedList<Entity> getSegments() {
        return segments;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public boolean isBig() {
        return isBigProperty.get();
    }

    public void setBig(boolean big) {
        isBigProperty.setValue(big);
    }

    public Entity getBigHead() {
        return bigHead;
    }

    private void grow() {
        segments.addLast(new Entity(segments.getLast().getImage(), px, py));
        callback.increment();
    }

    private void updateSprites() {
        segments.getFirst().setImage(Game.getImage("head.png"));

        if (segments.size() > 1) {
            for (int i = segments.size() - 1; i > 0; i--) {
                segments.get(i).setImage(Game.getImage("snake.png"));
            }
        }
    }

    private double[] nextPosition() {
        Entity head = segments.getFirst();
        double nx = -Game.CELL;
        double ny = -Game.CELL;

        switch (currentDirection) {
            case UP:
                nx = head.getX();
                ny = head.getY() - Game.CELL;

                break;
            case RIGHT:
                nx = head.getX() + Game.CELL;
                ny = head.getY();

                break;
            case DOWN:
                nx = head.getX();
                ny = head.getY() + Game.CELL;

                break;
            case LEFT:
                nx = head.getX() - Game.CELL;
                ny = head.getY();

                break;
        }

        return new double[] {nx, ny};
    }
}
