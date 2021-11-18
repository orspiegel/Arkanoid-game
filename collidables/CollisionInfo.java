package collidables;
//318720067

import geometry.Point;

/**
 * The "CollisionInfo" class.
 * holds the information on the collidable objects of the game.
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public class CollisionInfo {
    /**
     * the collisonInfo class.
     * holds the information on the collidable objects of the game.
     */
    private Point collisionPoint;
    private Collidable collisionObject;
    // the point at which the collision occurs

    /** constructor.
     * that constructor keeps the information about the collide.
     * @param collisionPoint = the collidable object
     * @param collisionObject = the collision point of the ballWith the object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * getter of the point of collision.
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * collisionObject method -> the collidable object involved in the collision.
     * the collidable object involved in the collision.
     * @return the object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
