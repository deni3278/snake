package snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import snake.controller.GameController;

import java.io.IOException;

/**
 * @author Denis Cokanovic
 * @version 0.0.1
 * @since 12.03.2021
 */
public class App extends Application {
    private static Stage stage;
    private static Scene sceneMenu, sceneGame;

    @Override
    public void start(Stage stage) {
        App.stage = stage;
        initScenes();

        stage.setTitle("Snake");
        stage.setResizable(false);
        setMenuScene();
        stage.show();
    }

    public static void setMenuScene() {
        stage.setScene(sceneMenu);
    }

    public static void setGameScene() {
        stage.setScene(sceneGame);

        if (sceneGame.getUserData() instanceof FXMLLoader) {
            FXMLLoader loader = (FXMLLoader) sceneGame.getUserData();

            if (loader.getController() instanceof GameController) {
                GameController controller = loader.getController();
                controller.startGame();
            }
        }
    }

    private void initScenes() {
        try {
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            sceneMenu = new Scene(menuLoader.load());
            sceneMenu.setUserData(menuLoader);

            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            sceneGame = new Scene(gameLoader.load());
            sceneGame.setUserData(gameLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
