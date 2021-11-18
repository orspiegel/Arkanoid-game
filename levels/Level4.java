package levels;

import animation.Velocity;
import backgrounds.Background4;
import collidables.Block;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//ID: 318710067
/**
 * Level4 class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 21.6.2021
 */
public class Level4 implements LevelInformation {
    private Sprite background;

    /**
     * constructor.
     */
    public Level4() {
        this.background = new Background4();
    }

    @Override
    public int numberOfBalls() {
        return 3;
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
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        int blockSize = (int) (780 / 15);
        double height = 200;
        int lonelyBlock = 20;

        List<Block> blockArrayList = new ArrayList<Block>();
        Color[] colorsArray = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN,
                Color.WHITE, Color.PINK, Color.CYAN};

        int blocksPerCol = colorsArray.length;
        int blocksPerRow = 15;

        for (int i = 0; i < blocksPerCol; i++) {
            double nextLev = 10;
            for (int j = 0; j < blocksPerRow; j++) {
                blockArrayList.add(new Block(new Point(nextLev, height), blockSize,
                        lonelyBlock, colorsArray[i]));
                nextLev += blockSize;
            }
            height += lonelyBlock;
        }
        return blockArrayList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    @Override
    public int getLevel() {
        return 4;
    }
}
