package levels;

import animation.Velocity;
import collidables.Block;
import sprites.Sprite;

import java.util.List;
//ID: 318720067
/**
 * interFace of the level information.
 * contains the settings of every level
 * that will implement this interface.
 */
public interface LevelInformation {
    /**
     * The number of the balls at that current level.
     *
     * @return the number.
     */
    int numberOfBalls();

    /**
     * Initial velocities method.
     *
     * @return the lost of balls current level velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the velocity of the paddle.
     *
     * @return the speed
     */
    int paddleSpeed();

    /**
     * the width of the paddle.
     *
     * @return the width
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the name string.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return sprite.
     */
    Sprite getBackground();


    /**
     * Blocks list.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list of blocks.
     */
    List<Block> blocks();


    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the number.
     */
    int numberOfBlocksToRemove();

    /**
     * getter.
     * @return the level.
     */
    int getLevel();
}
