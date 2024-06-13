package FlappyBird;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Bird implements Objects {
    private boolean isJumping = false;
    private float velocity = 0;

    private ImageView bird;

    public Bird() {
        bird = new ImageView(new Image(Bird.class.getResource("/bird.png").toExternalForm()));
        bird.setFitHeight(24);
        bird.setFitWidth(34);
        bird.setLayoutX(50);
        bird.setLayoutY(300);
    }

    @Override
    public void AddToPane(Pane pane) {
        pane.getChildren().add(bird);
    }

    public ImageView getImageView() {
        return bird;
    }

    public void resetPos(){
        bird.setLayoutX(50);
        bird.setLayoutY(300);
    }

    public void jump() {
        velocity = -7;
        setJumping(true);

        String soundFile = getClass().getResource("/audio_wing.wav").toExternalForm();
        Media sound = new Media(soundFile);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.10);
        mediaPlayer.play();
    }

    public void fall() {
        velocity += 0.5;
    }

    public void update() {
        fall();
        bird.setLayoutY(bird.getLayoutY() + velocity);
        if (bird.getLayoutY() > 600 - 136) {
            bird.setLayoutY(600 - 136);
        } else if (bird.getLayoutY() < 0) {
            bird.setLayoutY(0);
        }
    }

    public void setJumping(boolean jumping){
        this.isJumping = jumping;
    }

}
