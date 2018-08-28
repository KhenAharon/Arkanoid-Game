import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * Level4 - Forth level.
 */
public class Level4 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800, BLOCKSATLINE = 12,
                             AXISX = 40, BLOCKWIDTH = 60, NUMBEROFLINES = 7;
    private String levelName;
    private int paddleSpeed;
    private int numberOfBalls;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private Color color;
    private Block background;
    private boolean change;
    /**
     * Ctor.
     */
    public Level4() {
        levelName = "Final Four";
        paddleSpeed = 22;
        numberOfBalls = 3;
        paddleWidth = 82;
        numberOfBlocksToRemove = 84;
        color = new Color(34, 150, 255);
        background = new Block(new Point(0, 0), WIDTH, LENGTH, color, -1);
        change = true;
    }
    /**
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * @return velocity list of each ball.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        List<Velocity> velocityBalls = new LinkedList<Velocity>();
        int angel = 0;
            velocityBalls.add(Velocity.fromAngleAndSpeed(angel + 70, 7));
            velocityBalls.add(Velocity.fromAngleAndSpeed(angel, 7));
            velocityBalls.add(Velocity.fromAngleAndSpeed(angel - 70, 7));
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
     * @return level background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @param d
     *            surface to draw.
     */
    public void drawBackground(DrawSurface d) {
        background.drawOn(d);
        color = new Color(211, 244, 248);
        d.setColor(color);
        d.fillCircle(690, 295, 35);
        color = new Color(183, 236, 242);
        d.setColor(color);
        d.fillCircle(600, 300, 35);
        d.fillCircle(635, 320, 40);
        color = new Color(211, 244, 248);
        d.setColor(color);
        d.fillCircle(650, 260, 35);

        d.fillCircle(450, 360, 35);
        d.fillCircle(490, 395, 35);
        color = new Color(183, 236, 242);
        d.setColor(color);
        d.fillCircle(400, 400, 35);
        d.fillCircle(435, 420, 40);
        color = new Color(211, 244, 248);
        d.setColor(color);
        d.fillCircle(450, 360, 35);
        color = new Color(169, 232, 239);
        d.setColor(color);
        d.fillCircle(660, 195, 35);
        color = new Color(211, 244, 248);
        d.setColor(color);
        d.fillCircle(200, 300, 35);
        color = new Color(169, 232, 239);
        d.setColor(color);
        d.fillCircle(230, 350, 35);
        d.fillCircle(255, 300, 35);
        color = new Color(211, 244, 248);
        d.setColor(color);
        d.fillCircle(230, 260, 35);
    }
    /**
     * @return Blocks list of this level.
     */
    public java.util.List<Block> blocks() {
        List<Block> blocksList = new LinkedList<Block>();
        Block[][] block = new Block[NUMBEROFLINES][];
        if (!change) {
            return blocksList;
        }
        block[0] = createBlock(125, java.awt.Color.GRAY, 2, blocksList);
        block[1] = createBlock(145, java.awt.Color.RED, 1, blocksList);
        block[2] = createBlock(165, java.awt.Color.YELLOW, 1, blocksList);
        block[3] = createBlock(185, java.awt.Color.GREEN, 1, blocksList);
        block[4] = createBlock(205, java.awt.Color.WHITE, 1, blocksList);
        block[5] = createBlock(225, java.awt.Color.PINK, 1, blocksList);
        block[6] = createBlock(245, java.awt.Color.CYAN, 1, blocksList);
        return blocksList;
    }
    /**
     * creates an array of blocks.
     * @param locationY
     *            location Y
     * @param myColor
     *            of the rectangle
     * @param hitPoint
     *            number of hit at the block
     * @param blocks
     *            list of blocks
     * @return block list.
     */
    private Block[] createBlock(int locationY, Color myColor, int hitPoint, java.util.List<Block> blocks) {
        double locationX = AXISX;
        Block[] newBlock = new Block[BLOCKSATLINE];
        for (int i = 0; i < BLOCKSATLINE; i++) {
            if (!change) {
                return newBlock;
            }
            newBlock[i] = new Block(new Point(locationX, locationY), BLOCKWIDTH, 20, myColor, hitPoint);
            locationX += BLOCKWIDTH;
            blocks.add(newBlock[i]);
        }
        return newBlock;
    }
    /**
     * @return blocks number to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}