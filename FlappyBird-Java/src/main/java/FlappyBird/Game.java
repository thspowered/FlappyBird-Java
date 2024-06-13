package FlappyBird;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Scanner;

public class Game {

    private Pane pane;
    private Bird bird;
    private Pipe pipe;
    private Score score;
    private BackgroundImages Backgroundimages;
    private Menu menu;

    private Objects[] objects;
    private boolean gameOver = false;
    private boolean startGame = false;
    private boolean saveHS = false;

    private Scanner scanner;
    private Scene scene;


    public Game( ) {
            pane = new Pane();
            pane.setFocusTraversable(true);
            scene = new Scene(pane, 400, 600);
            scanner = new Scanner(System.in);
            bird = new Bird();
            pipe = new Pipe(this);
            Backgroundimages = new BackgroundImages();
            menu = new Menu();

            objects = new Objects[4];
            objects[0] = Backgroundimages;
            objects[1] = bird;
            objects[2] = pipe;
            objects[3] = menu;

            for (Objects o:objects) {
                o.AddToPane(pane);
            }

            score = new Score(this);
            initGameLoop();
        }
        private void initHandling(){
        if (!startGame){
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.SPACE) {
                    startGame = true;
                    menu.getMenuText().setVisible(false);
                    bird.jump();
                }
            });
        }else {
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.SPACE) {
                    bird.jump();
                }
            });
        }
        scene.setOnMouseClicked(event -> {
            if (gameOver && menu.getRestartImage().getBoundsInParent().contains(event.getX(), event.getY())) {
                restartGame();
            } });

        menu.getSaveButton().setOnMouseClicked(event -> {
            if (!saveHS) {
                saveHS = true;
                System.out.print("Zadaj meno: ");
                String name = scanner.nextLine();
                score.SaveHighScores(name);
            }
        });

        menu.getLoadButton().setOnMouseClicked(event -> {
            score.LoadScores();
            score.printScores();
        });
        }
        public Pane getPane() {
            return pane;
        }

        private void initGameLoop() {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                initHandling();
                if (startGame) {
                    if (!gameOver) {
                        bird.update();
                        pipe.update();
                        checkCollisions();
                    }
                }

            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }

        public Bird getBird() {
            return bird;
        }

    private void checkCollisions() {
        if (bird.getImageView().getBoundsInParent().intersects(pipe.getTopPipe().getBoundsInParent()) ||
                bird.getImageView().getBoundsInParent().intersects(pipe.getBottomPipe().getBoundsInParent())) {
            gameOver = true;

            endGame();

            String soundFile = getClass().getResource("/audio_hit.wav").toExternalForm();
            Media sound = new Media(soundFile);
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.10);
            mediaPlayer.play();
        }

    }
    private void endGame() {
        menu.getGameOverImage().setVisible(true);
        menu.getRestartImage().setVisible(true);
        score.getHighScoreText().setVisible(true);
        menu.getSaveButton().setVisible(true);
        menu.getLoadButton().setVisible(true);
    }
    private void restartGame() {
        gameOver = false;
        saveHS = false;
        menu.getGameOverImage().setVisible(false);
        menu.getRestartImage().setVisible(false);
        menu.getLoadButton().setVisible(false);
        menu.getSaveButton().setVisible(false);
        bird.resetPos();
        bird.jump();
        pipe.reset();
        score.resetScore();
        score.getHighScoreText().setVisible(false);
        score.printScore();
    }
    public void increaseScore(){
        score.increaseScore();
    }
}
