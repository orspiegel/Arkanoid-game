package geometry;
//ID: 318720067

/**
 * The "Line" class generate a line by coordinates.
 *
 * @author Or Spiegel
 * @version 2.0
 * @since 12.4.2021 -> 28.4 addition
 */
public class Line {
    private Point start;
    private Point end;
    private static int flag = 0;
    // constructors

    /**
     * constructor.
     * <p>
     * method implementation: filling the fields
     * <p>
     *
     * @param start = the start of the line
     * @param end   = the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     * <p>
     * method implementation: filling the fields
     * <p>
     *
     * @param x1 = x value of an anecdote
     * @param y1 = y value of an anecdote
     * @param x2 = x value of an anecdote
     * @param y2 = y value of an anecdote
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * "length" method.
     * <p>
     * implementation: return the length of the line by using distance method
     * <p>
     *
     * @return the length
     */
    // Return the length of the line
    public double length() {
        double len = this.start.distance(this.end);
        return len;
    }

    /**
     * "middle" method.
     * <p>
     * implementation: return the middle of the line,
     * by arithmetic manipulations
     * <p>
     *
     * @return the middle point of the line
     * `
     */
    public Point middle() {
        double xMiddle = (this.start.getX() + this.end.getX()) / 2;
        double yMiddle = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(xMiddle, yMiddle);
        return middle;
    }
    /**
     * "slope" method.
     * <p>
     * implementation: return the slope of the line
     * <p>
     *
     * @return the slope
     */
    public double slope() {
        return (this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX());
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect = the given rectangle
     * @return
     */





    /**
     * "equals" method.
     * <p>
     * implementation: return true is the lines are equal, false otherwise
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return boolean - true of false
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        if (this.start.equals(other.start)
                && this.end.equals(other.end)) {
            return true;
        }
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            if (this.start.equals(this.end) && other.start.equals(other.end)) {
                return false;
            }
            return true;
        }
        //if the start x and start point y are equals
        if (this.start.getX() == other.start.getX()
                && this.start.getY() == other.start.getY()) {
            // if the end x and y coordinates are equals
            if (this.end.getX() == other.end.getX()
                    && this.end.getY() == other.end.getY()) {
                // ill return  true because the lines are perfectly equal
                return true;
            }
        }
        return false;
    }

    /**
     * "b" method.
     * <p>
     * implementation: return the b point of the intersection with the x axis
     * <p>
     *
     * @return the intersection point with the x axis
     */
    public double b() {
        return this.start.getY() - this.start.getX() * this.slope();
    }

    /**
     * "isIntersecting" method.
     * <p>
     *
     * @param other = the other line
     *              <p>
     *              implementation: return true is there is an intersection point, false otherwise
     *              <p>
     * @return true or false
     */
    public Boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null && flag == 0) {
            return false;
        }
        return true;
    }

    /**
     * "intersectionWith" method.
     * <p>
     * implementation: return the intersection point of the lines
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return the intersection point between the lines
     */
    public Point intersectionWith(Line other) {
        //initialization of the flag to zero. the flag checks the
        //situation of line that contains the other line.
        flag = 0;
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            if (this.twoPoints(other)) {
                return this.start;
            } else {
                return null;
            }
        }
        // if it's a point and an infinity slope
        if (this.start.equals(this.end) && other.checkInf()) {
            return this.pointInf(other);
        }
        if (other.start.equals(other.end) && this.checkInf()) {
            return other.pointInf(this);
        }
        // if it's a point and a line
        if (this.start.equals(this.end)) {
            if (this.pointLine(other)) {
                return this.start;
            } else {
                return null;
            }
        }
        // if it's a line and a point
        if (other.start.equals(other.end)) {
            if (other.pointLine(this)) {
                return other.start;
            } else {
                return null;
            }
        }
        // if it's both infinity lines
        if (this.checkInf() && other.checkInf()) {
            return this.twoInfs(other);
        }
        // if it's a infinity slope and a normal line
        if (this.checkInf()) {
            return this.checkWhenInf(other);
        }
        // if it's a normal line and a infinity slope
        if (other.checkInf()) {
            return other.checkWhenInf(this);
        }
        // if it's the same line
        if (this.sameLines(other)) {
            flag = 1;
            return null;
        }
        if (this.start.equals(other.start) && !(this.end.equals(other.end))) {
            Point tmp = isOnePointIntersect(other);
            if (tmp != null) {
                return tmp;
            }
            if (this.isOneIn(other) != null || other.isOneIn(this) != null) {
                flag = 1;
            }
            if (flag == 1) {
                return null;
            }
            return this.start;
        }
        if (this.start.equals(other.end) && !(this.end.equals(other.start))) {
            Point tmp = isOnePointIntersect(other);
            if (tmp != null) {
                return tmp;
            }
            if (this.isOneIn(other) != null || other.isOneIn(this) != null) {
                flag = 1;
            }
            if (flag == 1) {
                return null;
            }
            return this.start;
        }
        if (this.end.equals(other.start) && !(this.start.equals(other.end))) {
            Point tmp = isOnePointIntersect(other);
            if (tmp != null) {
                return tmp;
            }
            if (this.isOneIn(other) != null || other.isOneIn(this) != null) {
                flag = 1;
            }
            if (flag == 1) {
                return null;
            }
            return this.end;
        }

        if (this.end.equals(other.end) && !(this.start.equals(other.start))) {
            if (this.isOneIn(other) != null || other.isOneIn(this) != null) {
                flag = 1;
            }
            if (flag == 1) {
                return null;
            }
            return this.end;
        }
        // if it's two normal lines
        return twoNorms(other);
    }

    /**
     * "pointInf" method.
     * <p>
     * implementation: return the intersection point of the lines
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return the intersection point between the lines
     */
    public Point pointInf(Line other) {
        if (isItIn(this.start.getY(), other.start.getY(), other.end.getY())) {
            return this.start;
        }
        return null;
    }

    /**
     * "twoNorms" method.
     * <p>
     *
     * @param other = the other line
     *              <p>
     *              implementation: enter to functions with coordination
     *              tho the slope
     *              <p>
     * @return the point between the lines
     */
    public Point twoNorms(Line other) {
        // check if they have the same slope
        if (this.slope() == other.slope()) {
            return this.sameSlopes(other);
        }
        return this.interTwoNorms(other);
    }

    /**
     * "interTwoNorms" method.
     * <p>
     * implementation: the intersection point of the lines if
     * it is a normal situation
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return the intersection point between the lines
     */
    public Point interTwoNorms(Line other) {
        if ((this.slope() - other.slope() == 0)) {
            // there is no intersection
            return null;
        }
        //if the tips are touching
        if (this.isTheTips(other) != null && this.slope() != other.slope()) { //to add if it is now the same slope
            return this.isTheTips(other);
        }
        //
        double xVal = (other.b() - this.b()) / (this.slope() - other.slope());
        if (!this.betweenFourPointsX(other, xVal)) {
            return null;
        }
        double yVal = this.slope() * xVal + this.b();
        if (!this.betweenFourPointsY(other, yVal)) {
            return null;
        }
        Point inter = new Point(xVal, yVal);
        return inter;
    }

    /**
     * "betweenFourPointsX" method.
     * <p>
     * implementation: return true if x coordinate of the
     * point is between a given scope of x values, false otherwise
     * <p>
     *
     * @param other = the other line
     * @param xVal  = the given value of x of the point
     *              <p>
     * @return true / false
     */
    public boolean betweenFourPointsX(Line other, double xVal) {
        if (isItIn(xVal, this.start.getX(), this.end.getX())
                && isItIn(xVal, other.start.getX(), other.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * "betweenFourPointsY" method.
     * <p>
     * implementation: return true if y coordinate of the
     * point is between a given scope of y values, false otherwise
     * <p>
     *
     * @param other = the other line
     * @param yVal  = the given y value of the point
     *              <p>
     * @return true / false
     */
    public boolean betweenFourPointsY(Line other, double yVal) {
        if (isItIn(yVal, this.start.getY(), this.end.getY())
                && isItIn(yVal, other.start.getY(), other.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * "sameSlopes" method.
     * <p>
     * implementation: return the intersection point of the lines
     * when their slopes are equal
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return the point, or null if theres no point
     */
    public Point sameSlopes(Line other) {
        // check all the possible outcomes for slopes
        // which means that the lines don't have anything to do with each other
        if (this.b() != other.b()) {
            return null;
        }
        //if both of the points of other are in this
        if (this.isBothIn(other)) {
            return null;
        }
        //if both of the points of this are in other
        if (other.isBothIn(this)) {
            return null;
        }
        // check if only some of the line is in the other line
        // check this by other
        if (this.isOneIn(other) != null) {
            flag = 1;
            return null;
        }
        //check other by this
        if (other.isOneIn(this) != null) {
            flag = 1;
            return null;
        }
        // check between this and other
        if (this.isTheTips(other) != null) {
            return this.isTheTips(other);
        }
        // check between other and this
        if (other.isTheTips(this) != null) {
            return other.isTheTips(this);
        }
        return null;
    }

    /**
     * "isTheTips" method.
     * <p>
     * implementation: return the intersection point of the lines
     * when the tips are touching each other
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return the point, or null if theres no point
     */
    public Point isTheTips(Line other) {
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        return null;
    }

    /**
     * "checkInf" method.
     * <p>
     * implementation: check if the slopes are infinity slope.
     * <p>
     *
     * @return true if the slope == +/- infinity, false otherwise
     */
    public boolean checkInf() {
        if (this.slope() == Double.POSITIVE_INFINITY
                || this.slope() == Double.NEGATIVE_INFINITY) {
            return true;
        }
        return false;
    }

    /**
     * "twoPoints" method.
     * <p>
     * implementation: check if the this line start equals to the other start
     * <p>
     *
     * @param other = the other line to compare to
     *              <p>
     * @return true if the point is equal / false otherwise
     */
    public boolean twoPoints(Line other) {
        // checking if the two points are really the same point
        if (this.start.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * "pointLine" method.
     * <p>
     * implementation: check if the line is actually an point
     * <p>
     *
     * @param other = the other line to compare to
     *              <p>
     * @return true if the line is a point / false otherwise
     */
    public boolean pointLine(Line other) {
        // if the point is on the line, we have a winner
        double theVal = this.getYForX(other);
        if (this.start.getY() - this.getYForX(other) < 0.000001
                && this.start.getY() - this.getYForX(other) > -0.000001) {
            return true;
        }
        return false;
    }

    /**
     * "getYForX" method.
     * <p>
     * implementation: getting the matching y point for
     * x value one the same infinity line
     *<p>
     * @param other = the other line to compare to
     *              <p>
     * @return the matching y point
     */
    public double getYForX(Line other) {
        return this.start.getX() * other.slope() + other.b();
    }

    /**
     * "checkWhenInf" method.
     * <p>
     * implementation: checking the intersection point when the slope is infinity
     * <p>
     *
     * @param other = the other line to compare to
     *              <p>
     * @return inter = the intersection point
     */
    public Point checkWhenInf(Line other) {
        // check if x value is in the line bound
        if (isItIn(this.start.getX(), other.start.getX(), other.end.getX())) {
            // if so, get the y value
            double yVal = this.getYForX(other);
            // check if the y value is inside the bound of the |
            if (isItIn(yVal, this.start.getY(), this.end.getY())) {
                // is so, create a new point
                Point inter = new Point(this.start.getX(), yVal);
                return inter;
            }
        }
        return null;
    }

    /**
     * "isItIn" method.
     * <p>
     * implementation: check if a given value is between 2 values.
     * <p>
     *
     * @param p  = the anecdote i want to check about
     * @param l1 = one of the outer anecdotes
     * @param l2 = one of the outer anecdotes
     *           <p>
     * @return true if the value is between, false otherwise
     */
    public boolean isItIn(double p, double l1, double l2) {
        if (p <= l1 && p >= l2) {
            return true;
        }
        if (p >= l1 && p <= l2) {
            return true;
        }
        return false;
    }

    /**
     * "twoInfs" method.
     * <p>
     * implementation: check if two of the lines slopes are infinty
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return the intersection point if the's a one
     */
    public Point twoInfs(Line other) {
        // check if the lines live in different x values
        if (this.start.getX() != other.start.getX()) {
            return null;
        }
        // check if one line is in the bigger line
        // if this < other
        if (this.isBothIn(other)) {
            return null;
        }
        // this > other
        if (other.isBothIn(this)) {
            return null;
        }
        // if y val is intersecting the start
        if (isYTouching(other, this.start.getY())) {
            return this.start;
        }
        // if y val is intersecting the end
        if (isYTouching(other, this.end.getY())) {
            return this.end;
        }
        // check if only some of the line is in the other line
        if (this.isOneIn(other) != null) {
            return null;
        }
        //check other by this
        if (other.isOneIn(this) != null) {
            return null;
        }
        // check if the tips touch
        return null;
    }

    /**
     * "isYTouching" method.
     * <p>
     * implementation: check if a value is the y value of the start/end of the line
     * <p>
     *
     * @param other = the other line
     * @param y     = the value
     *              <p>
     * @return true if is touching, false otherwise
     */
    public boolean isYTouching(Line other, double y) {
        if (y == other.start.getY() || y == other.end.getY()) {
            return true;
        }
        return false;
    }

    /**
     * "isOnePointIntersect" method.
     * <p>
     * implementation: check the lines are sharing one point (start or end).
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return the point they sharing, or null if they don't
     */
    public Point isOnePointIntersect(Line other) {
        if (this.start.getX() <= this.end.getX()) {
            if (other.start.getX() <= other.end.getX()) { // case 1
                if (this.end.equals(other.start)) {
                    return this.end;
                }
            } else if (other.start.getX() >= other.end.getX()) { // case 2
                if (this.end.equals(other.start)) {
                    return this.end;
                }
            }
        } else if (this.start.getX() >= this.end.getX()) {
            if (other.start.getX() <= other.end.getX()) {
                if (this.start.equals(other.start)) { // case 3
                    return this.start;
                }
            } else if (other.start.getX() >= other.end.getX()) {
                if (this.end.equals(other.start)) {
                    return this.end;
                }
            }
        }
        return null;
    }

    /**
     * "isBothIn" method.
     * <p>
     * implementation: check if both of the coordinates of the
     * other line are on the scope of this line.
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return true if the line is on the scope, false otherwise.
     */
    public boolean isBothIn(Line other) {
        if (isItIn(this.start.getY(), other.start.getY(), other.end.getY())
                && isItIn(this.end.getY(), other.start.getY(), other.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * "isOneIn" method.
     * <p>
     * implementation: check if one of the coordinates of the
     * other line is on the scope of this line.
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return true if the one of the points is on the scope, false otherwise.
     */
    public Point isOneIn(Line other) {
        Point tmp = isOnePointIntersect(other);
        if (tmp != null) {
            return tmp;
        }
        if (isItIn(this.start.getY(), other.start.getY(), other.end.getY())
                && !isItIn(this.end.getY(), other.start.getY(), other.end.getY())) {
            return this.start;
        }
        if (!isItIn(this.start.getY(), other.start.getY(), other.end.getY())
                && isItIn(this.end.getY(), other.start.getY(), other.end.getY())) {
            return this.end;
        }
        if (isItIn(this.start.getY(), other.start.getY(), other.end.getY())
                && isItIn(this.end.getY(), other.start.getY(), other.end.getY())) {
            return this.end;
        }
        return null;
    }

    /**
     * "sameLines" method.
     * <p>
     * implementation: check if both of the lines are the same line
     * <p>
     *
     * @param other = the other line
     *              <p>
     * @return true if the lines are equal, false otherwise.
     */
    public boolean sameLines(Line other) {
        // returns true if the lines are the same lines
        if (other == null) {
            return false;
        }
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.end.equals(other.start) && this.start.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * "start" method.
     * <p>
     * implementation: return this start. => getter
     * <p>
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * "end" method.
     * <p>
     * implementation: return this end. => getter
     * <p>
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * "getFlag" method.
     * <p>
     * implementation: return the flag
     * <p>
     *
     * @return the flag
     */
    public int getFlag() {
        return Line.flag;
    }

    /**
     * "setFlag" method.
     * <p>
     * implementation: setter of the flag
     * <p>
     *
     * @param myFlag = the flag
     */
    public void setFlag(int myFlag) {
        Line.flag = myFlag;
    }
}