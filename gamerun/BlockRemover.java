package gamerun;
//ID: 318720067

import collidables.Block;
import geometry.Ball;
import listeners.HitListener;

/**
 * The class is responsible for removing of blocks.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 1.6.2021
 */

public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    private Counter score;

    /**
     * Constructor.
     *
     * @param gameLevel          = the game parameter.
     * @param removedBlocks = the blocks to remove.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
        this.score = new Counter();
    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit = the block that being hit.
     * @param hitter   = the hitter.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCurrentHits() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.score.increase(5);
            this.remainingBlocks.decrease(1);
        }
    }
}
