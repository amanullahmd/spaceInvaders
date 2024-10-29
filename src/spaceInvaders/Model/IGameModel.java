package spaceInvaders.Model;

import java.util.List;

/**
 * Interface defining the functionalities of the game model for Space Invaders.
 * It includes methods for game actions like moving the player and enemies, shooting,
 * and managing the game state.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public interface IGameModel {
    /**
     * Moves the player in the given direction.
     *
     * @param dx Change in x-direction.
     * @param dy Change in y-direction.
     */
    void movePlayer(int dx, int dy);

    /**
     * Moves the enemy in the given direction.
     *
     * @param dx Change in x-direction.
     * @param dy Change in y-direction.
     */
    void moveEnemy(int dx, int dy);

    /**
     * Triggers the player to shoot a bullet.
     */
    void shootBullet();

    /**
     * Triggers an enemy to shoot a bullet.
     */
    void shootEnemyBullet();

    /**
     * Updates the game state, including the position of all game elements.
     */
    void updateGame();

    /**
     * Resets the game to its initial state.
     */
    void resetGame();

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * Gets the player object.
     *
     * @return The player object.
     */
    Player getPlayer();

    /**
     * Gets the enemy object.
     *
     * @return The enemy object.
     */
    Enemy getEnemy();

    /**
     * Gets the list of bullets shot by the player.
     *
     * @return List of bullets.
     */
    List<Bullet> getBullets();

    /**
     * Gets the list of bullets shot by the enemy.
     *
     * @return List of enemy bullets.
     */
    List<EnemyBullet> getEnemyBullets();

    /**
     * Gets the star object, if it exists in the game.
     *
     * @return The star object.
     */
    Star getStar();

    /**
     * Checks if a star exists in the game.
     *
     * @return true if a star exists, false otherwise.
     */
    boolean isStarExists();

    /**
     * Gets the current score of the player.
     *
     * @return Player's score.
     */
    int getScore();

    /**
     * Gets the current life count of the player.
     *
     * @return Player's life count.
     */
    int getLifeCount();

    /**
     * Gets the current score of the enemy.
     *
     * @return Enemy's score.
     */
    int getEnemyScore();

    /**
     * Gets the current number of lives left for the enemy.
     *
     * @return Enemy's remaining lives.
     */
    int getEnemyLives();
}
