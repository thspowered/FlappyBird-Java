package FlappyBird;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Score {
    private int score = 0;
    private Text scoreText;
    private int HighScore = 0;
    private Text HighScoreText;
    private Game game;
    private List<HighScore> HighScores = new ArrayList<>();

    public Score(Game game) {
        this.game = game;


        scoreText = new Text(String.valueOf(score));
        scoreText.setFont(Font.font("Roboto Mono", FontWeight.BOLD, 50));
        scoreText.setFill(Color.WHITE);
        scoreText.setLayoutX((400 - scoreText.prefWidth(-1)) / 2);
        scoreText.setLayoutY(70);
        game.getPane().getChildren().add(scoreText);

        HighScoreText = new Text("Best: " + HighScore);
        HighScoreText.setFont(Font.font("Roboto Mono", FontWeight.BOLD, 50));
        HighScoreText.setFill(Color.WHITE);
        HighScoreText.setLayoutX((400 - HighScoreText.prefWidth(-1)) / 2);
        HighScoreText.setLayoutY(225);
        game.getPane().getChildren().add(HighScoreText);
        HighScoreText.setVisible(false);
    }

    public void increaseScore() {
        score++;
        if (score > HighScore) {
            HighScore = score;
            HighScoreText.setText("Best: " + HighScore);
        }
        printScore();
    }

    public void printScore() {
        scoreText.setText(String.valueOf(score));
    }

    public Text getHighScoreText() {
        return HighScoreText;
    }

    public void resetScore() {
        score = 0;
    }


    public class HighScore implements Comparable<HighScore>{
        private String name;
        private int score;

        public HighScore(String name, int score){
            this.name = name;
            this.score = score;
        }
        @Override
        public int compareTo(HighScore other) {
            return Integer.compare(this.score, other.score);
        }

        public String getName(){
            return name;
        }
        public int getScore(){
            return score;
        }
    }
    public void SaveHighScores(String name) {
        try (FileWriter fw = new FileWriter("data.csv", true);) {
            fw.write(name + ";" + HighScore + "\n");
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
    public void LoadScores() {
        HighScores.clear();
        try (Scanner scanner = new Scanner(new java.io.File("data.csv"))) {
            scanner.useDelimiter("[;\\n]");

            while (scanner.hasNext()) {
                String name = scanner.next();
                int score = scanner.nextInt();
                HighScores.add(new HighScore(name, score));
            }


            Collections.sort(HighScores, Collections.reverseOrder());
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
    public void printScores() {
        System.out.println("High Scores: ");
        for (HighScore highScore : HighScores) {
            String name = highScore.getName();
            int score = highScore.getScore();


            System.out.println("Meno: " + name + ", Skore: " + score);
        }
        System.out.println();
    }
}




