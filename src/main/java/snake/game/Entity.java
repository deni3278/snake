package snake.game;

import javafx.scene.image.Image;

/**
 * Models a 2D entity with an associated sprite.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
 * @version 0.0.1
 * @since 14.03.2021
 */
public class Entity {
    private Image image;
    private double x, y, width ,height;

    /**
     * Initializes a new Entity with the specified image and x and y coordinates.
     *
     * @param image the sprite of the entity.
     * @param x the x coordinate of the entity.
     * @param y the y coordinate of the entity.
     */
    public Entity(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /**
     * Checks whether this entity intersects with another entity.
     *
     * <p>The method calls {@link #intersects(double, double, double, double)} to validate.
     *
     * @param entity the entity that is being checked.
     * @return true if this entity is intersecting the other entity.
     */
    public boolean intersects(Entity entity) {
        return intersects(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
    }

    /**
     * Checks whether this entity intersects the given bounds.
     *
     * @param x the top left x coordinate of the bounds.
     * @param y the top left y coordinate of the bounds.
     * @param width the width of the bounds.
     * @param height the height of the bounds.
     * @return true if this entity intersects the bounds.
     */
    public boolean intersects(double x, double y, double width, double height) {
        return (x < this.x + this.width && x + width > this.x) && (y < this.y + this.height && y + height > this.y);
    }

    public Image getImage() {
        return image;
    }

    /**
     * Sets a new image and updates the width and height variables to match.
     *
     * @param image the new image.
     */
    public void setImage(Image image) {
        this.image = image;

        width = image.getWidth();
        height = image.getHeight();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
