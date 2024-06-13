package FlappyBird;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Pipe implements Objects{

    private String TOP_PIPE_IMAGE = "/top_pipe.png";
    private String BOTTOM_PIPE_IMAGE = "/bottom_pipe.png";
    private  int PIPE_WIDTH = 50;
    private  int PIPE_SPEED = 4;
    private Game game;
    private boolean hasPassed;
    private ImageView topPipe;
    private ImageView bottomPipe;

    public Pipe(Game game) {
        this.game = game;
        initPipes();
    }



    private void initPipes() {
        int random = generateRandomHeight();
        topPipe = createPipe(TOP_PIPE_IMAGE, true, random);
        bottomPipe = createPipe(BOTTOM_PIPE_IMAGE, false, random);
    }

    private ImageView createPipe(String imageUrl, boolean isTop, int random) {
        ImageView pipe = new ImageView(new Image(Pipe.class.getResource(imageUrl).toExternalForm()));
        pipe.setFitWidth(PIPE_WIDTH);
        pipe.setFitHeight(194 + random);
        pipe.setLayoutX(400);

        if (isTop) {
            pipe.setLayoutY(0);
        } else {
            if (random >= 0) {
                pipe.setFitHeight(194 - random);
                pipe.setLayoutY(294 + random);
            } else {
                pipe.setFitHeight(194 + Math.abs(random));
                pipe.setLayoutY(294 - Math.abs(random));
            }
        }
        return pipe;
    }
    public void AddToPane(Pane pane) {
        pane.getChildren().addAll(topPipe, bottomPipe);
    }

    public void move() {
        topPipe.setLayoutX(topPipe.getLayoutX() - PIPE_SPEED);
        bottomPipe.setLayoutX(bottomPipe.getLayoutX() - PIPE_SPEED);
    }

    public boolean isOffScreen() {
        return topPipe.getLayoutX() + PIPE_WIDTH < 0;
    }

    public void update(){
        move();
        if (!hasPassed && isPipePassed()) {
            game.increaseScore();
            hasPassed = true;
        }

        if (isOffScreen()) {
            reset();
            hasPassed = false;
        }
    }

    public void reset() {
        int random = generateRandomHeight();
        topPipe.setLayoutX(400);
        topPipe.setLayoutY(0);
        topPipe.setFitHeight(194 + random);

        if (random >= 0) {
            bottomPipe.setFitHeight(194 - random);
            bottomPipe.setLayoutY(294 + random);
            bottomPipe.setLayoutX(400);
        } else {
            bottomPipe.setFitHeight(194 + Math.abs(random));
            bottomPipe.setLayoutY(294 - Math.abs(random));
            bottomPipe.setLayoutX(400);
        }
    }

    public ImageView getTopPipe() {
        return topPipe;
    }
    public ImageView getBottomPipe() {
        return bottomPipe;
    }

    private int generateRandomHeight() {
        Random random = new Random();
        int maxHeight = 100;
        int minHeight = -100;
        return random.nextInt(minHeight, maxHeight);
    }
    private boolean isPipePassed() {
        return topPipe.getBoundsInParent().getMaxX() < game.getBird().getImageView().getLayoutX();
    }
}
