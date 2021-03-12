package snake.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import snake.App;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 12.03.2021
 */
public class MenuController {
    @FXML
    public Button btnNormal, btnInsane;

    @FXML
    public void initialize() {

    }

    @FXML
    public void setGameScene(ActionEvent e) {
        App.setGameScene();
    }
}
