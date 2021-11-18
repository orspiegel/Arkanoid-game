package backgrounds;

import biuoop.DrawSurface;
import gamerun.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
//ID: 318720067
/**
 * Background1 class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 21.6.2021
 */
public class Background1 implements Sprite {
    private Rectangle frame;
    private Color color;

    /**
     * constructor.
     */
    public Background1() {
        this.frame = new Rectangle(new Point(10, 50), 780, 580);
        this.color = Color.black;
    }

    /**
     * Drawing the given geometry objects.
     *
     * @param surface = the surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.frame.drawOn(surface);
        surface.setColor(Color.blue);
        this.drawScopeForBrick(surface);

    }

    /**
     * drawing of the blue circles and lines for the red brick.
     *
     * @param surface = surface.
     */
    public void drawScopeForBrick(DrawSurface surface) {
        surface.drawLine(520, 210, 420, 210);
        surface.drawLine(280, 210, 380, 210);
        surface.drawLine(400, 90, 400, 190);
        surface.drawLine(400, 350, 400, 250);
        surface.drawCircle(400, 220, 80);
        surface.drawCircle(400, 220, 45);
        surface.drawCircle(400, 220, 25);

    }

    /**
     * timePassed method.
     */
    @Override
    public void timePassed() {
    }

    /**
     * add the background.
     *
     * @param g = game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
