package snake.game;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

import static javafx.scene.effect.BlendMode.SRC_OVER;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 14.03.2021
 */
public class Renderer {
    private final GraphicsContext context;

    public Renderer(GraphicsContext context) {
        this.context = context;

        context.setGlobalBlendMode(SRC_OVER);
    }

    public void draw(Snake snake, List<Food> foodObjects) {
        context.clearRect(0, 0, Game.getWidth(), Game.getHeight());

        for (Entity segment : snake.getSegments()) {
            if (!segment.equals(snake.getSegments().getFirst())) {
                context.drawImage(segment.getImage(), segment.getX(), segment.getY());
            } else {
                if (snake.isBig()) {
                    Entity bigHead = snake.getBigHead();

                    context.drawImage(bigHead.getImage(), bigHead.getX(), bigHead.getY());
                } else {
                    context.drawImage(segment.getImage(), segment.getX(), segment.getY());
                }
            }
        }

        for (Food food : foodObjects) {
            context.drawImage(food.getImage(), food.getX(), food.getY());
        }
    }
}
