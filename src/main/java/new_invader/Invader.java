package new_invader;

import java.util.Random;

public class Invader {
    private double x;
    private double y;
    private final double width = 40;
    private final double height = 20;
    private final double speed = 2;

    private double minX;
    private double maxX;
    private double direction;

    public Invader(double minX, double maxX) {
        this.minX = minX;
        this.maxX = maxX;
        this.direction = 1;

        Random random = new Random();
        this.x = random.nextDouble() * (maxX - minX) + minX;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void update() {
        x += direction * speed;

        if (x <= minX || x + width >= maxX) {
            direction *= -1;
        }
    }
}