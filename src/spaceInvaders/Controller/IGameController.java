package spaceInvaders.Controller;

/**
 * Interface defining the contract for the GameController in the Space Invaders game.
 * It includes methods for handling player and enemy movements, shooting actions, game state management,
 * and other game-related functionalities.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public interface IGameController {

    /**
     * Moves the player in the specified direction.
     *
     * @param dx Change in x-axis (-1 left, 1 right, 0 stationary).
     * @param dy Change in y-axis (-1 up, 1 down, 0 stationary).
     */
    void movePlayer(int dx, int dy);

    /**
     * Triggers the player to shoot a bullet.
     */
    void shootBullet();

    /**
     * Moves the enemy in the specified direction.
     *
     * @param dx Change in x-axis (-1 left, 1 right, 0 stationary).
     * @param dy Change in y-axis (-1 up, 1 down, 0 stationary).
     */
    void moveEnemy(int dx, int dy);
    /**
     * Triggers the enemy to shoot a bullet.
     */
    void shootEnemyBullet();


    /**
     * Sets the game state to a specified value.
     *
     * @param state New state of the game.
     */
    void setGameState(GameState state);

    /**
     * Retrieves the x-coordinate of the player.
     *
     * @return The x-coordinate of the player.
     */
    int getPlayerX();

    /**
     * Retrieves the y-coordinate of the player.
     *
     * @return The y-coordinate of the player.
     */
    int getPlayerY();

    /**
     * Gets the count of bullets currently in play.
     *
     * @return Number of bullets.
     */
    int getBulletCount();

    /**
     * Retrieves the position of a specific bullet based on its index.
     *
     * @param index Index of the bullet in the collection.
     * @return The x and y coordinates of the bullet, or null if index is invalid.
     */
    int[] getBulletPosition(int index);

    /**
     * Retrieves the count of remaining lives for the player.
     *
     * @return Number of remaining lives.
     */
    int getLifeCount();

    /**
     * Updates the game state and view.
     */
    void updateGame();

    /**
     * Checks if a star exists in the game.
     *
     * @return True if a star exists, false otherwise.
     */
    boolean isStarExists();

    /**
     * Retrieves the position of the star if it exists.
     *
     * @return The x and y coordinates of the star, or null if no star exists.
     */
    int[] getStarPosition();

    /**
     * Retrieves the current score of the player.
     *
     * @return Player's score.
     */
    int getScore();

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * Retrieves the x-coordinate of the enemy.
     *
     * @return The x-coordinate of the enemy.
     */
    int getEnemyX();

    /**
     * Retrieves the y-coordinate of the enemy.
     *
     * @return The y-coordinate of the enemy.
     */
    int getEnemyY();

    /**
     * Gets the count of enemy bullets currently in play.
     *
     * @return Number of enemy bullets.
     */
    int getEnemyBulletCount();

    /**
     * Retrieves the position of a specific enemy bullet based on its index.
     *
     * @param index Index of the enemy bullet in the collection.
     * @return The x and y coordinates of the enemy bullet, or null if index is invalid.
     */
    int[] getEnemyBulletPosition(int index);

    /**
     * Retrieves the current score of the enemy.
     *
     * @return Enemy's score.
     */
    int getEnemyScore();

    /**
     * Retrieves the number of lives remaining for the enemy.
     *
     * @return Number of enemy lives.
     */
    int getEnemyLives();

    /**
     * Retrieves the number of lives remaining for the player.
     *
     * @return Number of player lives.
     */
    int getPlayerLives();

    /**
     * Resets the game to its initial state.
     */
    void resetGame();

    /**
     * Handles key press events to control game functionality.
     *
     * @param key The character of the key pressed.
     * @param keyCode The keycode of the key pressed.
     */
    void handleKeyPress(char key, int keyCode);

    /**
     * Checks if the game is currently running.
     *
     * @return True if the game is running, false otherwise.
     */
    boolean isGameRunning();

    /**
     * Checks if the game is currently paused.
     *
     * @return True if the game is paused, false otherwise.
     */
    boolean isGamePaused();

    /**
     * Checks if the start screen is currently being displayed.
     *
     * @return True if the start screen is displayed, false otherwise.
     */
    boolean isStartScreen();
}