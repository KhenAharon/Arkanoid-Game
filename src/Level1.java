import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * Level1 - first level.
 */
public class Level1 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800, BLOCKWIDTH = 40;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private int numberOfBlocksToRemove;
    private Sprite background;
    /**
     * Ctor.
     */
    public Level1() {
        background = new Block(new Point(0, 0), WIDTH, LENGTH, java.awt.Color.black, -1);
        numberOfBlocksToRemove = 1;
        levelName = "Direct Hit";
        numberOfBalls = 1;
        paddleSpeed = 12;
        paddleWidth = 85;
    }
    /**
     * @return the number of balls left.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * @return velocity list of balls.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        List<Velocity> velocityBalls = new LinkedList<Velocity>();
        velocityBalls.add(Velocity.fromAngleAndSpeed(0, 10));
        return velocityBalls;
    }
    /**
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * @return paddle speed.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * @return level name.
     */
    public String levelName() {
        return this.levelName;
    }
    /**
     * @return background of level.
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * @return list of the Blocks of this level.
     */
    public java.util.List<Block> blocks() {
        List<Block> blocksList = new LinkedList<Block>();
        Block blocki = new Block(new Point(380, 150), BLOCKWIDTH, BLOCKWIDTH, java.awt.Color.RED, 1);
        blocksList.add(blocki);
        return blocksList;
    }
    /**
     * @param d surface to draw on
     */
    public void drawBackground(DrawSurface d) {
        Point sun = new Point(400, 170);
        this.background.drawOn(d);
        int radius = 40;
        d.drawLine((int) sun.getX(), (int) sun.getY() - radius, (int) sun.getX(), (int) sun.getY() + radius);
        d.drawLine((int) sun.getX() - radius, (int) sun.getY(), (int) sun.getX() + radius, (int) sun.getY());
        d.setColor(java.awt.Color.cyan);
        d.drawCircle((int) sun.getX(), (int) sun.getY(), radius);
        d.setColor(java.awt.Color.BLUE);
        d.drawCircle((int) sun.getX(), (int) sun.getY(), radius + 33);
        d.setColor(java.awt.Color.cyan);
        d.drawCircle((int) sun.getX(), (int) sun.getY(), radius + 66);
        d.setColor(Color.pink);
        d.drawLine(295, 170, 400, 170);
        d.drawLine(400, 170, 505, 170);
        d.drawLine(400, 170, 400, 275);
        d.drawLine(400, 170, 400, 65);
    }
    /**
     * @return number of blocks that should be removed.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}