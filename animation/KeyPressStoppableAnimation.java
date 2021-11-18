package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
//ID: 318720067
/**
 * Decorator-class that will wrap an existing animation and add a
 * "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean stop;
    private Animation animation;

    private KeyboardSensor keySensor;
    private String pressedKey;

    /**
     * constructor.
     * @param animation = animation.
     * @param pressedKey = the key that pressed.
     * @param keySensor = the sensor.
     */
    public KeyPressStoppableAnimation(Animation animation, String pressedKey,
                                      KeyboardSensor keySensor) {
        this.stop = false;
        this.animation = animation;
        this.keySensor = keySensor;
        this.pressedKey = pressedKey;
    }

    /**
     * do one frame.
     * @param d = the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keySensor.isPressed(pressedKey)) {
            this.stop = true;
        }
    }

    /**
     * should stop method.
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
