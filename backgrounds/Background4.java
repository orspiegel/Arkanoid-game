package backgrounds;

import biuoop.DrawSurface;
import gamerun.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
//ID: 318720067
/**
 * Background4 class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 21.6.2021
 */
public class Background4 implements Sprite {

    private Rectangle rectangle;
    private Color color;

    /**
     * constructor.
     */
    public Background4() {
        this.rectangle = new Rectangle(new Point(10, 50), 780, 580);
        this.color = new Color(72, 194, 224);
    }

    /**
     * drawing of the clouds.
     *
     * @param d = surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        this.rectangle.drawOn(d);
        d.setColor(Color.white);

        d.fillCircle(60, 500, 40);
        d.fillCircle(150, 480, 25);
        d.fillCircle(106, 500, 50);

        d.fillCircle(300, 450, 25);
        d.fillCircle(390, 430, 35);
        d.fillCircle(346, 450, 50);

        d.fillCircle(60, 60, 40);
        d.fillCircle(150, 40, 25);
        d.fillCircle(106, 60, 50);

        d.fillCircle(590, 110, 25);
        d.fillCircle(500, 60, 35);
        d.fillCircle(550, 100, 50);


        d.fillCircle(590, 600, 25);
        d.fillCircle(500, 570, 35);
        d.fillCircle(550, 590, 50);
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
