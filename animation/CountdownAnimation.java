package animation;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.Color;
//ID: 318720067
/**
 * Countdown Animation class.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop = false;

    /**
     * Constructor.
     *
     * @param numOfSeconds = number of seconds.
     * @param countFrom    = count from X seconds.
     * @param gameScreen   = the sceen.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * @param d = the surface.
     */
    public void doOneFrame(DrawSurface d) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        long sleepFor = (long) (1000 / numOfSeconds);
        gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 2 - 8, d.getHeight() / 2 + 12, "" + this.countFrom, 50);
        d.setColor(Color.pink);
        d.drawText(d.getWidth() / 2 - 7, d.getHeight() / 2 + 10, "" + this.countFrom, 40);
        d.setColor(Color.white);
        d.drawText(d.getWidth() / 2 - 6, d.getHeight() / 2 + 10, "" + this.countFrom, 32);

        sleeper.sleepFor(sleepFor);
        this.countFrom--;
        if (this.countFrom < 0) {
            this.stop = true;
        }
    }

    /**
     * Should stop animation.
     *
     * @return boolean - if this should stop - true. else - false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
