package new_bullet;


public class Bullet {
    double x;
    double y;
    double width = 5;
    double height = 5;
    double speed = 10;


    public Bullet(double x, double y) {
        this.x = x;
        this.y = y;
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
        y -= speed;
    }
}
