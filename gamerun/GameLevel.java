package gamerun;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import animation.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidables.Block;
import collidables.Collidable;
import collidables.Paddle;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import listeners.HitListener;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.List;
//318720067
/**
 * The "Game" class.
 * holds the sprites and the collidables,
 * and in charge of the animation.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 28.4.2021
 */
public class GameLevel implements Animation {
    /**
     * Game class fields.
     */
    private SpriteCollection sprites;
    private GameEnvironment environment;
    //800*600 canvas
    private GUI gui;
    private Counter blocksCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter score = new Counter();
    private AnimationRunner runner;
    //private ScoreTrackingListener scoreCounter;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * constructor.
     *
     * @param levelInfo = info
     * @param ar        = animation runner
     * @param c         = counter
     * @param gui       = the gui surface
     * @param key1      = the key
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, Counter c, GUI gui, KeyboardSensor key1) {
        this.levelInformation = levelInfo;
        this.environment = new GameEnvironment(new Point(800, 0), new Point(0, 600));
        this.blocksCounter = new Counter();
        this.blocksCounter.increase(this.levelInformation.numberOfBlocksToRemove());
        this.ballCounter = new Counter();
        this.ballCounter.increase(this.levelInformation.numberOfBalls());
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.runner = ar;
        this.score = c;
        this.sprites = new SpriteCollection();
    }

    /**
     * adds the dsired collidable object to the game.
     *
     * @param c = the collidable that i want to add to the game
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adds the dsired sprite object to the game.
     *
     * @param s = the sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * that method initializes a new game: creates the Blocks and Ball (and Paddle)
     * and adds them to the game.
     */
    public void initialize2() {
        levelInformation.getBackground().addToGame(this);
        List<Velocity> velocityList = this.levelInformation.initialBallVelocities();
        int paddleSpeed = this.levelInformation.paddleSpeed();
        int paddleWidth = this.levelInformation.paddleWidth();
        KeyboardSensor k = this.keyboard;

        Point paddlePoint = new Point(400 - (levelInformation.paddleWidth() / 2), 580);

        Paddle paddle = new Paddle(new Rectangle(paddlePoint, paddleWidth, 10),
                Color.YELLOW, paddleSpeed, k, 10, 790);
        paddle.addToGame(this);

        HitListener scoreTrack = new ScoreTrackingListener(this.score);
        BlockRemover hittenBlock = new BlockRemover(this, this.blocksCounter);

        List<Block> blockList = this.levelInformation.blocks();
        int blockAmount = this.levelInformation.numberOfBlocksToRemove();
        for (int i = 0; i < blockAmount; i++) {
            Block block = blockList.get(i);
            block.addToGame(this);
            block.addHitListener(hittenBlock);
            block.addHitListener(scoreTrack);
        }
        addCorners();
        addLevelName();

    }

    /**
     * the frame.
     */
    public void addCorners() {
        Block scoreIndicator = new Block(new Point(0, 0), 800, 50, Color.white);
        Block upperBound = new Block(new Point(0, 50), 800, 10, Color.gray);

        Block leftBound = new Block(new Point(0, 50), 10, 600, Color.gray);
        Block rightBound = new Block(new Point(790, 50), 10, 600, Color.gray);
        Block deathRegion = new Block(new Point(0, 590), 800, 10, Color.gray);

        ScoreIndicator score1 = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        score1.addToGame(this);

        BallRemover ballRemove = new BallRemover(this, this.ballCounter);
        deathRegion.addHitListener(ballRemove);

        upperBound.addToGame(this);
        leftBound.addToGame(this);
        rightBound.addToGame(this);
        deathRegion.addToGame(this);
    }

    /**
     * add the level name.
     */
    public void addLevelName() {
        LevelIndicator levelIndicator = new LevelIndicator(levelInformation);
        levelIndicator.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.running = true;
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
    }

    /**
     * creating the moving balls.
     */
    public void createBallsOnTopOfPaddle() {
        int paddleCorner = (int) (400 - this.levelInformation.paddleWidth() / 2);
        int ballAmount = this.levelInformation.numberOfBalls();
        int ballLocationsSpace = this.levelInformation.paddleWidth() / ballAmount;
        int space = (int) (ballLocationsSpace / 2);

        for (int i = 0; i < ballAmount; i++) {
            Ball ball = new Ball(new Point(paddleCorner + space, 550), 5, Color.pink);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            space = space + ballLocationsSpace;

        }
    }

    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     *
     * @param d = the surface.
     */
    public void doOneFrame(DrawSurface d) {
        Block r = new Block(new Point(0, 0), 800, 600,
                new Color(255 / (float) 255, 255 / (float) 255, 230 / (float) 255));
        r.drawOn(d);

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation keyPressStoppableAnimation =
                    new KeyPressStoppableAnimation(new PauseScreen(this.keyboard),
                            KeyboardSensor.SPACE_KEY, this.keyboard);
            this.runner.run(keyPressStoppableAnimation);

            this.runner.run(new PauseScreen(this.keyboard));
        }

        if (this.ballCounter.getValue() == 0) {
            this.running = false;
            System.out.println("No more balls.");
            return;
        }

        if (this.blocksCounter.getValue() == 0) {
            System.out.println("No more blocks.");
        }
        this.running = false;

    }

    /**
     * Should Stop method.
     *
     * @return boolean - true if the animation should stop,
     * false otherwise.
     */
    public boolean shouldStop() {

        if (this.blocksCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Remover of a sprite.
     *
     * @param s = the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Removing of the collidable from the game.
     *
     * @param c = collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * getter.
     *
     * @return the num.
     */
    public int getBallsNumber() {
        return this.ballCounter.getValue();
    }

    /**
     * getter.
     *
     * @return the num.
     */
    public int getBlocksNumber() {
        return this.blocksCounter.getValue();
    }
}
