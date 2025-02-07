package FlappyBird;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();

        primaryStage.setTitle("Flappy Bird");
        primaryStage.setScene(game.getPane().getScene());
        primaryStage.show();
    }
}
