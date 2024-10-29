package ModelTest;

import spaceInvaders.Model.Star;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test class for the Star class in the Space Invaders game.
 *
 * This test class contains various test methods to validate the behavior of the Star class.
 *
 *  @version 1.0
 * @author MD Amanullah
 */
public class StarTest {

    /**
     * Private field to hold a Star object for testing.
     */
    private Star star;

    /**
     * Default constructor for StarTest.
     * This constructor initializes the StarTest class.
     */
    public StarTest() {
        // Default constructor
    }

    /**
     * Sets up the test by initializing a Star object at specific coordinates.
     */
    @BeforeEach
    public void setUp() {
        star = new Star(100, 100); // Initialize star at specific coordinates
    }

    /**
     * Tests the constructor of the Star class.
     *
     * This method validates that the Star's initial X and Y positions match the expected values.
     */
    @Test
    public void testConstructor() {
        assertEquals(100, star.getX());
        assertEquals(100, star.getY());
    }

    /**
     * Tests the isHit method of the Star class.
     *
     * This method checks whether the isHit method correctly determines if a bullet overlaps with the star.
     */
    @Test
    public void testIsHit() {
        Star star = new Star(100, 100); // Star at (100, 100)

        // Bullet overlaps with the star
        assertTrue(star.isHit(95, 95, 10, 20), "Should return true for overlapping bullet.");

        // Bullet does not overlap with the star
        assertFalse(star.isHit(150, 150, 10, 20), "Should return false for non-overlapping bullet.");
    }


    /**
     * Tests the getX and getY methods of the Star class.
     *
     * This method validates that the Star's getX and getY methods return the expected positions.
     */
    @Test
    public void testGetXGetY() {
        assertEquals(100, star.getX());
        assertEquals(100, star.getY());
    }
}
