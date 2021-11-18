package animation;

import biuoop.DrawSurface;
//ID: 318720067
/**
 * Animation interface.
 */
public interface Animation {

    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     * @param d = the surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop() is in charge of stopping condition.
     * @return boolean. true if should stop, either - false.
     */
    boolean shouldStop();
}
