package sprites;
//ID: 318720067
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The class SpriteCollection supports the addition of new sprites.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 26.4.2021
 */
public class SpriteCollection {
    private ArrayList spriteCollection;
    /**
     * constructor that initials a new list of sprites.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList();
    }

    /**
     * constructor that initials a new list of sprites.
     *
     * @param spriteCollection -> output
     */
    public SpriteCollection(List spriteCollection) {
        this.spriteCollection = new ArrayList();
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // for every object at the collection
        // of colloidal objects, ill put the timePassed function
        for (int i = 0; i < this.spriteCollection.size(); i++) {
            Sprite s = (Sprite) this.spriteCollection.get(i);
            s.timePassed();
        }
    }

    /**
     * adding sprite to the sprite list.
     *
     * @param s = the input sprite to add to the collection
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    /**
     * Removing sprite to the sprite list.
     *
     * @param s = the input sprite to remove from the collection
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }

    /**
     * drawing on the screen of the game all the sprites.
     * all the collidable things\objects
     *
     * @param d = the surface <-> game screen
     */
    public void drawAllOn(DrawSurface d) {
        int spritesAmount = this.spriteCollection.size();
        for (Object o : this.spriteCollection) {
            Sprite mySprite = (Sprite) o;
            mySprite.drawOn(d);
        }
    }
}
