package collidables;
//ID: 318720067

import animation.Velocity;
import gamerun.GameLevel;
import sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Rectangle;
import geometry.Ball;
import geometry.Point;

import java.awt.Color;

/**
 * The class generates the moving paddle.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The class generates the paddle of my game.
     * the paddle id kind of an block, that also can move.
     */
    private double rightBoundary;
    private double leftBoundary;
    private Color color;
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int velocity;

    /**
     * Constructor of the paddle.
     *
     * @param shape         = shape.
     * @param color         = the color.
     * @param speed         = the velocity of the paddle.
     * @param keyboard      = the key.
     * @param leftBoundary  = the left Bound.
     * @param rightBoundary = the right Bound.
     */

    public Paddle(Rectangle shape, Color color, int speed,
                  KeyboardSensor keyboard, double leftBoundary, double rightBoundary) {
        this.velocity = speed;
        this.keyboard = keyboard;
        this.rectangle = shape;
        this.color = color;
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
    }

    /**
     * Method that moves the paddle right when the user has pressed the left key.
     */

    public void moveLeft() {

        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();

        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();

        double leftBoundX = upperLeftX - (this.velocity);

        if (upperLeftX > this.leftBoundary) {
            this.rectangle = new Rectangle(new Point(leftBoundX, upperLeftY), (int) width, (int) height);
        } else {
            this.rectangle = new Rectangle(new Point(this.leftBoundary, upperLeftY), (int) width, (int) height);
        }
    }

    /**
     * Method that moves the paddle right when the user has pressed the right key.
     */
    public void moveRight() {
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();

        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();

         double rightBoundX = this.rightBoundary - width;

        if (upperLeftX + width + (this.velocity) < this.rightBoundary) {
            this.rectangle = new Rectangle(new Point(
                    upperLeftX + (this.velocity),
                    upperLeftY), width, height);
        } else {
            this.rectangle = new Rectangle(new Point(rightBoundX,
                    upperLeftY), width, height);
        }
    }

    /**
     * Sprite implementation of the Interface
     * that detects which key that the user pressed on.
     * that method moves the paddle right or left with coordination to his
     * pressing
     */
    public void timePassed() {
        // if the user had pressed the left key
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();

            // if the user had pressed the right key
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * DrawOn - draws the paddle on the surface with a given color.
     *
     * @param d = the canvas
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, this.color);
    }

    /**
     * constructur for the Paddle class.
     *
     * @return - the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Devides the paddle to scopes, which hitting.
     * every different scope giving the ball a matching velocity
     *
     * @param p = hit collision point
     * @param v = current velocity
     * @return the new velocity
     */

    public Velocity scopeVelocity(Point p, Velocity v) {

        double partOfPaddle = this.getCollisionRectangle().getWidth() / 5;

        double coord = p.getX() - this.getCollisionRectangle().getUpperLeft().getX();
        double curVel = v.getVelocityVector();
        Velocity newV = null;
        if (coord <= partOfPaddle && coord >= 0) {
            newV = Velocity.fromAngleAndSpeed(300, curVel);
            //  System.out.println("Zone 1\n");
        } else if (coord > partOfPaddle && coord <= partOfPaddle * 2) {
            //   System.out.println("Zone 2\n");
            newV = Velocity.fromAngleAndSpeed(330, curVel);
        } else if (coord > partOfPaddle * 2 && coord <= partOfPaddle * 3) {
            //  System.out.println("Zone 3\n");
            newV = new Velocity(v.getDx(), v.getDy() * -1);
            //newV = new Velocity(v.getDx(), v.getDy());
        } else if (coord > partOfPaddle * 3 && coord <= partOfPaddle * 4) {
            // System.out.println("Zone 4\n");
            newV = Velocity.fromAngleAndSpeed(30, curVel);
        } else if (coord > partOfPaddle * 4 && coord <= partOfPaddle * 5) {
            //System.out.println("Zone 5\n");
            newV = Velocity.fromAngleAndSpeed(60, curVel);
        }
        Velocity veryNewV = new Velocity(newV.getDx(), newV.getDy());
        return veryNewV;
    }


    /**
     * hit function - > implements the colloidale Interface.
     * gives speed to the ball
     *
     * @param collisionPoint  = the collision point with the paddle
     * @param currentVelocity = the given prev velocity
     * @param ball            = the hitting ball
     * @return new velocity
     */
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        double xVel = currentVelocity.getDx();
        double yVel = currentVelocity.getDy();
        // if the collision point is horizontal
        double coord = this.getCollisionRectangle().getUpperLeft().getX();
        double xCoord = collisionPoint.getX();
        double yCoord = collisionPoint.getY();
        if (collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[0])
                || collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[1])) {
            //if the ball hit the top of the paddle
            if (collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[0])) {
                return this.scopeVelocity(collisionPoint, currentVelocity);
            }
            yVel = -1 * yVel;
        }  //if the ball hit the right / left side of the paddle
        if (collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[2])
                || collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[3])) {
            xVel = -1 * xVel;
        }
        return new Velocity(xVel, yVel);
    }

    // Add this paddle to the game.

    /**
     * add that paddle to our game!
     *
     * @param g = my game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
