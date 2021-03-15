package snake.game;

import javafx.scene.image.Image;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public class Entity {
    private Image image;
    private double x, y, width ,height;

    public Entity(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public boolean intersects(Entity entity) {
        return intersects(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
    }

    public boolean intersects(double x, double y, double width, double height) {
        return (x < this.x + this.width && x + width > this.x) && (y < this.y + this.height && y + height > this.y);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
