package FlappyBird;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Menu implements Objects {

    private ImageView gameOverImage;
    private ImageView restartImage;

    private Button saveButton;
    private Button loadButton;
    private Text MenuText;

    public Menu(){
        gameOverImage = new ImageView(new Image("/gameover.png"));
        gameOverImage.setFitWidth(192);
        gameOverImage.setFitHeight(42);
        gameOverImage.setLayoutX(104);
        gameOverImage.setLayoutY(100);
        gameOverImage.setVisible(false);


        restartImage = new ImageView(new Image("/restart.png"));
        restartImage.setFitWidth(120);
        restartImage.setFitHeight(42);
        restartImage.setLayoutX(140);
        restartImage.setLayoutY(300);
        restartImage.setVisible(false);

        saveButton = new Button("SaveHS");
        saveButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        saveButton.setEffect(new DropShadow());
        saveButton.setLayoutX(70);
        saveButton.setLayoutY(400);
        saveButton.setVisible(false);

        loadButton = new Button("ShowHS");
        loadButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        loadButton.setEffect(new DropShadow());
        loadButton.setLayoutX(240);
        loadButton.setLayoutY(400);
        loadButton.setVisible(false);

        MenuText = new Text("Press space to start Game");
        MenuText.setFont(Font.font("Roboto Mono", FontWeight.BOLD, 30));
        MenuText.setFill(Color.WHITE);
        MenuText.setLayoutX((400 - MenuText.prefWidth(-1)) / 2);
        MenuText.setLayoutY(225);
        MenuText.setVisible(true);
    }

    public ImageView getGameOverImage(){
        return gameOverImage;
    }
    public ImageView getRestartImage(){
        return restartImage;
    }
    public Button getSaveButton(){
        return saveButton;
    }
    public Button getLoadButton(){
        return loadButton;
    }
    public Text getMenuText(){
        return MenuText;
    }
    @Override
    public void AddToPane(Pane pane) {
        pane.getChildren().addAll(saveButton, loadButton, restartImage, gameOverImage, MenuText);
    }
}
