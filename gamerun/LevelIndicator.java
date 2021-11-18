package gamerun;

import biuoop.DrawSurface;
import levels.LevelInformation;
import sprites.Sprite;

import java.awt.Color;
//318720067
/**
 * indicates in which level the current game.
 */
public class LevelIndicator implements Sprite {
    private LevelInformation levelInformation;

    /**
     * Constructor.
     * @param levelInformation = the data about the level preferences.
     */
    public LevelIndicator(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int textSize = 20;
        d.setColor(Color.black);
        String level = "Level: " + this.levelInformation.levelName();
        d.drawText(600, 33, level, textSize);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}
