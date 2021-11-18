package levels;

import animation.Velocity;
import backgrounds.Background1;
import collidables.Block;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
//ID: 318720067
/**
 * Level1 class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 21.6.2021
 */
public class Level1 implements LevelInformation {
    private Sprite background1;

    /**
     * constructor.
     */
    public Level1() {
        this.background1 = new Background1();
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new LinkedList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocity.add(new Velocity(i, -2));
        }
        return velocity;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct hit";
    }

    @Override
    public Sprite getBackground() {
        return this.background1;
    }

    @Override
    public List<Block> blocks() {

        List<Block> blocks = new LinkedList<Block>();
        Block b = new Block(new Point(390, 210), 20, 20, Color.red);
        blocks.add(b);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}

