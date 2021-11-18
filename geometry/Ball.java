package geometry;
//ID: 318720067

import animation.Velocity;
import collidables.CollisionInfo;
import gamerun.GameLevel;
import gamerun.GameEnvironment;

import sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class generates a ball.
 *
 * @author Or Spiegel
 * @version 2.0
 * @since 29.4.2021 -> 28.4 addition
 */
public class Ball implements Sprite {
    /**
     * The class generates a ball, which i be using.
     * at the next classes and methods at my program.
     */
    private Point center;
    private int r;
    private Color color;
    private Velocity v = new Velocity(2, 2);
    private Point lowerLimit;
    private Point upperLimit;
    private GameEnvironment env;
    private static final double EPS = 0.000000000001;
    private Point prev;


    /**
     * Ball constructor.
     * <p>
     *
     * @param center - the x,y axis anecdote.
     *               <p>
     * @param r      - radius.
     *               <p>
     * @param color  - color of the ball.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Ball constructor.
     * <p>
     *
     * @param x     = the x anecdote.
     *              <p>
     * @param y     = the y anecdote
     *              <p>
     * @param r     = radius.
     *              <p>
     * @param color = color of the ball.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * constructor to Ball class.
     *
     * @param x     = x coord
     * @param y     = y coord
     * @param r     = radius
     * @param color = the color
     * @param env   = environment
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment env) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.env = env;
    }

    /**
     * Ball constructor.
     * <p>
     *
     * @param x          = the x anecdote.
     *                   <p>
     * @param y          = the y anecdote
     *                   <p>
     * @param radius     = radius.
     *                   <p>
     * @param color      = color of the ball.
     *                   <p>
     * @param lowerLimit = the lower left limit point of the surface
     *                   <p>
     * @param upperLimit = the upper right limit point of the surface
     */
    public Ball(int x, int y, int radius, Color color,
                Point lowerLimit, Point upperLimit) {
        this(new Point(x, y), radius, color);
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    /**
     * Ball constructor.
     * <p>
     *
     * @param center     = middle of the ball coordinate.
     *                   <p>
     * @param r          = radius.
     *                   <p>
     * @param color      = color of the ball.
     *                   <p>
     * @param lowerLimit = the lower left limit point of the surface
     *                   <p>
     * @param upperLimit = the upper right limit point of the surface
     */
    public Ball(Point center, int r, Color color,
                Point lowerLimit, Point upperLimit) {
        this(center, r, color);
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    // accessors

    /**
     * Ball constructor.
     * <p>
     *
     * @return x = the x of the middle point of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Ball constructor.
     * <p>
     *
     * @return y = the y of the middle point of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Ball constructor.
     * <p>
     *
     * @return r = the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * "setSize" method.
     * <p>
     *
     * @param rad = the new radius to set.
     */
    public void setSize(int rad) {
        this.r = rad;
    }

    /**
     * "getColor" method.
     * <p>
     * method implementation: returns the color, getter.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * "setVelocity" method.
     * <p>
     * method implementation: setts the velocity field, setter.
     * <p>
     *
     * @param vel = velocities sum
     */
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }

    /**
     * "setVelocity" method.
     * <p>
     * method implementation: setts the velocity field, setter.
     * <p>
     *
     * @param dx = x axis velocity
     *           <p>
     * @param dy =  y axis velocity
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * "getVelocity" method.
     * <p>
     * method implementation: returns the velocity, getter.
     * <p>
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * accessor - "get center". getter.
     * <p>
     *
     * @return the center: a point.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * accessor - "set center". setter.
     * <p>
     *
     * @param x = new center x point
     * @param y = new center y point
     */
    public void setCenter(int x, int y) {
        this.center = new Point(x, y);
    }

    /**
     * "drawOn" method.
     * <p>
     * method implementation: drawing a given DrawSurface
     * <p>
     *
     * @param surface = the drawing "canvas"
     */
    public void drawOn(DrawSurface surface) {
        DrawSurface b = surface;
        b.setColor(getColor());
        if (this.getSize() < 0) {
            this.setSize(0);
        } else if (this.getSize() > surface.getWidth() / 2) {
            this.setSize(surface.getWidth() / 2 - 2);
        }
        b.drawCircle(getX(), getY(), getSize());
        b.fillCircle(getX(), getY(), getSize());
    }

    /**
     *
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * "limitKeeper" method.
     * <p>
     * method implementation: keeps the ball at the boundaries
     * that they are the surface width and height.
     * changes velocity when touches the limits
     */
    public void limitKeeper() {
        int hasChanged = 0; // a new flag
        double xVelocity = this.getVelocity().getDx();
        double yVelocity = this.getVelocity().getDy();
        double width = this.upperLimit.getX();
        double height = this.upperLimit.getY();
        double xAxis = this.lowerLimit.getX();
        double yAxis = this.lowerLimit.getY();
        double radius = this.getSize();
        //if the ball access the bounds of the drawing
        this.center = this.getVelocity().applyToPoint(this.center);
        if (this.center.getX() + radius + xVelocity >= width) {
            this.center.setX(width - radius);
            xVelocity = -xVelocity;
            hasChanged++;
            //if the ball gets to zero axes of the drawing
        } else if (this.center.getX() + xVelocity - radius <= xAxis) {
            this.center.setX(xAxis + radius);
            xVelocity = -xVelocity;
            hasChanged++;
        }
        //if the ball access the bounds of the drawing
        if (this.center.getY() + radius + yVelocity >= height) {
            this.center.setY(height - radius);
            yVelocity = -yVelocity;
            hasChanged++;
            //if the ball gets to zero axes of the drawing
        } else if (this.center.getY() + yVelocity - radius <= yAxis) {
            this.center.setY(yAxis + radius);
            yVelocity = -yVelocity;
            hasChanged++;
        }

        this.setVelocity(xVelocity, yVelocity);
    }

    /**
     * the trajectory method.returns the linear equation.
     * graph of the line
     * * <p>
     *
     * @return the line trajectory
     */
    public Line getTrajectory() {
        double linear = Math.sqrt((this.env.getLowerLeftEdge().getX()
                - this.env.getUpperRightEdge().getX()) * (this.env.getLowerLeftEdge().getX()
                - this.env.getUpperRightEdge().getX() + ((this.env.getLowerLeftEdge().getY()
                - this.env.getUpperRightEdge().getY())
                * (this.env.getLowerLeftEdge().getY() - this.env.getUpperRightEdge().getY()))));
        return new Line(this.getCenter(), new Point(this.getCenter().getX()
                + linear * this.getVelocity().getDx(),
                this.getCenter().getY() + linear * this.getVelocity().getDy()));
    }

    /**
     * setter that setting the gameEnveironment.
     *
     * @param gameEnvironment = the given gameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.env = gameEnvironment;
    }

    /**
     * method that responsible for changing the ball object.
     * actually, the animation function.
     * it will change such that its movement will be defined by the obstacles in the game environment.
     */
    public void moveOneStep() {
        //the linear equasion for the way that the ball passes
        Line trajectory = this.getTrajectory();
        //the optimal closest collision point
        CollisionInfo infoOfColl = this.env.getClosestCollision(trajectory);
        if (!this.env.isEmpty() && env != null && infoOfColl.collisionPoint().pointOnLine(trajectory)) {
            if (infoOfColl != null) {
                if (infoOfColl.collisionPoint().distance(this.center) - this.v.getVelocityVector() <= this.getSize()) {
                    this.v = infoOfColl.collisionObject().hit(this, infoOfColl.collisionPoint(), this.v);
                }
            }
            //new coordinate for the center
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * remover from the game.
     * @param g = the current game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

