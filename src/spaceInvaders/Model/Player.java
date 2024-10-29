package spaceInvaders.Model;

/**
 * Represents the player in the Space Invaders game.
 * This class manages the properties and behaviors of the player, including position,
 * movement, lives, and score.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class Player {
    /**
     * The X coordinate of the player.
     * The Y coordinate of the player.
     */
    private int x, y;
    /**
     * The speed of the player.
     */
    private final int speed = 10;

    /**
     * The width of the player.
     */
    public static final int WIDTH = 120;

    /**
     * The height of the player.
     */
    public static final int HEIGHT = 100;

    /**
     * The number of lives the player has.
     */
    private int lives;

    /**
     * The score of the player.
     */
    private int score;

    /**
     * Constructs a new Player with specified starting coordinates.
     *
     * @param startX The initial X coordinate of the player.
     * @param startY The initial Y coordinate of the player.
     */
    public Player(int startX, int startY) { if (startX < 0 || startY < 0) { throw new IllegalArgumentException("Starting coordinates must be positive"); }
        this.x = startX;
        this.y = startY;
        this.lives = 10; // Initial lives for the player
        this.score = 0; // Initial score for the player
    }

    /**
     * Moves the player based on specified deltas.
     *
     * @param dx The change in the x-direction.
     * @param dy The change in the y-direction.
     */
    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;

        // Ensure the player stays within bounds
        x = Math.max(0, Math.min(x, 1000 - WIDTH));
        y = Math.max(0, Math.min(y, 600 - HEIGHT));
    }

    /**
     * Decreases the number of lives of the player.
     *
     * @param amount The amount to decrease the lives by.
     */
    public void decreaseLives(int amount)  { if (amount < 0) { throw new IllegalArgumentException("Amount must be positive"); }
        this.lives -= amount;
    }

    /**
     * Increases the number of lives of the player.
     *
     * @param amount The amount to increase the lives by.
     */
    public void increaseLives(int amount)  {  if (amount < 0) { throw new IllegalArgumentException("Amount must be positive"); }
         this.lives += amount;
    }

    /**
     * Increases the score of the player.
     *
     * @param amount The amount to increase the score by.
     */
    public void increaseScore(int amount)  { if (amount < 0) { throw new IllegalArgumentException("Amount must be positive"); }
         this.score += amount;
    }

    /**
     * Gets the X coordinate of the player.
     *
     * @return The X coordinate of the player.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate of the player.
     *
     * @return The Y coordinate of the player.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the number of lives of the player.
     *
     * @return The number of lives of the player.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Gets the score of the player.
     *
     * @return The score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns a string representation of the Player object.
     * This representation includes details about the player's position (x and y coordinates),
     * speed, dimensions (width and height), the number of lives, and the current score.
     * This method is useful for debugging purposes to quickly get an overview of the player's current state.
     *
     * @return A string that represents the current state of the player, including its position, speed, dimensions, lives, and score.
     */
    @Override
    public String toString() { return "Player {" + "x=" + x + ", y=" + y + ", speed=" + speed + ", width=" + WIDTH + ", height=" + HEIGHT + ", lives=" + lives + ", score=" + score + '}';}

}