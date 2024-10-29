package ModelTest;

import org.junit.jupiter.api.AfterEach;
import spaceInvaders.Model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the GameModel class in the Space Invaders game.
 *
 * This test class contains various test methods to validate the behavior of the GameModel class.
 *
 * Note: The use of reflection to access private methods is for testing purposes and may not be considered a best practice in production code.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class GameModelTest {

    /**
     * Default constructor for the GameModelTest class.
     */
    public GameModelTest() {
        // No specific initialization logic for the default constructor
    }

    /**
     * Private field to hold a GameModel object for testing.
     */
    private GameModel gameModel;


    /**
     * Sets up the test environment by initializing the GameModel.
     */
    @BeforeEach
    void setUp() {
        // Initialize the GameModel
        gameModel = new GameModel();
    }

    /**
     * Cleans up resources after each test.
     */
    @AfterEach
    void tearDown() {
        // Clean up resources if needed
        gameModel = null; // Set gameModel to null to release resources
    }

    /**
     * Tests the movement of the player character.
     *
     * This method verifies the movement of the player character within the game environment. It checks if the player can move
     * to the right and down, and whether the player's coordinates match the expected values after each movement.
     *
     * The player's initial position is determined by the setup of the test. It also ensures that the player does not move
     * near the bottom boundary of the game screen.
     */
    @Test
    void movePlayer() {
        // Ensure the player is not near the bottom boundary
        // For example, if the screen height is 600 and the player height is 100,
        // ensure the player's y coordinate is less than 500
        if (gameModel.getPlayer().getY() >= 500) {
            gameModel.movePlayer(0, -2); // Move the player up sufficiently
        }

        int initialX = gameModel.getPlayer().getX();
        int initialY = gameModel.getPlayer().getY();
        int moveAmount = 10; // Update to match the actual speed of the player

        // Move the player to the right by one unit
        gameModel.movePlayer(1, 0);
        assertEquals(initialX + moveAmount, gameModel.getPlayer().getX(),
                "Player X coordinate should match expected value after moving right.");

        // Move the player down by one unit
        gameModel.movePlayer(0, 1);
        assertEquals(initialY + moveAmount, gameModel.getPlayer().getY(),
                "Player Y coordinate should match expected value after moving down.");
    }

    /**
     * Tests the movement of the enemy character.
     *
     * This method verifies the movement of the enemy character within the game environment. It checks if the enemy can move
     * to the right and down, and whether the enemy's coordinates match the expected values after each movement.
     */
    @Test
    void moveEnemy() {
        int initialX = gameModel.getEnemy().getX();
        int initialY = gameModel.getEnemy().getY();
        int moveAmount = 10; // Update to match the actual speed of the enemy

        // Move the enemy to the right by one unit
        gameModel.moveEnemy(1, 0);
        assertEquals(initialX + moveAmount, gameModel.getEnemy().getX(),
                "Enemy X coordinate should match expected value after moving right.");

        // Move the enemy down by one unit
        gameModel.moveEnemy(0, 1);
        assertEquals(initialY + moveAmount, gameModel.getEnemy().getY(),
                "Enemy Y coordinate should match expected value after moving down.");
    }


    /**
     * Tests the shooting of bullets by the player.
     *
     * This method validates whether the player can successfully shoot bullets. It checks if the list of bullets in the game
     * model is not empty after the player shoots a bullet.
     */
    @Test
    void shootBullet() {
        gameModel.shootBullet();
        assertFalse(gameModel.getBullets().isEmpty(), "Player should successfully shoot a bullet.");
    }

    /**
     * Tests the shooting of bullets by the enemy character.
     *
     * This method validates whether the enemy character can successfully shoot bullets. It checks if the list of enemy bullets
     * in the game model is not empty after the enemy character shoots a bullet.
     */
    @Test
    void shootEnemyBullet() {
        gameModel.shootEnemyBullet();
        assertFalse(gameModel.getEnemyBullets().isEmpty(), "Enemy should successfully shoot a bullet.");
    }

    /**
     * Tests the game update mechanism.
     *
     * This method verifies the functionality of updating the game state. It checks if the game model's 'isGameOver' method
     * returns a non-null value after updating the game.
     */
    @Test
    void updateGame() {
        gameModel.updateGame();
        assertNotNull(gameModel.isGameOver(), "Game update should not result in a null game over state.");
    }

    /**
     * Tests the game reset mechanism.
     *
     * This method validates the functionality of resetting the game. It checks if the lists of bullets (player's and enemy's)
     * are empty and if the game over state is set to false after resetting the game.
     */
    @Test
    void resetGame() {
        gameModel.resetGame();
        assertTrue(gameModel.getBullets().isEmpty() && gameModel.getEnemyBullets().isEmpty(),
                "After resetting the game, bullet lists should be empty.");
        assertFalse(gameModel.isGameOver(), "After resetting the game, the game over state should be false.");
    }

    /**
     * Tests the game over condition.
     *
     * This method checks if the game over condition is correctly determined. It ensures that the game over state is false
     * when the player's lives are not depleted, and it becomes true when the player's lives reach zero and the game is updated.
     */
    @Test
    void isGameOver() {
        assertFalse(gameModel.isGameOver(), "Game over state should be false initially.");
        gameModel.getPlayer().decreaseLives(10); // Simulate player losing all lives
        gameModel.updateGame(); // Update the game to trigger game over check
        assertTrue(gameModel.isGameOver(), "Game over state should be true when player's lives reach zero.");
    }

    /**
     * Tests the retrieval of score and life count.
     *
     * This method validates the accuracy of obtaining the player's score and life count. It checks if the initial score
     * and life count match the expected values based on the initial state of the player. It also performs actions that might
     * change the score and life count and ensures that they are updated correctly.
     *
     * To fully test changes in score and life count, you may need to set up scenarios where the player hits enemies,
     * loses lives, or gains lives. This can involve more complex test cases.
     */
    @Test
    void getScoreAndLifeCount() {
        // Initial score and life count should be based on the initial state of the player
        int initialScore = gameModel.getScore();
        int initialLifeCount = gameModel.getLifeCount();

        // Perform some actions that might change the score and life count
        // For example, let the player shoot a bullet and update the game model
        gameModel.shootBullet();
        gameModel.updateGame();

        // Assuming shooting a bullet and updating the game could change the score or life count
        // Check if the score and life count have been updated
        assertEquals(initialScore, gameModel.getScore(), "Score should be the same as initially if no enemy is hit.");
        assertEquals(initialLifeCount, gameModel.getLifeCount(),
                "Life count should remain the same if no life is lost or gained.");

    }

    /**
     * Tests the retrieval of the enemy's initial score.
     *
     * This method verifies that the initial enemy score is correctly initialized to 0.
     */
    @Test
    public void testGetEnemyScore() {
        assertEquals(0, gameModel.getEnemyScore(), "Initial enemy score should be 0.");
    }

    /**
     * Tests the retrieval of the enemy's initial lives.
     *
     * This method checks that the enemy's initial lives are correctly initialized to 10.
     */
    @Test
    public void testGetEnemyLives() {
        assertEquals(10, gameModel.getEnemyLives(), "Initial enemy lives should be 10.");
    }

    /**
     * Tests shooting enemy bullets.
     *
     * This method validates that shooting an enemy bullet results in the creation of enemy bullets in the game model.
     */
    @Test
    public void testShootEnemyBullet() {
        gameModel.shootEnemyBullet();
        assertFalse(gameModel.getEnemyBullets().isEmpty(), "Shooting enemy bullet should create enemy bullets.");
    }

    /**
     * Tests the update of enemy bullets.
     *
     * This method verifies that enemy bullets are correctly updated, ensuring they are not marked as off-screen.
     */
    @Test
    public void testUpdateEnemyBullets() {
        gameModel.shootEnemyBullet();
        gameModel.updateGame();
        assertTrue(gameModel.getEnemyBullets().stream().noneMatch(EnemyBullet::isOffScreen),
                "Enemy bullets should not be marked as off-screen after an update.");
    }

    /**
     * Tests enemy lives after taking damage.
     *
     * This method simulates the enemy taking damage and checks if its lives have decreased by 1 as expected.
     */
    @Test
    public void testEnemyLivesAfterDamage() {
        Enemy enemy = gameModel.getEnemy();
        int initialLives = enemy.getLives(); // Assuming getLives is a public method

        // Simulate the enemy taking damage
        enemy.decreaseLives(1);

        // Check that the enemy's lives have decreased by 1
        assertEquals(initialLives - 1, enemy.getLives(), "Enemy lives should decrease after taking damage.");
    }

    /**
     * Tests the retrieval of enemy bullets.
     *
     * This method ensures that the game model returns a non-null list of enemy bullets when queried.
     */
    @Test
    public void testGetEnemyBullets() {
        assertNotNull(gameModel.getEnemyBullets(), "Should return a non-null list of enemy bullets.");
    }
    /**
     * Tests the retrieval of the player object.
     *
     * This method ensures that the game model returns a non-null player object.
     */
    @Test
    void getPlayer() {
        assertNotNull(gameModel.getPlayer(), "The game model should return a non-null player object.");
    }

    /**
     * Tests the retrieval of the enemy object.
     *
     * This method ensures that the game model returns a non-null enemy object.
     */
    @Test
    void getEnemy() {
        assertNotNull(gameModel.getEnemy(), "The game model should return a non-null enemy object.");
    }


    /**
     * Tests the retrieval of bullets.
     *
     * This method verifies that the game model returns a non-null list of bullets.
     */
    @Test
    void getBullets() {
        assertNotNull(gameModel.getBullets(), "The game model should return a non-null list of bullets.");
    }


    /**
     * Tests the retrieval of enemy bullets.
     *
     * This method ensures that the game model returns a non-null list of enemy bullets.
     */
    @Test
    void getEnemyBullets() {
        assertNotNull(gameModel.getEnemyBullets(), "The game model should return a non-null list of enemy bullets.");
    }


    /**
     * Tests the handling of bullet-enemy collision using reflection.
     *
     * This method sets up the scenario of a bullet colliding with an enemy, invokes the private method
     * "handleBulletEnemyCollision" using reflection, and asserts that the enemy's lives decrease by 1
     * and the player's score increases by 50 as expected.
     *
     * @throws Exception if there's an issue with reflection or method invocation.
     */
    @Test
    void testHandleBulletEnemyCollision() throws Exception {
        // Setup
        Bullet bullet = new Bullet(100, 100); // Adjust coordinates accordingly
        int initialEnemyLives = gameModel.getEnemy().getLives();
        int initialPlayerScore = gameModel.getPlayer().getScore();

        // Using reflection to access the private method
        Method method = GameModel.class.getDeclaredMethod("handleBulletEnemyCollision", Bullet.class);
        method.setAccessible(true);

        // Invoke the method
        method.invoke(gameModel, bullet);

        // Assert
        assertEquals(initialEnemyLives - 1, gameModel.getEnemy().getLives(), "Enemy lives should decrease by 1.");
        assertEquals(initialPlayerScore + 50, gameModel.getPlayer().getScore(), "Player score should increase by 50.");
    }

    /**
     * Tests the handling of bullet-star collision using reflection.
     *
     * This method sets up the scenario of a bullet colliding with a star, invokes the private method
     * "handleBulletStarCollision" using reflection, and asserts that the player's lives increase by 1
     * and the star no longer exists as expected.
     *
     * @throws Exception if there's an issue with reflection or method invocation.
     */
    @Test
    void testHandleBulletStarCollision() throws Exception {
        Bullet bullet = new Bullet(100, 100); // Adjust coordinates accordingly
        int initialPlayerLives = gameModel.getPlayer().getLives();

        // Access the private method using reflection
        Method method = GameModel.class.getDeclaredMethod("handleBulletStarCollision", Bullet.class);
        method.setAccessible(true);

        // Invoke the method
        method.invoke(gameModel, bullet);

        // Assertions
        assertEquals(initialPlayerLives + 2, gameModel.getPlayer().getLives(), "Player lives should increase by 2.");
        assertFalse(gameModel.isStarExists(), "Star should no longer exist after being hit.");
    }


    /**
     * Tests the collision check between a bullet and an enemy using reflection.
     *
     * This method sets up the scenario of a bullet colliding with an enemy, invokes the private method
     * "checkCollision" with Bullet and Enemy parameters using reflection, and asserts that the collision
     * check returns true as expected.
     *
     * @throws Exception if there's an issue with reflection or method invocation.
     */
    @Test
    void testCheckCollisionBulletEnemy() throws Exception {
        Bullet bullet = new Bullet(gameModel.getEnemy().getX(), gameModel.getEnemy().getY());

        // Access the private method using reflection
        Method method = GameModel.class.getDeclaredMethod("checkCollision", Bullet.class, Enemy.class);
        method.setAccessible(true);

        // Invoke the method and get the result
        boolean result = (boolean) method.invoke(gameModel, bullet, gameModel.getEnemy());

        // Assertions
        assertTrue(result, "Bullet should collide with Enemy.");
    }

    /**
     * Tests the handling of bullet-player collision using reflection.
     *
     * This method sets up the scenario of an enemy bullet colliding with the player, invokes the private method
     * "handleBulletPlayerCollision" using reflection, and asserts that the player's lives decrease by 1
     * and the enemy's score increases by 50 as expected.
     *
     * @throws Exception if there's an issue with reflection or method invocation.
     */
    @Test
    void testHandleBulletPlayerCollision() throws Exception {
        EnemyBullet bullet = new EnemyBullet(100, 100); // Adjust coordinates accordingly
        int initialPlayerLives = gameModel.getPlayer().getLives();
        int initialEnemyScore = gameModel.getEnemy().getScore();

        // Access the private method using reflection
        Method method = GameModel.class.getDeclaredMethod("handleBulletPlayerCollision", EnemyBullet.class);
        method.setAccessible(true);

        // Invoke the method
        method.invoke(gameModel, bullet);

        // Assertions
        assertEquals(initialPlayerLives - 1, gameModel.getPlayer().getLives(), "Player lives should decrease by 1.");
        assertEquals(initialEnemyScore + 50, gameModel.getEnemy().getScore(), "Enemy score should increase by 50.");
    }

    /**
     * Tests the handling of enemy bullet-star collision using reflection.
     *
     * This method sets up the scenario of an enemy bullet colliding with a star, invokes the private method
     * "handleBulletStarCollision" using reflection, and asserts that the enemy's lives increase by 1
     * and the star no longer exists as expected.
     *
     * @throws Exception if there's an issue with reflection or method invocation.
     */
    @Test
    void testHandleBulletStarCollisionEnemyBullet() throws Exception {
        EnemyBullet bullet = new EnemyBullet(100, 100); // Adjust coordinates accordingly
        int initialEnemyLives = gameModel.getEnemy().getLives();

        // Access the private method using reflection
        Method method = GameModel.class.getDeclaredMethod("handleBulletStarCollision", EnemyBullet.class);
        method.setAccessible(true);

        // Invoke the method
        method.invoke(gameModel, bullet);

        // Assertions
        assertEquals(initialEnemyLives + 2, gameModel.getEnemy().getLives(), "Enemy lives should increase by 2.");
        assertFalse(gameModel.isStarExists(), "Star should no longer exist after being hit by enemy bullet.");
    }


    /**
     * Tests the collision check between an enemy bullet and the player using reflection.
     *
     * This method sets up the scenario of an enemy bullet colliding with the player, invokes the private method
     * "checkCollision" with EnemyBullet and Player parameters using reflection, and asserts that the collision
     * check returns true as expected.
     *
     * @throws Exception if there's an issue with reflection or method invocation.
     */
    @Test
    void testCheckCollisionEnemyBulletPlayer() throws Exception {
        EnemyBullet bullet = new EnemyBullet(gameModel.getPlayer().getX(), gameModel.getPlayer().getY());

        // Access the private method using reflection
        Method method = GameModel.class.getDeclaredMethod("checkCollision", EnemyBullet.class, Player.class);
        method.setAccessible(true);

        // Invoke the method and get the result
        boolean result = (boolean) method.invoke(gameModel, bullet, gameModel.getPlayer());

        // Assertions
        assertTrue(result, "Enemy Bullet should collide with Player.");
    }

    /**
     * Tests the collision check between a bullet and a star using reflection.
     *
     * This method sets up the scenario of a bullet colliding with a star, invokes the private method
     * "checkCollision" with Bullet and Star parameters using reflection, and asserts that the collision
     * check returns true as expected.
     *
     * @throws Exception if there's an issue with reflection or method invocation.
     */
    @Test
    void testCheckCollisionBulletStar() throws Exception {
        // Create a star object
        Star star = new Star(100, 100);

        // Create a bullet that collides with the star
        Bullet collidingBullet = new Bullet(star.getX(), star.getY());

        // Access the private method using reflection
        Method checkCollision = GameModel.class.getDeclaredMethod("checkCollision", Bullet.class, Star.class);
        checkCollision.setAccessible(true);

        // Invoke the method and assert true for collision
        boolean result = (boolean) checkCollision.invoke(gameModel, collidingBullet, star);
        assertTrue(result, "Bullet should collide with Star.");

        // Create a bullet that does not collide with the star
        Bullet nonCollidingBullet = new Bullet(star.getX() + Star.SIZE + 10, star.getY() + Star.SIZE + 10);

        // Invoke the method again and assert false for no collision
        result = (boolean) checkCollision.invoke(gameModel, nonCollidingBullet, star);
        assertFalse(result, "Bullet should not collide with Star when they do not overlap.");
    }



    /**
     * Tests the collision check between an enemy bullet and a star using reflection.
     *
     * This method sets up the scenario of an enemy bullet colliding with a star, invokes the private method
     * "checkCollision" with EnemyBullet and Star parameters using reflection, and asserts that the collision
     * check returns true as expected.
     *
     * @throws Exception if there's an issue with reflection or method invocation.
     */
    @Test
    void testCheckCollisionEnemyBulletStar() throws Exception {
        // Create a star object
        Star star = new Star(100, 100);

        // Create an enemy bullet that collides with the star
        EnemyBullet collidingBullet = new EnemyBullet(star.getX(), star.getY());

        // Access the private method using reflection
        Method checkCollision = GameModel.class.getDeclaredMethod("checkCollision", EnemyBullet.class, Star.class);
        checkCollision.setAccessible(true);

        // Invoke the method and assert true for collision
        boolean result = (boolean) checkCollision.invoke(gameModel, collidingBullet, star);
        assertTrue(result, "Enemy bullet should collide with Star.");

        // Create an enemy bullet that does not collide with the star
        EnemyBullet nonCollidingBullet = new EnemyBullet(star.getX() + Star.SIZE + 10, star.getY() + Star.SIZE + 10);

        // Invoke the method again and assert false for no collision
        result = (boolean) checkCollision.invoke(gameModel, nonCollidingBullet, star);
        assertFalse(result, "Enemy bullet should not collide with Star when they do not overlap.");
    }


    /**
     * Tests the interruption behavior of the star update thread in the GameModel.
     *
     * This test method uses reflection to access and invoke the private method 'startStarUpdateThread' of the GameModel class.
     * It then retrieves the 'starUpdateThread' via reflection to test its interrupt behavior.
     *
     * The method first invokes 'startStarUpdateThread' to initiate the star update process.
     * Then it accesses the private 'starUpdateThread' field from the GameModel instance.
     * After obtaining the thread, the method interrupts it and asserts whether the thread's interrupt flag is set.
     * This is crucial to ensure that the thread can handle interruptions properly,
     * which is a significant aspect of responsive and resilient multithreaded applications.
     *
     * @throws Exception if any reflection-based operation fails, such as method invocation or field access.
     */
    @Test
    public void testStarUpdateThreadInterruption() throws Exception {

        // Access the startStarUpdateThread method using reflection
        Method startMethod = GameModel.class.getDeclaredMethod("startStarUpdateThread");
        startMethod.setAccessible(true);
        startMethod.invoke(gameModel);

        // Access the starUpdateThread field using reflection
        Field threadField = GameModel.class.getDeclaredField("starUpdateThread");
        threadField.setAccessible(true);
        Thread starUpdateThread = (Thread) threadField.get(gameModel);

        // Test the interruption
        starUpdateThread.interrupt();
        assertTrue(starUpdateThread.isInterrupted(), "Star update thread should be interrupted.");
    }

    /**
     * Tests the retrieval of the Star object from the GameModel.
     *
     * This test ensures that the 'getStar' method in the GameModel class correctly returns a Star object.
     * The method initially invokes 'updateGame' on the GameModel to ensure the star is updated or created,
     * as the star's existence is dependent on the game's state which is managed within 'updateGame'.
     *
     * After calling 'updateGame', it retrieves the star using the 'getStar' method.
     * The test then asserts the non-nullity of the returned Star object.
     * This assertion checks that the game model maintains and provides access to the current Star object,
     * which is an essential part of the game's functionality.
     *
     * The test assumes that a star is always present or created during the updateGame method call,
     * which should be the typical behavior in the game's lifecycle.
     *
     * @throws AssertionError if the 'getStar' method returns a null object, indicating a failure in the Star management logic.
     */
    @Test
    void testGetStar() {
        // Assuming the star is created during the updateGame method.
        gameModel.updateGame();

        // Now retrieve the star using the getStar method
        Star star = gameModel.getStar();

        // Check that the star is not null (assuming a star should always exist after updateGame is called)
        assertNotNull(star, "getStar should return a non-null Star object.");
    }


}