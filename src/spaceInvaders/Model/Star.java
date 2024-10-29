package spaceInvaders.Model;

/**
 * Represents a star in the Space Invaders game.
 * This class handles the properties and collision detection for a star object.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class Star {
    /**
     * The X coordinate of the star.
     * The Y coordinate of the star.
     */
    private int x, y;
    /**
     * The size of the star.
     */
    public static final int SIZE = 50;

    /**
     * Constructs a new Star with specified starting coordinates.
     *
     * @param startX The initial X coordinate of the star.
     * @param startY The initial Y coordinate of the star.
     */
    public Star(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    /**
     * Checks if the star is hit by a bullet.
     * This method is used for collision detection between the star and a bullet.
     *
     * @param bulletX      The X coordinate of the bullet.
     * @param bulletY      The Y coordinate of the bullet.
     * @param bulletWidth  The width of the bullet.
     * @param bulletHeight The height of the bullet.
     * @return true if the bullet intersects with the star, false otherwise.
     */
    public boolean isHit(int bulletX, int bulletY, int bulletWidth, int bulletHeight) {
        return (bulletX < x + SIZE && bulletX + bulletWidth > x && bulletY < y + SIZE && bulletY + bulletHeight > y);
    }

    /**
     * Gets the X coordinate of the star.
     *
     * @return The X coordinate of the star.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate of the star.
     *
     * @return The Y coordinate of the star.
     */
    public int getY() {
        return y;
    }

 /*   @Override
    public String toString() {
        return "Star {" +  "x=" + x + ", y=" + y + ", SIZE=" + SIZE + '}';
    } */

}
