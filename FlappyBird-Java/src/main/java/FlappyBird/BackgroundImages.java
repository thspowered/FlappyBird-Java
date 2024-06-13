package FlappyBird;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BackgroundImages implements Objects{
    private ImageView background;
    private ImageView base;


    public BackgroundImages(){
        background = new ImageView(new Image("background-day.png"));
        background.setFitWidth(400);
        background.setFitHeight(600);

        base = new ImageView(new Image("base.png"));
        base.setFitWidth(400);
        base.setFitHeight(112);
        base.setLayoutY(600 - 112);

    }

    @Override
    public void AddToPane(Pane pane) {
        pane.getChildren().addAll(background, base);
    }
}
