package geometry;
//ID: 318720067

/**
 * The class generates a point, and manipulate balls coordinates.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 12.4.2021
 */
public class Point {
    private static final double EPSILON = 0.0000001;
    private double x;
    private double y;

    /**
     * Point constructor.
     * <p>
     *
     * @param x - the x axis anecdote of the point.
     *          <p>
     * @param y - the y axis anecdote of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * "distance" method.
     * <p>
     *
     * @param other - another point.
     *              <p>
     *              method implementation: the method checks by algebraic calculation
     *              the distance between 2 points
     *              <p>
     * @return the distance between this point to the argument passed point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        double sum = ((dx * dx) + (dy * dy));
        //the distance
        return Math.sqrt(sum);
    }

    /**
     * "equals" method.
     * <p>
     *
     * @param other - another point.
     *              <p>
     *              method implementation: the method checks by algebraic calculation
     *              if this point is equal to the argument passed point
     *              <p>
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        if ((this.x - other.x) < EPSILON && (this.x - other.x) > -EPSILON
                && (this.y - other.y < EPSILON && (this.y - other.y) > -EPSILON)) {
            return true;
        }
        return false;
    }

    /**
     * "getX" method.
     * <p>
     * method implementation: returns the x coordinate of this point, getter.
     * <p>
     *
     * @return x val
     */
    public double getX() {
        return this.x;
    }

    /**
     * "getY" method.
     * <p>
     * method implementation: returns the y coordinate of this point, getter.
     * <p>
     *
     * @return y val
     */
    public double getY() {
        return this.y;
    }

    /**
     * "setX" method.
     * <p>
     * method implementation: set the x field, setter.
     * <p>
     *
     * @param pX = the new x value to set
     *           <p>
     */
    public void setX(double pX) {
        this.x = pX;
    }

    /**
     * "setY" method.
     * <p>
     * method implementation: set the y field, setter.
     * <p>
     *
     * @param pY = the new y value to set
     */
    public void setY(double pY) {
        this.y = pY;
    }

    /**
     * is the point on the line.
     *
     * @param l = the line
     * @return true if the point on the line, false otherwise.
     */
    public boolean pointOnLine(Line l) {
        return this.distance(l.start()) + this.distance(l.end()) - l.start().distance(l.end()) < 0.000001
                && l.start().distance(l.end()) - this.distance(l.start()) + this.distance(l.end()) > -0.000001;
    }
}
