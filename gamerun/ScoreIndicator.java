package gamerun;
//ID: 318720067

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * The class ScoreIndicator supports the indicator of the score.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 1.6.2021
 */
public class ScoreIndicator implements Sprite {
    private Counter counterOfHits;

    @Override
    public void drawOn(DrawSurface d) {
        int counter = this.counterOfHits.getValue();
        int textSize = 20;
        d.setColor(Color.black);
        String hits = "Score: " + Integer.toString(counter);
        d.drawText(340, 33, hits, textSize);
    }

    /**
     * The constructor.
     *
     * @param scoreCounter = the counter for the score
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.counterOfHits = scoreCounter;
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
