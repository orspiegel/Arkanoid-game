package gamerun;
// 318720067

import collidables.Block;
import collidables.Collidable;
import collidables.CollisionInfo;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The "GameEnvironment" class.
 * During the game, there are going to be many objects a Ball can collide with. The GameEnvironment class
 * will be a collection of such things. The ball will know the game environment,
 * and will use it to check for collisions and direct its movement.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidable;
    private Point lowerLeftEdge;
    private Point upperRightEdge;
    private boolean cornerHit = false;

    /**
     * constructor to GameEnvironment class.
     */
    public GameEnvironment() {
        this.collidable = new ArrayList<Collidable>();
    }

    /**
     * getter of the flag.
     *
     * @return boolean.
     */
    public boolean getCornerHit() {
        return this.cornerHit;
    }

    /**
     * constructor.
     *
     * @param lowerLeftEdge  = the lower edge
     * @param upperRightEdge = the upper right edge
     */
    public GameEnvironment(Point lowerLeftEdge, Point upperRightEdge) {
        this.collidable = new ArrayList<Collidable>();
        this.lowerLeftEdge = lowerLeftEdge;
        this.upperRightEdge = upperRightEdge;
    }

    /**
     * constructor.
     *
     * @param collidable = list of the collidables
     */
    public GameEnvironment(ArrayList collidable) {
        this.collidable = new ArrayList<Collidable>();
    }

    /**
     * constructor.
     *
     * @param collidable     = the array of the collidable objects
     * @param lowerLeftEdge  = the lower edge
     * @param upperRightEdge = the upper right edge
     */
    public GameEnvironment(ArrayList collidable, Point lowerLeftEdge, Point upperRightEdge) {
        this.collidable = new ArrayList<Collidable>();
        this.upperRightEdge = upperRightEdge;
        this.lowerLeftEdge = lowerLeftEdge;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c = the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.collidable.add(c);
    }

    /**
     * getter for the collidable list.
     *
     * @return the collidable list
     */
    public List<Collidable> getCollidable() {
        return this.collidable;
    }

    /**
     * getter of the lower left edge.
     *
     * @return the tip
     */
    public Point getLowerLeftEdge() {
        return this.lowerLeftEdge;
    }

    /**
     * getter of the upper right edge.
     *
     * @return the tip
     */
    public Point getUpperRightEdge() {
        return this.upperRightEdge;
    }

    /**
     * setter of the lower edge point.
     *
     * @param p = the point to set on the lowerLeftEdge
     */
    public void setLowerLeftEdge(Point p) {
        this.lowerLeftEdge = p;
    }

    /**
     * setter of the upper edge point.
     *
     * @param p = the point to set on the upperRightEdge
     */
    public void setUpperRightEdge(Point p) {
        this.upperRightEdge = p;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory = the line that starts with the current center of the ball,
     *                   and ends with the next center location of the bal with coordination
     *                   to the velocity and the angle.
     * @return collosionInfo -> the closest point and closest collide object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //trajectory end defined as the next point,
        // that the ball gonna hit.
        // ant trajectory start defined as the current point.
        Point bestPoint = null;
        Collidable closest = null;
        double shortestDistance = Double.POSITIVE_INFINITY;
        //loop for all the collidable objects
        for (int recIndex = 0; recIndex < this.collidable.size(); recIndex++) {
            //adding to the array -> maximum of 2 intersectionPoints with the rectangle
            List<Point> collisionPointsList = this.collidable.get(recIndex).getCollisionRectangle()
                    .intersectionPoints(trajectory);
            if (collisionPointsList != null) {
                for (int k = 0; k < collisionPointsList.size(); k++) {
                    double dis = collisionPointsList.get(k).distance(trajectory.start());
                    if (dis < shortestDistance) {
                        shortestDistance = dis;
                        // the closest point of collide
                        bestPoint = collisionPointsList.get(k);
                        // the closest object to collide
                        closest = this.collidable.get(recIndex);
                        this.cornerHit = false;
                    } else if (dis == shortestDistance) {
                        // hitted the corner of 2 bricks
                        this.cornerHit = true;
                    }
                }
            }
        }
        if (bestPoint == null) {
            return null;
        }
        CollisionInfo info = new CollisionInfo(bestPoint, closest);
        return info;
    }


    /**
     * isEmpty method.
     *
     * @return bolean: true if there are no collide objects, false otherwise.
     */
    public boolean isEmpty() {
        return this.collidable.isEmpty();
    }

    /**
     * drawOn method for the obstacles.
     *
     * @param surface = the canvas.
     */
    public void drawObstacles(DrawSurface surface) {
        for (Collidable collidableObject : this.collidable) {
            Block obstacle = (Block) collidableObject;
            obstacle.drawOn(surface);
        }
    }

    /**
     * Removing collidable method.
     * @param c = the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidable.remove(c);
    }
}

