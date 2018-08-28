/**
 * class BallRemover is in charge of removing balls, and updating
 *  the ball counter.
 */
public class BallRemover implements HitListener {
    private GameLevel g;
    private Counter removedBalls;
    /**
     * @param game current being played.
     * @param removedBalls counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.g = game;
        this.removedBalls = removedBalls;
    }
    /**
     * Remove ball and update counter.
     * @param deathRegion block to determine whether a ball went off the screen.
     * @param hitter hitting ball.
     */
    public void hitEvent(Block deathRegion, Ball hitter) {
        hitter.removeFromGame(this.g);
        this.removedBalls.decrease(1);

    }
}