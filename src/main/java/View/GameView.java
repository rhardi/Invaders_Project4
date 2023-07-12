package View;


import Alien_Class.Alien;
import Shoot.AlienShot;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import new_bullet.Bullet;
import new_ship.Ship;


public class GameView {
    private Group root;
    private Canvas canvas;
    private GraphicsContext gc;


    public GameView(Group root, double width, double height) {
        this.root = root;
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
    }


    public void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    public void drawShip(Ship ship) {
        double x = ship.getX();
        double y = ship.getY();
        double width = ship.getWidth();
        double height = ship.getHeight();
        gc.setFill(Color.CRIMSON);


        double[] shipXPoints = {x, x + width / 2, x + width};
        double[] shipYPoints = {y + height, y, y + height};


        gc.fillPolygon(shipXPoints, shipYPoints, 3);
    }


    public void drawBullet(Bullet bullet) {
        gc.setFill(Color.BLUE);
        gc.fillRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
    }


    public void drawPlayerName(String playerName) {
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        gc.fillText("Player: " + playerName, 100, 20);
    }


    public void drawTimer(int gameTimeSeconds) {
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        gc.fillText("Timer: " + gameTimeSeconds + "s", 10, 20);
    }


    public void drawScore(int score) {
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        gc.fillText("Score: " + score, 10, 50);
    }


    public void drawLives(int lives) {
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        gc.fillText("Lives: " + lives, 10, 80);
    }


    public void drawAlien(Alien alien) {
        gc.setFill(Color.LIME);
        gc.fillRect(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
    }


    public void drawAlienShot(AlienShot alienShot) {
        double x = alienShot.getX();
        double y = alienShot.getY();
        double width = alienShot.getWidth();
        double height = alienShot.getHeight();


        gc.setFill(Color.RED);
        gc.fillRect(x, y, width, height);
    }
}
