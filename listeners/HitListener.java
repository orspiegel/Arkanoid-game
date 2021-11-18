package listeners;
//ID: 318720067
import collidables.Block;
import geometry.Ball;
/**
 * HitListener interface.
 * @author Or Spiegel
 * @version 1.0
 * @since 2.6.2021
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit = the block that being hit.
     * @param hitter = the hitter.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
