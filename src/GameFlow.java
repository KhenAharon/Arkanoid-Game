
import java.util.List;
import biuoop.KeyboardSensor;

/**
 * GameFlow class -is in charge of creating the differnet levels, and moving
 * from one level to the next.
 */
public class GameFlow {
    private static final int MAX_LIVES = 7;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private boolean didWin = true;
    private Counter countBloocks = new Counter(0);
    private Counter score = new Counter(0);
    private Counter livesnum = new Counter(MAX_LIVES);

    /**
     * @param anim is AnimationRunner of all the game.
     * @param key is our KeyboardSensor of the game.
     */
    public GameFlow(AnimationRunner anim, KeyboardSensor key) {
        this.animationRunner = anim;
        this.keyboardSensor = key;
    }
    /**
     * Creates the wanted level by given levelinfo array, run while number
     * of lives does not equal to 0 and Run game over when winning or loosing the game.
     * @param levels List with level to play in order
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel game = new GameLevel(levelInfo, this.animationRunner,
                    this.keyboardSensor, this.livesnum, this.countBloocks, this.score);
            game.initialize();
            game.run();
            if (game.getNumberOfLives() == 0) {
                this.didWin = false;
                break;
            }
        }
        this.animationRunner.run(new EndScreen(this.animationRunner.getGui().getKeyboardSensor(), this.score,
                                 this.didWin));
    }
}