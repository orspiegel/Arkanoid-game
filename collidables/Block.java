package collidables;

import animation.Velocity;
import gamerun.GameLevel;
import geometry.Ball;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Sprite;
import biuoop.DrawSurface;
import geometry.Rectangle;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
//ID: 318720067

/**
 * Block class to generate a new block.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The class generates a new block by preferences i coosed.
     */
    private Rectangle block;
    private Color color;
    private List<HitListener> hitListeners;
    private int hits;

    /**
     * constructor for the Block class.
     *
     * @param upperLeft = the upper left point
     * @param width     = the with value
     * @param height    = the height value
     * @param color     = the color of the block
     */
    public Block(Point upperLeft, int width, int height, Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * hits Getter.
     * @return = the hits.
     */
    public int getCurrentHits() {
        return this.hits;
    }

    /**
     * block class.
     * constructor of the
     *
     * @param rectangle = the given rectangle
     * @param color = the given color
     * @param hits = the hits amount.
     */
    public Block(Rectangle rectangle, Color color, int hits) {
        this.block = rectangle;
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * constructor to Block class.
     *
     * @param block = the block to add.
     */
    public Block(Rectangle block) {
        this.block = block;
    }

    /**
     * Constructor for thr block.
     */
    public Block() {
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Hit method - returns the new velocity after the hit of the ball.
     *
     * @param collisionPoint  =  the point of the collision
     * @param currentVelocity = the current velocity
     * @param hitter = the hitter
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        if (this.hits > 0) {
            this.hits--;
        }
        double xVel = currentVelocity.getDx();
        double yVel = currentVelocity.getDy();
        // if the collision point is horizontal
        if (collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[0])
                || collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[1])) {
            yVel = -1 * yVel;
        }  //if the ball hit the right / left side of the paddle
        if (collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[2])
                || collisionPoint.pointOnLine(this.getCollisionRectangle().getRibsOfTheRec()[3])) {
            xVel = -1 * xVel;
        }
        this.notifyHit(hitter);
        return new Velocity(xVel, yVel);
    }

    /**
     * getting the collision object shape.
     *
     * @return the block
     */
    @Override
    public geometry.Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * "Draw On" method -> draws the blocks on the screen.
     *
     * @param surface = the canvas
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.block.drawOn(surface, this.color);
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * time passed function - implementation of the interface.
     */
    @Override
    public void timePassed() {
    }

    /**
     * notifying on the hit.
     * @param hitter = the ball that hitting.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * add to game - implementation of the Sprite interface.
     * adding to the game the desired block
     *
     * @param g = the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);

    }

    /**
     * Remover from the game.
     * @param gameLevel = the current game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl = hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl = the hit listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}