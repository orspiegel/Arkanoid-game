package gamerun;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.LoseScreen;
import animation.WinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import java.util.List;
//ID: 318720067


/**
 * Game flow class.
 */
public class GameFlow {
    private GameEnvironment environment;
    private GUI gui;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private Counter gameScore;

    /**
     * Constructor.
     *
     * @param environment = the game env.
     * @param gui         = gui
     * @param runner      = the runner
     * @param keyboard    = the key
     * @param gameScore   = the score
     */
    public GameFlow(GameEnvironment environment, GUI gui, AnimationRunner runner,
                    KeyboardSensor keyboard, Counter gameScore) {
        this.environment = environment;
        this.gui = gui;
        this.runner = runner;
        this.keyboard = keyboard;
        this.gameScore = gameScore;
    }

    /**
     * That method follow all the levels.
     *
     * @param levels = the given levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.runner, this.gameScore,
                    this.gui, this.keyboard);
            level.initialize2();

            do {
                level.run();
            }

            while (!level.shouldStop());

            if (level.getBallsNumber() == 0) {
                LoseScreen win = new LoseScreen(this.keyboard, this.gameScore.getValue());
                win.setScore(gameScore.getValue());
                KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(win,
                        KeyboardSensor.SPACE_KEY, gui.getKeyboardSensor());
                this.runner.run(keyPressStoppableAnimation);
                this.gui.close();
                return;
            }
            this.gameScore.increase(100);
        }

        WinScreen win = new WinScreen(this.keyboard, this.gameScore.getValue());
        win.setScore(this.gameScore.getValue());
        KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(win,
                KeyboardSensor.SPACE_KEY, gui.getKeyboardSensor());

        this.runner.run(keyPressStoppableAnimation);
        this.gui.close();

    }
}
