package levels;

import animation.Velocity;
import backgrounds.Background3;
import collidables.Block;
import sprites.Sprite;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//ID: 318720067
/**
 * Level3 class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 21.6.2021
 */
public class Level3 implements LevelInformation {
    private Sprite background;

    /**
     * constructor.
     */
    public Level3() {
        this.background = new Background3();
    }

    @Override
    public int numberOfBalls() {
        return 2;
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
        return 80;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {

        List<Block> blockListArr = new ArrayList<Block>();
        int startPointY = 200;
        int startPointX;
        int x = 740;
        int blockWidth = 50;
        int blockHeight = 20;
        int longestRow = 10;
        int colsNum = 5;

        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        for (int i = 0; i < colsNum; i++) {
            startPointX = x;
                for (int j = i; j < longestRow; j++) {
                    blockListArr.add(new Block(new Point(startPointX, startPointY),
                            blockWidth, blockHeight, colors[i]));
                    startPointX -= blockWidth;
                }
            startPointY += blockHeight;
        }
        return blockListArr;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
