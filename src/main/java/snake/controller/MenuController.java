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
        btnNormal.setUserData(false);
        btnInsane.setUserData(true);
    }

    @FXML
    public void setGameScene(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            Button btn = (Button) e.getSource();

            if (btn.getUserData() instanceof Boolean) {
                boolean insane = (boolean) btn.getUserData();

                App.setGameScene(insane);
            }
        }
    }
}
