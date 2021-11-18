package geometry;
// 318720067

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Rectangle class to generate a new Rectangle.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public class Rectangle {
    /**
     * Block class to generate a new Rectangle by the desired preferences.
     */
    private Point upperLeft;
    private int width;
    private int height;
    private Color color;
    private static final int RIBS = 4;
    private static final double EPSILON = 0.000001;

    /**
     * constructor to Rectangle class.
     * Creates a new rectangle with location and width/height.
     *
     * @param upperLeft = the upper left point
     * @param width     = the width value
     * @param height    = the height value
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * constructor to Rectangle class.
     *
     * @param upperLeft = the upper left point
     * @param width     = the width value
     * @param height    = the height value
     * @param color     = the desired color
     */
    public Rectangle(Point upperLeft, int width, int height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }


    // Returns the upper-left point of the rectangle.

    /**
     * getter.
     *
     * @return the upperLeft point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getter.
     *
     * @return the upperRight point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point((int) upperLeft.getX() + this.width, (int) this.upperLeft.getY());
    }

    /**
     * getter.
     *
     * @return the lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return new Point((int) this.upperLeft.getX(), (int) this.upperLeft.getY() + this.height);
    }

    /**
     * getter.
     *
     * @return the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point((int) this.upperLeft.getX() + this.width, (int) this.upperLeft.getY() + this.height);
    }

    /**
     * get lines is returning an array with the four rectangle lines.
     *
     * @return the array of the lines
     */
    public Line[] getRibsOfTheRec() {
        Point rightTop = getUpperRight();
        Point rightDown = getLowerRight();
        Point leftDown = getLowerLeft();
        Point p1 = new Point(upperLeft.getX(), upperLeft.getY() + EPSILON);
        Point p2 = new Point(leftDown.getX(), leftDown.getY() - EPSILON);
        Point p3 = new Point(rightTop.getX(), rightTop.getY() + EPSILON);
        Point p4 = new Point(rightDown.getX(), rightDown.getY() - EPSILON);
        Line[] ribsArr = new Line[RIBS];
        // up
        ribsArr[0] = new Line(upperLeft, rightTop);
        // down
        ribsArr[1] = new Line(leftDown, rightDown);
        // left
        ribsArr[2] = new Line(p1, p2);
        // right
        ribsArr[3] = new Line(p3, p4);

        return ribsArr;
    }

    /**
     * returns the point list of the possible intersection points with the collidable objects.
     *
     * @param line = the current line (rib) of the rectangle
     * @return the array of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] lineOfRecArr = this.getRibsOfTheRec();
        ArrayList<Point> intersections = new ArrayList<>();
        for (var j = 0; j < RIBS; j++) {
            Point potentialIntersectionP = lineOfRecArr[j].intersectionWith(line);
            // if there are possible intersection points, ill add them to the list.
            if (potentialIntersectionP != null) {
                intersections.add(potentialIntersectionP);
            }
        }
        return intersections;

    }

    /**
     * drawOn method.
     * DrawOn method - GUI Visual implementation, which drawing the
     * rectangle on the screen.
     *
     * @param surface = the canvas
     * @param c       = the desired color
     */
    public void drawOn(DrawSurface surface, Color c) {
        int getWidth = this.width;
        int getHeight = this.height;
        DrawSurface b = surface;
        int upperLX = (int) this.upperLeft.getX();
        int upperLY = (int) this.upperLeft.getY();
        b.setColor(c);
        b.fillRectangle(upperLX, upperLY,
                getWidth, getHeight);

        b.setColor(Color.black);
        b.drawRectangle(upperLX, upperLY,
                getWidth, getHeight);
    }

    /**
     * getter.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getter.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * drawOn method.
     *
     * @param surface = the surface
     */

    public void drawOn(DrawSurface surface) {
        int getWidth = this.width;
        int getHeight = this.height;
        int upperLX = (int) this.upperLeft.getX();
        int upperLY = (int) this.upperLeft.getY();
        surface.fillRectangle(upperLX, upperLY,
                getWidth, getHeight);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(upperLX, upperLY,
                getWidth, getHeight);
    }
}
