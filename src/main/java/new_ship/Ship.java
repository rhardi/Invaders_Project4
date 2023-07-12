package new_ship;


import Invaders.SpaceInvaders;


public class Ship {
    double x;
    double y;
    double width = 40;
    double height = 20;
    double speed = 10;


    public Ship(double x, double y) {
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


    public void moveLeft() {
        if (x > 0) {
            x = x - speed;
        }
    }


    public void moveRight() {
        if (x < SpaceInvaders.WIDTH - width) {
            x = x + speed;
        }
    }
}
