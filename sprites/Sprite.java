package sprites;
//ID: 318720067
import gamerun.GameLevel;
import biuoop.DrawSurface;

/**
 * The SpriteCollection InterfaceSpriteCollection
 * holds a collection of sprites.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public interface Sprite {
    /**
     * drawOn function to the objects (sprites) drawing on the screen.
     *
     * @param d = surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the sprite to the game.
     *
     * @param g = game
     */
    void addToGame(GameLevel g);
}
