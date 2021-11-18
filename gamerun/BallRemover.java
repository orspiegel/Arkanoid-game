package gamerun;
//ID: 318720067
import collidables.Block;
import geometry.Ball;
import listeners.HitListener;

/**
 * The class is responsible for removing of balls.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 1.6.2021
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * BallRemover method.
     * @param gameLevel = the game parameter.
     * @param remainingBalls = the remaining amount of balls that left.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
        this.gameLevel = gameLevel;
    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit = the block that being hit.
     * @param hitter   = the hitter.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(gameLevel);
    }

    /**
     * remaining balls getter.
     * @return the amount of balls that remained.
     */
    public int getRemaningBalls() {
        return this.remainingBalls.getValue();
    }
}
