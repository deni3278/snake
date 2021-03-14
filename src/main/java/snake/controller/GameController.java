package snake.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import snake.game.Game;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 12.03.2021
 */
public class GameController {
    @FXML
    public Canvas canvas;

    @FXML
    public Label labelScore;

    public void startGame(boolean insane) {
        Game game = new Game(canvas, insane);
        game.startLoop();

        labelScore.textProperty().bind(Bindings.format("Score: %-10d", game.getScoreProperty()));
    }
}
