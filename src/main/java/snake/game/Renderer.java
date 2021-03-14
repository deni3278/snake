package snake.game;

import javafx.scene.canvas.GraphicsContext;

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

    public void draw() {
        context.clearRect(0, 0, Game.getWidth(), Game.getHeight());
    }
}
