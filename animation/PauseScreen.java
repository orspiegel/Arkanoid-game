package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
//ID: 318720067
/**
 * PauseScreen animation class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * pause screen animation.
     *
     * @param k = key
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     *
     * @param d = the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
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
