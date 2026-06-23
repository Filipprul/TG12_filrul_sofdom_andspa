import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import menu.menu;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake Game");

        menu gamemenu = new menu();

        Scene menuScene = gameMenu.getMenuScene(primaryStage);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}