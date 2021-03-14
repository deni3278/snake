package snake.game;

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

    private double px, py;

    public Snake(ScoreHandler callback) {
        this.callback = callback;

        double x = Math.floor(Game.getWidth() / Game.CELL / 2) * Game.CELL; // Canvas' center cell's top left x coordinate
        double y = Math.floor(Game.getWidth() / Game.CELL / 2) * Game.CELL; // Canvas' center cell's top left y coordinate
        px = x;
        py = y;

        segments.add(new Entity(Game.getImage("snake.png"), x, y));   // Initialize the head to start in the centermost cell of the canvas
    }

    public void move() {
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
            if (food.intersects(nextPosition[0], nextPosition[1], Game.CELL, Game.CELL)) {
                grow();

                food.move();

                break;
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

    private void grow() {
        segments.addLast(new Entity(segments.getLast().getImage(), px, py));
        callback.increment();
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
