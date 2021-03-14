package snake.game;

import java.util.LinkedList;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public class Snake {
    private final LinkedList<Entity> segments = new LinkedList<>();

    private Direction direction = Direction.UP;                             // Direction the snake will move in (initialized to start by moving up)
    private boolean isMoving;                                               // Disables the KeyHandler

    public Snake() {
        double x = Math.floor(Game.getWidth() / Game.CELL / 2) * Game.CELL; // Canvas' center cell's top left x coordinate
        double y = Math.floor(Game.getWidth() / Game.CELL / 2) * Game.CELL; // Canvas' center cell's top left y coordinate

        segments.add(new Entity(Game.getImage("snake.png"), x, y));   // Initialize the head to start in the centermost cell of the canvas
    }

    public void move() {
        for (int i = 0; i < segments.size() - 1; i++) {
            Entity current = segments.get(i);
            Entity next = segments.get(i + 1);

            current.setX(next.getX());
            current.setY(next.getY());
        }

        Entity head = segments.getFirst();

        switch (direction) {
            case UP:
                head.setY(head.getY() - Game.CELL);

                break;
            case RIGHT:
                head.setX(head.getX() + Game.CELL);

                break;
            case DOWN:
                head.setY(head.getY() + Game.CELL);

                break;
            case LEFT:
                head.setX(head.getX() - Game.CELL);

                break;
        }

        isMoving = false;
    }

    public LinkedList<Entity> getSegments() {
        return segments;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
