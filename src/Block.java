import java.awt.Color;
import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * @author Khen Aharon
 */
public class Block implements Collidable, HitNotifier, Sprite {
    private Rectangle rect;
    private int hits;
    private ArrayList<HitListener> hitListeners;

    /**
     * @param rect
     *            rectangle
     * @param hits
     *            no times of being hit
     */
    public Block(Rectangle rect, int hits) {
        this.rect = rect;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @param point
     *            location
     * @param width
     *            of block
     * @param height
     *            of block
     * @param color
     *            of block
     * @param hits
     *            how much times remaining
     */
    public Block(Point point, int width, int height, Color color, int hits) {
        rect = new Rectangle(point, width, height, color);
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    @Override
    /**
     * @return this rect
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Tells collidable object whether it's been hit and return new velocity
     * accordingly.
     * @param collisionPoint
     *            collision point where there was a hit of block or paddle or
     *            wall (block too).
     * @param currentVelocity
     *            velocity before the collision.
     * @param hitter the hitting ball.
     * @return new velocity after hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle.LinePosition side = this.rect.getIntersectionLinePoisition(collisionPoint);
        if (side == Rectangle.LinePosition.Down || side == Rectangle.LinePosition.Up) {
            currentVelocity.changeDyDirection();
        }
        if (side == Rectangle.LinePosition.Left || side == Rectangle.LinePosition.Right) {
            currentVelocity.changeDxDirection();
        }
        if (hits > 0) {
            this.hits--;
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * Draws the block on the surface.
     * @param d
     *            surface on which to draw
     */
    public void drawOn(DrawSurface d) {
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) rect.getUpperLeft().getY();
        // width
        int w = (int) rect.getWidth();
        // height
        int h = (int) rect.getHeight();
        d.setColor(java.awt.Color.black);
        d.drawRectangle(x, y, w, h);
        d.setColor(rect.getColor());
        d.fillRectangle(x + 1, y + 1, w - 1, h - 1);
        if (hits > 0) {
            d.setColor(Color.BLACK);
            d.drawText(x + 10, y + 10, Integer.toString(hits), 12);
        } else if (hits == 0) {
            d.setColor(Color.BLACK);
            d.drawText(x + 10, y + 10, "X", 12);
        }
    }

    @Override
    public void timePassed() {
    }

    /**
     * the function add a ball to the game.
     * @param game
     *            is the object that the ball add to it
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * the function will remove a block.
     * @param game to remove from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * @param hitter hitting ball.
     */
    private void notifyHit(Ball hitter) {
              // Make a copy of the hitListeners before iterating over them.
              ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
              // Notify all listeners about a hit event:
              for (HitListener hl : listeners) {
                 hl.hitEvent(this, hitter);
              }
           }
    /**
     * @return number of hits that's taken place
     */
    public int getHitPoint() {
        return this.hits;
    }
    /**
     * the function add hl as a listener to hit events.
     * @param hl is notify listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * the function remove hl from the list of listeners to hit events.
     * @param hl is notify listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
