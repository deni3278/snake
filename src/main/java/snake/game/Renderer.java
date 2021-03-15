package snake.game;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

import static javafx.scene.effect.BlendMode.SRC_OVER;

/**
 * Models a rendering engine that allows game objects to be rendered onto a canvas.
 *
 * @author Denis, Morten, Niclas, Rasmus & Vanda
 * @version 0.0.1
 * @since 14.03.2021
 */
public class Renderer {
    private final GraphicsContext context;

    /**
     * Initializes a Renderer with a graphics context to send draw calls to.
     *
     * @param context the canvas' graphics context to draw onto.
     */
    public Renderer(GraphicsContext context) {
        this.context = context;

        context.setGlobalBlendMode(SRC_OVER);   // Allows the clear method of the graphics context to create transparency instead of a white background
    }

    /**
     * Draws the specified game objects.
     *
     * @param snake the snake to draw.
     * @param foodObjects the food objects to draw.
     */
    public void draw(Snake snake, List<Food> foodObjects) {
        context.clearRect(0, 0, Game.getWidth(), Game.getHeight());

        for (Entity segment : snake.getSegments()) {
            context.drawImage(segment.getImage(), segment.getX(), segment.getY());

            if (segment.equals(snake.getSegments().getFirst()) && snake.isBig()) {
                Entity bigHead = snake.getBigHead();

                context.drawImage(bigHead.getImage(), bigHead.getX(), bigHead.getY());
            }
        }

        for (Food food : foodObjects) {
            context.drawImage(food.getImage(), food.getX(), food.getY());
        }
    }
}
