package collidables;
// 318720067

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import listeners.HitListener;

/**
 * The class generates a block rows as the given preferences.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public class BlockGenerator {
    private Point upperLeft;
    private Point lowerRight;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param upperLeft  = the upper left coordiante
     * @param lowerRight = the lower right coordintaion
     */
    public BlockGenerator(Point upperLeft, Point lowerRight) {
        /**
         generates  Block list / row by the given preferences
         */
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * generating to block in coordination to the frame edges.
     *
     * @param lim   = the upper left edge of the block
     * @param color = the color of the block
     * @param hittt = the hit.
     * @return the new block
     */
    public Block newBlock(Point lim, Color color, ArrayList<HitListener> hittt) {
        Block newB = new Block(lim, 51, 30, color);
        return newB;
    }

    /**
     * @param start = the point in which i want to start from the blocks raw.
     *              every iteration, ill add the start x value to the
     *              50 x value, to get another 50 width block at the same raw.
     * @return the corner.
     */
    public Point newPointForNewBlock(Point start) {
        Point newCorner;
        newCorner = new Point(50 + start.getX(), 0 + start.getY());
        return newCorner;
    }

    /**
     * that method generates a list of blocks.
     *
     * @param start       = the start point for the new raw of blocks
     * @param numOfBlocks = the desired number of blocks to generate
     *                    on one raw.
     * @param color       = the desired color for each block.
     * @param hitListener = the hit listener.
     * @return = the list of the blocks.
     */
    public ArrayList newBlockList(Point start, int numOfBlocks, Color color, ArrayList<HitListener> hitListener) {
        ArrayList arrayOfBlocks = new ArrayList();
        for (int i = 0; i < numOfBlocks; i++) {
            arrayOfBlocks.add(this.newBlock(start, color, hitListener));
            start = this.newPointForNewBlock(
                    new Point(start.getX(), start.getY()));
        }
        return arrayOfBlocks;
    }

    /**
     * method to start a new array of blocks.
     *
     * @param start = the start point for the array values
     * @param color = the desired color of the blocks raw
     * @return the array of the blocks
     */
    public List newBlocksGenerator(Point start, Color color) {
        int len = (int) (this.upperLeft.getX() - this.lowerRight.getX()) - 30;
        double diff = len  - start.getX();
        int blocks =  (int) (diff / 51);
        List arr;
        arr = new ArrayList(newBlockList(start, blocks, color, (ArrayList<HitListener>) this.hitListeners));
        return arr;
    }

}
