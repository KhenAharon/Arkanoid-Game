import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * Level3 - third level.
 */
public class Level3 implements LevelInformation {
    private static final int WINDOW_LENGHT = 600, WINDOW_WIDE = 800, BLOCKWIDTH = 50, LINES = 5;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private String levelName;
    private int count;
    private Color color;
    private Block background;
    private boolean change;
    /**
     * Ctor.
     */
    public Level3() {
        numberOfBalls = 2;
        paddleSpeed = 15;
        paddleWidth = 70;
        numberOfBlocksToRemove = 40;
        levelName = "Green 3";
        count = 0;
        color = new Color(133, 250, 130);
        background = new Block(new Point(0, 0), WINDOW_WIDE, WINDOW_LENGHT, color, -1);
        change = true;
    }
    /**
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * @return velocity of each ball.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<Velocity>();
        int initialV = 38;
            velocities.add(Velocity.fromAngleAndSpeed(initialV, 10));
            initialV -= 81;
            velocities.add(Velocity.fromAngleAndSpeed(initialV, 10));
            initialV -= 81;
            velocities.add(Velocity.fromAngleAndSpeed(initialV, 10));
        return velocities;
    }

    /**
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return paddle width
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
        background.drawOn(d);
        // building
        color = new Color(150, 0, 60);
        d.setColor(color);
        d.fillRectangle(70, 480, 90, 120);
        d.setColor(Color.WHITE);
        for (int i = 78; i < 144; i += 16) {
            for (int j = 488; j < 600; j += 21) {
                d.fillRectangle(i, j, 10, 15);
            }
        }
        color = new Color(200, 62, 230);
        d.setColor(color);
        d.fillRectangle(111, 200, 8, 250);
        d.fillRectangle(105, 450, 20, 30);
        d.setColor(Color.yellow);
        d.fillCircle(115, 200, 10);
        d.setColor(Color.BLACK);
        if (!change) {
            count = 7;
        }
        this.count += 6;
        if (this.count == 40) {
            this.count = 0;
        }
    }

    /**
     * @return Blocks of this level.
     */
    public java.util.List<Block> blocks() {
        List<Block> blocksList = new LinkedList<Block>();
        Block[][] block = new Block[LINES][];
        block[0] = createBlock(10, 725, 125, java.awt.Color.GRAY, 2, blocksList);
        block[1] = createBlock(9, 725, 145, java.awt.Color.RED, 1, blocksList);
        block[2] = createBlock(8, 725, 165, java.awt.Color.YELLOW, 1, blocksList);
        block[3] = createBlock(7, 725, 185, java.awt.Color.BLUE, 1, blocksList);
        block[4] = createBlock(6, 725, 205, java.awt.Color.PINK, 1, blocksList);
        return blocksList;
    }

    /**
     * @return number of blocks that should be removed.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
    /**
     * create blocks array.
     * @param size
     *            number of blocks
     * @param axisX
     *            x axis
     * @param axisY
     *            y axis
     * @param mycolor
     *            block color
     * @param hits
     *            number of hits
     * @param blocks
     *            block list
     * @return blocks array created.
     */
    private Block[] createBlock(int size, int axisX, int axisY, Color mycolor, int hits,
            java.util.List<Block> blocks) {
        Block[] someBlock = new Block[size];
        if (!change) {
            return someBlock;
        }
        for (int i = 0; i < size; i++) {
            someBlock[i] = new Block(new Point(axisX, axisY), BLOCKWIDTH, 20, mycolor, hits);
            axisX -= BLOCKWIDTH;
            blocks.add(someBlock[i]);
        }
        return someBlock;
    }
}
