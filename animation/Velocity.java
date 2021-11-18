package animation;
//ID: 318720067

import geometry.Point;

/**
 * The class generates the velocity of the ball.
 *
 * @author Or Spiegel
 * @version 2.0
 * @since 12.4.2021 -> 28.4 addition
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Point constructor.
     * <p>
     *
     * @param dx - the x axis velocity value.
     *           <p>
     * @param dy - the y axis velocity value.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * "getDx" method.
     * <p>
     * Dx getter.
     * <p>
     *
     * @return the x velocity = Dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * "getDx" method.
     * <p>
     * Dy getter.
     * <p>
     *
     * @return the y velocity = Dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * that method returns the "consider" of the velosities.
     * thus, it returns the square value of the 2 exponent
     * of both of the linear velocities (x axis velocity, y axis velocity)
     * by the arithmetic equation:
     * V tot = (vx^2 + vy ^2)^0.5
     *
     * @return = the vector of the velocity.
     */
    public double getVelocityVector() {
        double expX = this.getDx() * this.getDx();
        double expY = this.getDy() * this.getDy();
        // returns the square root val of the velocity
        return Math.sqrt(expX + expY);
    }

    /**
     * "applyToPoint" method.
     * <p>
     * implementation: Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     * <p>
     *
     * @param p = the current center point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        Point newCenter;
        newCenter = new Point(this.dx + p.getX(), this.dy + p.getY());
        return newCenter;
    }

    /**
     * "fromAngleAndSpeed" method.
     * <p>
     * implementation: converts angle and a linear speed,
     * angular speed, by the trigonometric laws
     * <p>
     *
     * @param angle = the given angle of the movement
     * @param speed = the given linear speed
     *              <p>
     * @return the new angular velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angularDx = Math.sin(Math.toRadians(angle)) * speed;
        double angularDy = Math.cos(Math.toRadians(angle)) * speed;
        Velocity vel = new Velocity(angularDx, angularDy * -1);
        return vel;
    }

}