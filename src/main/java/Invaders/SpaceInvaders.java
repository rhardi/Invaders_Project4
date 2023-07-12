package Invaders;


import Controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;


public class SpaceInvaders extends Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController(primaryStage);
        gameController.startGame();
    }
}
