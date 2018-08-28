import java.awt.Color;
import java.util.Iterator;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Khen Aharon
 */
public class GameLevel implements Animation {
    /**
     * class Game will create a new game with balls, blocks and paddle, and
     * activate the whole games's functions.
     */

    private LevelInformation info;
    private Counter score = new Counter(0);
    private Counter blockCount = new Counter(0);
    private Counter ballCount = new Counter(0);
    private Counter numberOfLives = new Counter(0);
    private ScoreTrackingListener tracker;
    private BallRemover ballRem;
    private BlockRemover blockRem;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Ball[] balls;
    private Block[] walls;
    private java.util.List<Block> blocks;
    private java.util.List<Velocity> velocities;
    private Paddle paddle;
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    public static final int BALLS_NO = 2;
    public static final int DEGREES = 360;
    public static final int WALLS_NO = 3;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    /**
     * @param info our level info
     * @param r animation runner to run animation.
     * @param k keyboard sensor.
     * @param lives no of lives.
     * @param blockcount no of blocks.
     * @param scores the score.
     */
    public GameLevel(LevelInformation info, AnimationRunner r, KeyboardSensor k,
                     Counter lives, Counter blockcount, Counter scores) {
        this.score = scores;
        this.blockCount = blockcount;
        this.numberOfLives = lives;
        this.keyboard = k;
        this.runner = r;
        this.info = info;
    }
    /**
     * @param c
     *            add this Collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * @param c
     *            collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * @param s
     *            add this sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * @param s
     *            sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Create ball, blocks, paddle...the whole game environment.
     */
    public void initialize() {
        this.blockCount = new Counter(info.numberOfBlocksToRemove());
        this.ballCount = new Counter(info.numberOfBalls());
        this.ballRem = new BallRemover(this, this.ballCount);
        this.blockRem = new BlockRemover(this, this.blockCount);
        this.tracker = new ScoreTrackingListener(this.score);
        int i;
        sprites = new SpriteCollection();
        environment = Ball.getGameEvironment();
        walls = new Block[3];
        walls[0] = new Block(new Point(0, 25), BOARD_WIDTH, 25, Color.gray, -1);
        walls[1] = new Block(new Point(0, 25), 25, BOARD_HEIGHT, Color.gray, -1);
        walls[2] = new Block(new Point(BOARD_WIDTH - 25, 25), 25, BOARD_HEIGHT, Color.gray, -1);
        Block deathBlock = new Block(new Point(0, BOARD_HEIGHT + 25), BOARD_WIDTH, 25, Color.gray, -1);
        ScoreIndicator scoreIndi = new ScoreIndicator(new Rectangle(new Point(0, 0), BOARD_WIDTH, 25),
                                                      this.score);
        LevelNameIndicator levelName = new LevelNameIndicator(new Rectangle(new Point(0, 0), BOARD_WIDTH, 25),
                this.info.levelName());
        LivesIndicator liveIndi = new LivesIndicator(new Rectangle(new Point(0, 0), BOARD_WIDTH, 25),
                this.numberOfLives);
        deathBlock.addHitListener(ballRem);
        for (i = 0; i < WALLS_NO; i++) {
            walls[i].addToGame(this);
        }
        deathBlock.addToGame(this);
        scoreIndi.addToGame(this);
        liveIndi.addToGame(this);
        levelName.addToGame(this);
        blocks = this.info.blocks();
        Iterator<Block> iter1 = blocks.iterator();
        while (iter1.hasNext()) {
            iter1.next().addToGame(this);
        }
        Iterator<Block> iter2 = blocks.iterator();
        while (iter2.hasNext()) {
            iter2.next().addHitListener(blockRem);
        }
        Iterator<Block> iter3 = blocks.iterator();
        while (iter3.hasNext()) {
            iter3.next().addHitListener(tracker);
        }
    }
    /**
     * remove all elements from level.
     */
    public void removeElements() {
        this.environment.deplete();
        this.sprites.deplete();
    }
    /**
     * create balls on top of the paddle and the paddle.
     */
    private void createBallsOnTopOfPaddle() {
        int i;
        balls = new Ball[info.numberOfBalls()];
        paddle = new Paddle(new Point((BOARD_WIDTH - info.paddleWidth()) / 2,
                            BOARD_HEIGHT - 20 - 25), info.paddleWidth(), 20,
                            Color.ORANGE, this.keyboard);
        paddle.setSpeed(info.paddleSpeed());
        paddle.addToGame(this);
        this.velocities = info.initialBallVelocities();
        Iterator<Velocity> velo = velocities.iterator();
        for (i = 0; i < info.numberOfBalls(); i++) {
            balls[i] = new Ball(BOARD_WIDTH / 2, BOARD_HEIGHT - 65, 5, Color.white);
            balls[i].setVelocity(velo.next());
            balls[i].addToGame(this);
        }
    }
    /**
     * if to stop the animation.
     * @return if the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return !running;
    }
    /**
     * Run the game.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        // Count Down 3 2 1 Go (during 2 seconds).
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, info));
        running = true;
        // the loop that runs the game
        this.runner.run(this);
    }
    /**
     * implement of the animation interface.
     * @param d draw surface to draw.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (blockCount.getValue() == 0 || ballCount.getValue() == 0) {
            running = false;
        }
        info.drawBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }
    /**
     * run the level.
     */
    public void run() {
        while (numberOfLives.getValue() > 0) {
            playOneTurn();
            if (blockCount.getValue() == 0) {
                score.increase(100);
                runner.runOneFrame(this);
                removeElements();
                return;
            }
            this.paddle.removeFromGame(this);
            numberOfLives.decrease(1);
            ballCount.increase(info.numberOfBalls());
        }
        removeElements();
        return;
    }
    /**
     * get lives func.
     * @return number of lives
     */
    public int getNumberOfLives() {
        return numberOfLives.getValue();
    }
    /**
     * get blocks number.
     * @return number of blocks.
     */
    public int getBlockCount() {
        return blockCount.getValue();
    }
}
