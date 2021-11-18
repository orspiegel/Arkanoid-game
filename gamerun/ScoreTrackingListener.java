package gamerun;
//ID: 318720067
import collidables.Block;
import geometry.Ball;
import listeners.HitListener;

/**
 * The "ScoreTrackingListener" class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 1.6.2021
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * The constructor.
     * @param scoreCounter = the counter for the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hit event method.
     * @param beingHit = the block that being hit.
     * @param hitter = the hitter.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
