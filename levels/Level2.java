package levels;

import animation.Velocity;
import backgrounds.Background2;
import collidables.Block;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
//ID: 318720067
/**
 * Level2 class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 21.6.2021
 */
public class Level2 implements LevelInformation {
    private Sprite background;

    /**
     * constructor.
     */
    public Level2() {
        this.background = new Background2();
    }

    @Override
    public int numberOfBalls() {
        return 10;
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
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        int blockSize = (int) (780 / 15);
        List<Block> blockss = new LinkedList<>();
        Color[] colorsArr = {Color.RED, Color.red, Color.ORANGE, Color.ORANGE, Color.yellow,
                Color.YELLOW, Color.GREEN, Color.green, Color.green, Color.BLUE,
                Color.blue, Color.PINK, Color.pink, Color.CYAN, Color.CYAN};
        double p = 10;
        for (int i = 0; i < 15; i++) {
            blockss.add(new Block(new Point(p, 300), blockSize, 25, colorsArr[i]));
            p += 780 / 15;
        }
        return blockss;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public int getLevel() {
        return 2;
    }
}
