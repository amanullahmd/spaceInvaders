package ModelTest;

import spaceInvaders.Model.Bullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the Bullet class in the Space Invaders game.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class BulletTest {

    /**
     * Private field to hold a Bullet object for testing.
     */
    private Bullet bullet;


    /**
     * Default constructor for BulletTest.
     * This constructor initializes the BulletTest class.
     */
    public BulletTest() {

    }

    /**
     * Sets up the test by initializing a Bullet object with starting coordinates.
     */
    @BeforeEach
    public void setUp() {
        bullet = new Bullet(100, 200); // Initialize bullet with starting coordinates
    }

    /**
     * Tests the constructor of the Bullet class.
     */
    @Test
    public void testConstructor() {
        assertEquals(100, bullet.getX());
        assertEquals(200, bullet.getY());
    }

    /**
     * Tests the update method of the Bullet class.
     */
    @Test
    public void testUpdate() {
        int initialY = bullet.getY();
        bullet.update();
        assertEquals(initialY - 10, bullet.getY()); // y should decrease by speed (10) after update
    }

    /**
     * Tests the isOffScreen method of the Bullet class when the bullet is off the screen.
     */
    @Test
    public void testIsOffScreenTrue() {
        // Position the bullet such that it is off the screen
        bullet = new Bullet(100, -21);
        assertTrue(bullet.isOffScreen());
    }


    /**
     * Tests the isOffScreen method of the Bullet class when the bullet is on the screen.
     */
    @Test
    public void testIsOffScreenFalse() {
        // Position the bullet such that it is on the screen
        assertFalse(bullet.isOffScreen());
    }

    /**
     * Tests the getX method of the Bullet class.
     */
    @Test
    public void testGetX() {
        assertEquals(100, bullet.getX());
    }

    /**
     * Tests the getY method of the Bullet class.
     */
    @Test
    public void testGetY() {
        assertEquals(200, bullet.getY());
    }
}
