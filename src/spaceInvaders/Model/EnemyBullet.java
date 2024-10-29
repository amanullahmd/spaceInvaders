package spaceInvaders.Model;

/**
 * Represents an enemy bullet in the Space Invaders game.
 * This class manages the properties and behavior of an enemy bullet, including its
 * position, movement, and collision detection.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class EnemyBullet {
    /**
     * The X coordinate of the enemy bullet.
     * The Y coordinate of the enemy bullet.
     */
    private int x, y;
    /**
     * The speed of the enemy bullet, determining how fast it moves.
     */
    private final int speed = 10;

    /**
     * The width of the enemy bullet.
     */
    public static final int WIDTH = 10;

    /**
     * The height of the enemy bullet.
     */
    public static final int HEIGHT = 20;

    /**
     * Constructs a new enemy bullet with specified starting coordinates.
     *
     * @param startX The initial X coordinate of the bullet.
     * @param startY The initial Y coordinate of the bullet.
     */
    public EnemyBullet(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    /**
     * Updates the position of the enemy bullet, moving it downwards.
     */
    public void update() {
        y += speed; // Move the bullet downwards
    }

    /**
     * Checks if the enemy bullet is off the screen.
     *
     * @return true if the bullet is off the screen, false otherwise.
     */
    public boolean isOffScreen() {
        return y + HEIGHT > 600; // Assuming the screen height is 600
    }

    /**
     * Gets the X coordinate of the enemy bullet.
     *
     * @return The X coordinate of the bullet.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate of the enemy bullet.
     *
     * @return The Y coordinate of the bullet.
     */
    public int getY() {
        return y;
    }

   /* @Override
    public String toString() {
        return String.format("EnemyBullet [x=%d, y=%d, speed=%d, WIDTH=%d, HEIGHT=%d]",
                x, y, speed, WIDTH, HEIGHT);
    }*/

}
