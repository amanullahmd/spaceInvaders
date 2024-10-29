package spaceInvaders.Model;

/**
 * Represents an enemy in the Space Invaders game.
 * This class manages the properties and behaviors of an enemy, including its position,
 * movement, lives, and score.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class Enemy {
    /**
     * The X coordinate of the enemy.
     * The Y coordinate of the enemy.
     */
    private int x, y;
    /**
     * The speed of the enemy.
     */
    private final int speed = 10;

    /**
     * The width of the enemy.
     */
    public static final int WIDTH = 120;

    /**
     * The height of the enemy.
     */
    public static final int HEIGHT = 100;

    /**
     * The score associated with the enemy.
     */
    private int score;

    /**
     * The number of lives the enemy has.
     */
    private int lives;

    /**
     * Constructs a new enemy with specified starting coordinates.
     *
     * @param startX The initial X coordinate of the enemy.
     * @param startY The initial Y coordinate of the enemy.
     */
    public Enemy(int startX, int startY) { if (startX < 0 || startY < 0) { throw new IllegalArgumentException("Starting coordinates must be non-negative"); }
        this.x = startX;
        this.y = startY;
        this.lives = 10; // Initial lives for the enemy
        this.score = 0; // Initial score for the enemy
    }

    /**
     * Moves the enemy based on specified deltas.
     *
     * @param dx The change in the x-direction.
     * @param dy The change in the y-direction.
     */
    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
        // Ensure the enemy stays within bounds
        x = Math.max(0, Math.min(x, 1000 - WIDTH));
        y = Math.max(0, Math.min(y, 600 - HEIGHT));
    }

    /**
     * Gets the X coordinate of the enemy.
     *
     * @return The X coordinate of the enemy.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate of the enemy.
     *
     * @return The Y coordinate of the enemy.
     */
    public int getY() {
        return y;
    }

    /**
     * Increases the number of lives of the enemy.
     *
     * @param amount The amount to increase the lives by.
     */
    public void increaseLives(int amount)  { if (amount < 0) { throw new IllegalArgumentException("Amount to increase lives cannot be negative"); }
         this.lives += amount;
    }

    /**
     * Decreases the number of lives of the enemy.
     *
     * @param amount The amount to decrease the lives by.
     */
    public void decreaseLives(int amount) { if (amount < 0) { throw new IllegalArgumentException("Amount to decrease lives cannot be negative");}
        this.lives -= amount;
    }

    /**
     * Increases the score of the enemy.
     *
     * @param amount The amount to increase the score by.
     */
    public void increaseScore(int amount) { if (amount < 0) { throw new IllegalArgumentException("Amount to increase score cannot be negative");}
        this.score += amount;
    }

    /**
     * Gets the score of the enemy.
     *
     * @return The score of the enemy.
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the number of lives of the enemy.
     *
     * @return The number of lives of the enemy.
     */
    public int getLives() {
        return lives;
    }

  /*  @Override
    public String toString() {
        return String.format("Enemy [x=%d, y=%d, speed=%d, WIDTH=%d, HEIGHT=%d, score=%d, lives=%d]", x, y, speed, WIDTH, HEIGHT, score, lives);
    }*/

}