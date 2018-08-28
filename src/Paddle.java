import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class Paddle is a rectangle with the ability to move.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private int speed = 10;
    private KeyboardSensor keyboard;
    private java.awt.Color color;
    private static final int FIRSTDEG = 30;
    private static final int SECTDEG = 60;
    private static final int THIRDTDEG = 300;
    private static final int FORTHDEG = 330;
    private static final int LEFTLIM = 30;
    private static final int RIGHTLIM = 770;

    /**
     * constructor.
     *
     * @param rect
     *            rectangle
     */
    public Paddle(Rectangle rect) {
        this.rectangle = rect;
    }

    /**
     * constructor.
     *
     * @param upperLeft
     *            - the upper left point
     * @param width
     *            - of the paddle
     * @param height
     *            - of the paddle
     * @param color
     *            - of the paddle
     * @param keyboard
     *            - sensor
     */
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color, KeyboardSensor keyboard) {
        this.rectangle = new Rectangle(upperLeft, width, height, color);
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * @return color of the paddle
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this sweet little func moves the paddle left.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() > LEFTLIM) {
            this.rectangle = new Rectangle(
                    new Point(this.rectangle.getUpperLeft().getX() - speed, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight(), color);
        }
    }

    /**
     * move paddle right.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() < RIGHTLIM) {
            this.rectangle = new Rectangle(
                    new Point(this.rectangle.getUpperLeft().getX() + speed, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight(), color);
        }
    }

    /**
     * Sprite implement - draw the paddle where it's moved to.
     *
     * @param draw
     *            where we draw
     */
    public void drawOn(DrawSurface draw) {
        draw.setColor(this.getColor());
        draw.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Sprite implement - call time passed related to the key that was pressed.
     */
    public void timePassed() {
        if (!(keyboard.isPressed(KeyboardSensor.LEFT_KEY)) && (keyboard.isPressed(KeyboardSensor.RIGHT_KEY))) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && !(keyboard.isPressed(KeyboardSensor.RIGHT_KEY))) {
            moveLeft();
        }
    }

    /**
     * get collidable.
     *
     * @return paddle collidable
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This method will calculate and return a new velocity of the ball
     * regarding were the hit took place.
     *
     * @param collisionPoint
     *            where collision occurred
     * @param currentVelocity
     *            of the ball
     * @param hitter the hitting ball.
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double slice = (this.rectangle.getWidth() / 5);
        double x = this.rectangle.getUpperLeft().getX();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double mySpeed = Math.sqrt(dx * dx + dy * dy);
        double colPX = collisionPoint.getX();
        double colPXRounded = Math.round(colPX);
        if (colPXRounded <= (x + slice) && (colPXRounded >= x)) {
            currentVelocity = Velocity.fromAngleAndSpeed(THIRDTDEG, mySpeed);
        } else if ((colPXRounded <= (x + (2 * slice))) && (colPXRounded > (x + slice))) {
            currentVelocity = Velocity.fromAngleAndSpeed(FORTHDEG, mySpeed);
        } else if ((colPXRounded <= (x + (3 * slice))) && (colPXRounded > (x + (2 * slice)))) {
            currentVelocity = new Velocity(Math.round(dx), Math.round(currentVelocity.getDy()) * -1);
        } else if ((colPXRounded <= (x + (4 * slice))) && (colPXRounded > (x + (3 * slice)))) {
            currentVelocity = Velocity.fromAngleAndSpeed(FIRSTDEG, mySpeed);
        } else if ((colPXRounded <= (x + (5 * slice))) && (colPXRounded > (x + (4 * slice)))) {
            currentVelocity = Velocity.fromAngleAndSpeed(SECTDEG, mySpeed);
        }
        return currentVelocity;
    }

    /**
     * this func will add the paddle to the game.
     *
     * @param game
     *            to add the paddle to
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    /**
     * this func will remove the paddle to the game.
     *
     * @param game
     *            to remove the paddle from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * @param v to set this speed
     */
    public void setSpeed(int v) {
        this.speed = v;
    }
}