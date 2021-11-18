package backgrounds;

import biuoop.DrawSurface;
import gamerun.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
//ID: 318720067
/**
 * Background2 class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 21.6.2021
 */
public class Background2 implements Sprite {
    private Rectangle frame;
    private Color color;

    /**
     * constructor.
     */
    public Background2() {
        this.frame = new Rectangle(new Point(10, 50), 780, 580);
        this.color = Color.white;
    }

    /**
     * DrawOn method.
     *
     * @param surface = the surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.frame.drawOn(surface);
        this.drawSunLight(surface);
        this.drawSun(surface);

    }

    /**
     * drawing the sun light.
     *
     * @param surface = surface.
     */

    public void drawSunLight(DrawSurface surface) {
        surface.setColor(new Color(255, 230, 179));
        surface.drawCircle(130, 200, 70);
        surface.fillCircle(130, 200, 70);
        for (int i = 0; i < 60; i++) {
            surface.drawLine(130, 200, 100 + i * 20, 300);
        }
    }

    /**
     * drawing the sun.
     *
     * @param surface = surface.
     */

    public void drawSun(DrawSurface surface) {
        surface.setColor(new Color(255, 209, 26));
        surface.drawCircle(130, 200, 55);
        surface.fillCircle(130, 200, 55);

        surface.setColor(new Color(255, 255, 102));
        surface.drawCircle(130, 200, 40);
        surface.fillCircle(130, 200, 40);

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
