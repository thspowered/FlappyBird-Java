module projekt {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens FlappyBird to javafx.fxml;
    exports FlappyBird;
}