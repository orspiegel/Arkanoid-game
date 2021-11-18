package collidables;
// 318720067

import animation.Velocity;
import gamerun.GameLevel;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * Rectangle class to generate a new Rectangle.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision shape.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  = the point of the collision
     * @param currentVelocity = the given velocity
     * @param ball = the ball.
     * @return the new velocoty.
     */
    Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity);

    /**
     * method to add the collidable objects to my game.
     *
     * @param g = my game.
     */
    void addToGame(GameLevel g);
}


