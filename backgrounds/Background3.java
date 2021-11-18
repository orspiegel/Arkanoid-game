package backgrounds;

import biuoop.DrawSurface;
import gamerun.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
//ID: 318720067
/**
 * Background3 class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 21.6.2021
 */
public class Background3 implements Sprite {
    private Rectangle frame;
    private Color color;

    /**
     * constructor.
     */
    public Background3() {
        this.frame = new Rectangle(new Point(10, 50), 780, 580);
        this.color = new Color(36, 143, 36);
    }

    /**
     * Drawing of the building.
     * @param surface = the surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.frame.drawOn(surface);

        surface.setColor(Color.gray);
        surface.fillRectangle(100, 200, 10, 300);

        surface.setColor(new Color(61, 61, 92));
        surface.fillRectangle(90, 300, 30, 300);


        surface.setColor(new Color(255, 255, 102));
        surface.fillCircle(106, 200, 15);

        surface.setColor(new Color(255, 100, 102));
        surface.fillCircle(106, 200, 10);

        surface.setColor(new Color(255, 222, 200));
        surface.fillCircle(106, 200, 4);


        surface.setColor(Color.black);

        surface.fillRectangle(50, 350, 100, 600);
        surface.setColor(Color.white);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 6; j++) {
                surface.fillRectangle(57 + 15 * j, 360 + i * 35, 10, 30);
            }
        }


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