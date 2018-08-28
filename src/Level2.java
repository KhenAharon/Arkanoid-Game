import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * Level2 - second level.
 */
public class Level2 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800, BLOCKWIDTH = 50, LOCATIONX = 725, LOCATIONY = 245;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private String levelName;
    private Block background;
    /**
     * Ctor.
     */
    public Level2() {
        background = new Block(new Point(0, 0), WIDTH, LENGTH, java.awt.Color.CYAN, -1);
        levelName = "Wide Easy";
        numberOfBalls = 10;
        paddleSpeed = 6;
        paddleWidth = 600;
        numberOfBlocksToRemove = 15;
    }
    /**
     * @return the number of balls at this level
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * @return list with the velocity balls.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        List<Velocity> velocityBalls = new LinkedList<Velocity>();
        int angel = 72;
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityBalls.add(Velocity.fromAngleAndSpeed(angel, 5));
            angel -= 16;
        }
        return velocityBalls;
    }
    /**
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return paddle width.
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
     * @param d surface to draw on.
     */
    public void drawBackground(DrawSurface d) {
        java.awt.Color color = new Color(190, 252, 252);
        background.drawOn(d);
        int j = 0;
        color = new Color(255, 255, 180);
        d.setColor(color);
        for (int i = 25; i < 100; i++) {
            d.drawLine(100, 120, j, 245);
            j += 12;
        }
        d.fillCircle(100, 120, 55);
        color = new Color(255, 255, 120);
        d.setColor(color);
        d.fillCircle(100, 120, 45);
        color = new Color(255, 255, 60);
        d.setColor(color);
        d.fillCircle(100, 120, 40);
    }
    /**
     * @return a list of the Blocks that make up this level
     */
    public java.util.List<Block> blocks() {
        int hit = 1;
        double axisX = LOCATIONX;
        double axisY = LOCATIONY;
        List<Block> blocksList = new LinkedList<Block>();
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.PINK, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.PINK, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.BLUE, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.BLUE, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.GREEN, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.GREEN, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.GREEN, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.YELLOW, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.YELLOW, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.ORANGE, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.ORANGE, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.RED, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.RED, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.CYAN, hit));
            axisX -= BLOCKWIDTH;
            blocksList.add(new Block(new Point(axisX , axisY),
                    BLOCKWIDTH, 20, java.awt.Color.CYAN, hit));
            axisX -= BLOCKWIDTH;
return blocksList;
    }
    /**
     * @return number of blocks that should be removed.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
