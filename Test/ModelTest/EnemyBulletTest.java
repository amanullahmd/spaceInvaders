package ModelTest;

import spaceInvaders.Model.EnemyBullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test class for the EnemyBullet class in the Space Invaders game.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class EnemyBulletTest {

    /**
     * Private field to hold an EnemyBullet object for testing.
     */
    private EnemyBullet enemyBullet;

    /**
     * Default constructor for EnemyBulletTest.
     * This constructor initializes the EnemyBulletTest class.
     */
    public EnemyBulletTest() {
        //Default constructor
    }

    /**
     * Sets up the test by initializing an EnemyBullet object with starting coordinates.
     */
    @BeforeEach
    public void setUp() {
        enemyBullet = new EnemyBullet(100, 100); // Initialize enemy bullet with starting coordinates
    }

    /**
     * Tests the constructor of the EnemyBullet class.
     */
    @Test
    public void testConstructor() {
        assertEquals(100, enemyBullet.getX(), "X position should match the initialized value.");
        assertEquals(100, enemyBullet.getY(), "Y position should match the initialized value.");
    }

    /**
     * Tests the update method of the EnemyBullet class.
     * It checks whether the bullet moves downwards by the expected amount.
     */
    @Test
    public void testUpdate() {
        int initialY = enemyBullet.getY();
        enemyBullet.update();
        assertEquals(initialY + 10, enemyBullet.getY(), "Bullet should move downwards by speed units.");
    }

    /**
     * Tests the isOffScreen method of the EnemyBullet class.
     * It verifies that the bullet is considered off-screen after an update.
     */
    @Test
    public void testIsOffScreen() {
        enemyBullet = new EnemyBullet(100, 591); // Position the bullet near the bottom of the screen
        enemyBullet.update(); // Update bullet position
        assertTrue(enemyBullet.isOffScreen(), "Bullet should be off-screen after update.");
    }

    /**
     * Tests the getX method of the EnemyBullet class.
     * It checks whether the X position matches the initialized value.
     */
    @Test
    public void testGetX() {
        assertEquals(100, enemyBullet.getX(), "X position should match the initialized value.");
    }

    /**
     * Tests the getY method of the EnemyBullet class.
     * It verifies that the Y position matches the initialized value.
     */
    @Test
    public void testGetY() {
        assertEquals(100, enemyBullet.getY(), "Y position should match the initialized value.");
    }
}
