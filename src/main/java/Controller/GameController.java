package Controller;


import Alien_Class.Alien;
import Collision.CollisionDetector;
import Invaders.SpaceInvaders;
import Rules.RulesDialog;
import Shoot.AlienShot;
import View.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import new_bullet.Bullet;
import new_ship.Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GameController {
    private Stage stage;
    private Group root;
    private GameView gameView;


    private Ship ship;
    private List<Bullet> bullets;
    private List<Alien> aliens;
    private List<AlienShot> alienShots;


    private Timeline gameLoop;
    private String playerName;
    private int gameTimeSeconds;
    private int score;
    private int lives;


    public GameController(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        this.gameView = new GameView(root, SpaceInvaders.WIDTH, SpaceInvaders.HEIGHT);


        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Player Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your name:");
        dialog.setResizable(false);


        playerName = dialog.showAndWait().orElse("Player");


        Scene scene = new Scene(root, SpaceInvaders.WIDTH, SpaceInvaders.HEIGHT);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                shoot();
            }
            if (event.getCode() == KeyCode.LEFT) {
                ship.moveLeft();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                ship.moveRight();
            }
        });


        stage.setScene(scene);
        stage.setTitle("Space Invaders Game JavaFX");


        bullets = new ArrayList<>();
        alienShots = new ArrayList<>();
    }


    public void startGame() {
        stage.show();


        RulesDialog.show();


        ship = new Ship(SpaceInvaders.WIDTH / 2, SpaceInvaders.HEIGHT - 50);
        aliens = new ArrayList<>();


        createAliens();


        score = 0;
        lives = 3;
        gameTimeSeconds = 0;


        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            gameTimeSeconds++;
            gameView.drawTimer(gameTimeSeconds);
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();


        gameLoop = new Timeline(new KeyFrame(Duration.seconds(0.020), event -> {
            update();
            draw();
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.playFromStart();
    }


    private void shoot() {
        Bullet bullet = new Bullet(ship.getX() + ship.getWidth() / 2, ship.getY());
        bullets.add(bullet);
    }


    private void update() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();
            if (bullet.getY() < 0) {
                bulletIterator.remove();
            } else {
                for (Alien alien : aliens) {
                    if (CollisionDetector.isBulletCollidingWithAlien(bullet, alien)) {
                        bulletIterator.remove();
                        aliens.remove(alien);
                        increaseScore();
                        break;
                    }
                }
            }
        }


        boolean moveDown = false;
        for (Alien alien : aliens) {
            alien.update();
            double alienX = alien.getX();
            double alienWidth = alien.getWidth();
            if (alienX <= 0 || alienX + alienWidth >= SpaceInvaders.WIDTH) {
                moveDown = true;
                break;
            }
        }


        if (moveDown) {
            for (Alien alien : aliens) {
                alien.changeDirection();
                alien.setY(alien.getY() + Alien.HEIGHT);
            }
        }


        for (Alien alien : aliens) {
            if (Math.random() < 0.005) {
                AlienShot alienShot = new AlienShot(alien.getX() + alien.getWidth() / 2, alien.getY() + alien.getHeight());
                alienShots.add(alienShot);
            }
        }


        Iterator<AlienShot> alienShotIterator = alienShots.iterator();
        while (alienShotIterator.hasNext()) {
            AlienShot alienShot = alienShotIterator.next();
            alienShot.update();
            if (alienShot.getY() > SpaceInvaders.HEIGHT) {
                alienShotIterator.remove();
            } else if (CollisionDetector.isShipCollidingWithAlienShot(ship, alienShot)) {
                decreaseLives();
                alienShotIterator.remove();
            }
        }


        if (aliens.isEmpty()) {
            endGame();
            Platform.runLater(() -> showCongratulationsDialog());
            return;
        }
    }


    private void draw() {
        gameView.clear();
        gameView.drawPlayerName(playerName);
        gameView.drawTimer(gameTimeSeconds);
        gameView.drawShip(ship);
        gameView.drawScore(score);
        gameView.drawLives(lives);
        for (Alien alien : aliens) {
            gameView.drawAlien(alien);
        }
        for (Bullet bullet : bullets) {
            gameView.drawBullet(bullet);
        }
        for (AlienShot alienShot : alienShots) {
            gameView.drawAlienShot(alienShot);
        }
    }


    private void createAliens() {
        double initialX = 100;
        double initialY = 100;
        double spacing = 50;
        int rows = 3;
        int columns = 6;


        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                double x = initialX + col * spacing;
                double y = initialY + row * spacing;


                Alien alien = new Alien(x, y);
                aliens.add(alien);
            }
        }
    }


    private void increaseScore() {
        score += 10;
        gameView.drawScore(score);
    }


    private void decreaseLives() {
        lives--;
        if (lives == 0) {
            endGame();
        }
        gameView.drawLives(lives);
    }


    private void endGame() {
        gameLoop.stop();
        Platform.runLater(() -> {
            Alert endGameAlert = new Alert(Alert.AlertType.INFORMATION);
            endGameAlert.setTitle("Game Over");
            endGameAlert.setHeaderText(null);
            endGameAlert.setContentText("Game Over! Your final score is: " + score);
            endGameAlert.showAndWait();
        });
    }


    private void showCongratulationsDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations! You have defeated all of the aliens!");
        alert.showAndWait();
    }
}
