package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
//ID: 318720067
/**
 * Once the game is over (either the player died.
 * or he managed to clear all the levels), we will display the final score.
 */
public class WinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * constructor.
     * @param keyboard = key
     * @param score = the score
     */
    public WinScreen(KeyboardSensor keyboard, int score) {
        this.keyboard = keyboard;
        this.stop = false;
        this.score = score;
    }


    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     *
     * @param d = the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "you won! your score is " + score, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }

    }

    /**
     * setter.
     * @param x = thr score that earned.
     */
    public void setScore(int x) {
        this.score = x;
    }


    /**
     * shouldStop() is in charge of stopping condition.
     *
     * @return boolean. true if should stop, either - false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
