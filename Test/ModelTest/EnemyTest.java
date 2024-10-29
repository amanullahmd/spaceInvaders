package ModelTest;

import spaceInvaders.Model.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test class for the Enemy class in the Space Invaders game.
 *
 * This test class contains various test methods to validate the behavior of the Enemy class.
 * @version 1.0
 * @author MD Amanullah
 */
public class EnemyTest {

    /**
     * Private field to hold an Enemy object for testing.
     */
    private Enemy enemy;

    /**
     * Default constructor for EnemyTest.
     * This constructor initializes the EnemyTest class.
     */
    public EnemyTest() {
        // Default constructor
    }

    /**
     * Sets up the test by initializing an Enemy object with starting coordinates.
     */
    @BeforeEach
    public void setUp() {
        enemy = new Enemy(100, 200); // Initialize enemy with starting coordinates
    }

    /**
     * Tests the constructor of the Enemy class.
     *
     * This method validates that the Enemy's initial X and Y positions, lives, and score match the expected values.
     */
    @Test
    public void testConstructor() {
        assertEquals(100, enemy.getX());
        assertEquals(200, enemy.getY());
        assertEquals(10, enemy.getLives());
        assertEquals(0, enemy.getScore());
    }

    /**
     * Tests the move method of the Enemy class.
     *
     * This method checks whether the Enemy's position changes correctly when moving in different directions.
     */
    @Test
    public void testMove() {
        enemy.move(1, 0); // Move right
        assertEquals(110, enemy.getX()); // x should be initialX (100) + 1 * speed (10)
        enemy.move(0, 1); // Move down
        assertEquals(210, enemy.getY()); // y should be initialY (200) + 1 * speed (10)
    }


    /**
     * Tests boundary conditions for the move method of the Enemy class.
     *
     * This method checks whether the Enemy's position is constrained within the screen boundaries when moving.
     */
    @Test
    public void testBoundaryConditions() {
        enemy.move(200, 0); // Move right beyond screen width
        assertEquals(1000 - Enemy.WIDTH, enemy.getX());
        enemy.move(0, 100); // Move down beyond screen height
        assertEquals(600 - Enemy.HEIGHT, enemy.getY());
    }

    /**
     * Tests the increaseLives method of the Enemy class.
     *
     * This method validates that the Enemy's lives increase by the specified amount.
     */
    @Test
    public void testIncreaseLives() {
        enemy.increaseLives(1);
        assertEquals(11, enemy.getLives());
    }

    /**
     * Tests the decreaseLives method of the Enemy class.
     *
     * This method validates that the Enemy's lives decrease by the specified amount.
     */
    @Test
    public void testDecreaseLives() {
        enemy.decreaseLives(1);
        assertEquals(9, enemy.getLives());
    }

    /**
     * Tests the increaseScore method of the Enemy class.
     *
     * This method validates that the Enemy's score increases by the specified amount.
     */
    @Test
    public void testIncreaseScore() {
        enemy.increaseScore(100);
        assertEquals(100, enemy.getScore());
    }

    /**
     * Tests the getX method of the Enemy class.
     *
     * This method validates that the Enemy's getX method returns the expected X position.
     */
    @Test
    public void testGetX() {
        assertEquals(100, enemy.getX());
    }

    /**
     * Tests the getY method of the Enemy class.
     *
     * This method validates that the Enemy's getY method returns the expected Y position.
     */
    @Test
    public void testGetY() {
        assertEquals(200, enemy.getY());
    }

    /**
     * Tests the getLives method of the Enemy class.
     *
     * This method validates that the Enemy's getLives method returns the expected number of lives.
     */
    @Test
    public void testGetLives() {
        assertEquals(10, enemy.getLives());
    }

    /**
     * Tests the getScore method of the Enemy class.
     *
     * This method validates that the Enemy's getScore method returns the expected score.
     */
    @Test
    public void testGetScore() {
        assertEquals(0, enemy.getScore());
    }
}
