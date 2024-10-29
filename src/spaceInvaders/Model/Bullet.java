package spaceInvaders.Model;

/**
 * Represents a bullet in the Space Invaders game.
 * This class handles the properties and behavior of a bullet, such as its position,
 * movement, and checking if it has gone off the screen.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class Bullet {
    /**
     * The X coordinate of the bullet.
     * The Y coordinate of the bullet.
     */
    private int x, y; // X and Y coordinates of the bullet
    /**
     * The speed of the bullet, determining how fast it moves.
     */
    private final int speed = 10;

    /**
     * The width of the bullet.
     */
    public static final int WIDTH = 10;

    /**
     * The height of the bullet.
     */
    public static final int HEIGHT = 20;


    /**
     * Constructs a new Bullet object.
     *
     * @param startX The initial x-coordinate of the bullet.
     * @param startY The initial y-coordinate of the bullet.
     */
    public Bullet(int startX, int startY) {
        this.x = startX;
        this.y = startY; }

    /**
     * Updates the position of the bullet, moving it upwards on the screen.
     */
    public void update() {y -= speed;}

    /**
     * Checks if the bullet is off the screen.
     *
     * @return true if the bullet is off the screen, false otherwise.
     */
    public boolean isOffScreen() { return y < -HEIGHT; } // Bullet is off-screen if its top has moved past the top of the screen


    /**
     * Gets the x-coordinate of the bullet.
     *
     * @return The x-coordinate of the bullet.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the bullet.
     *
     * @return The y-coordinate of the bullet.
     */
    public int getY() {
        return y;
    }


 /*  @Override
    public String toString() {
        return String.format("Bullet [x=%d, y=%d, speed=%d, WIDTH=%d, HEIGHT=%d]", x, y, speed, WIDTH, HEIGHT);
    } */

}
