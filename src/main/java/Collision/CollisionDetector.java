package Collision;

import Alien_Class.Alien;
import Shoot.AlienShot;
import new_bullet.Bullet;
import new_ship.Ship;

public class CollisionDetector {
    public static boolean isBulletCollidingWithAlien(Bullet bullet, Alien alien) {
        double bulletX = bullet.getX();
        double bulletY = bullet.getY();
        double bulletWidth = bullet.getWidth();
        double bulletHeight = bullet.getHeight();

        double alienX = alien.getX();
        double alienY = alien.getY();
        double alienWidth = alien.getWidth();
        double alienHeight = alien.getHeight();

        return (bulletX < alienX + alienWidth && bulletX + bulletWidth > alienX &&
                bulletY < alienY + alienHeight && bulletY + bulletHeight > alienY);
    }

    public static boolean isShipCollidingWithAlienShot(Ship ship, AlienShot alienShot) {
        double shipX = ship.getX();
        double shipY = ship.getY();
        double shipWidth = ship.getWidth();
        double shipHeight = ship.getHeight();

        double alienShotX = alienShot.getX();
        double alienShotY = alienShot.getY();
        double alienShotWidth = alienShot.getWidth();
        double alienShotHeight = alienShot.getHeight();

        return (shipX < alienShotX + alienShotWidth && shipX + shipWidth > alienShotX &&
                shipY < alienShotY + alienShotHeight && shipY + shipHeight > alienShotY);
    }
}
