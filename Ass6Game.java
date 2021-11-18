import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamerun.Counter;
import gamerun.GameEnvironment;
import gamerun.GameFlow;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;
//ID: 318720067

/**
 * The class is responsible for initiation of the game on the screen.
 *
 * @author Or Spiegel
 * @version 4.0
 * @since 28.4.2021
 * - version - 21.6.2021
 */
public class Ass6Game {
    private GUI gui = new GUI("gui", 600, 800);
    private AnimationRunner animationRunner;
    private KeyboardSensor key;

    /**
     * Constructor of the ass6 game class.
     *
     * @param key = the keyBoard sensor.
     */
    public Ass6Game(KeyboardSensor key) {
        this.animationRunner = new AnimationRunner(this.gui);
        this.key = key;
        this.key = gui.getKeyboardSensor();
    }

    /**
     * the main method - initiates the game in coordination
     * to the given arguments.
     *
     * @param args = the levels
     */
    public static void main(String[] args) {
        GameEnvironment environment = new GameEnvironment();
        GUI gui = new GUI("Game", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Counter gameScore = new Counter();
        gameScore.setValue(0);

        int string;
        List<LevelInformation> levels = new ArrayList<LevelInformation>();

        boolean integerArgs = false;

        for (int i = 0; i < args.length; i++) {
            try {
                string = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                continue;
            }
            if (string == 1) {
                levels.add(new Level1());
                integerArgs = true;
            } else if (string == 2) {
                levels.add(new Level2());
                integerArgs = true;
            } else if (string == 3) {
                levels.add(new Level3());
                integerArgs = true;
            } else if (string == 4) {
                levels.add(new Level4());
                integerArgs = true;
            }

        }
        if (!integerArgs) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }

        GameFlow game = new GameFlow(environment, gui, runner, keyboard, gameScore);
        game.runLevels(levels);
    }
}
