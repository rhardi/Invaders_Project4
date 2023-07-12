package Alien_Class;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Alien extends Rectangle {
    public static final double HEIGHT = 30;
    public static final double WIDTH = 40;
    private static final Color COLOR = Color.LIME;

    private double velocityX;
    private int moveDirection; // -1 for left, 1 for right

    public Alien(double x, double y) {
        super(x, y, WIDTH, HEIGHT);
        setFill(COLOR);
        velocityX = 2.0; // Adjust the velocity as needed
        moveDirection = 1;
    }

    public void update() {
        setX(getX() + (velocityX * moveDirection));
    }

    public void changeDirection() {
        moveDirection *= -1;
    }
}
