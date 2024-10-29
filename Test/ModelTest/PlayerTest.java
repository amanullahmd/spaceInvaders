package ModelTest;

import spaceInvaders.Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test class for the Player class in the Space Invaders game.
 *
 * This test class contains various test methods to validate the behavior of the Player class.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class PlayerTest {

    /**
     * Private field to hold a Player object for testing.
     */
    private Player player;

    /**
     * Default constructor for PlayerTest.
     * This constructor initializes the PlayerTest class.
     */
    public PlayerTest() {
        // Add any necessary initialization code here
    }

    /**
     * Sets up the test by initializing a Player object with starting coordinates.
     */
    @BeforeEach
    public void setUp() {
        player = new Player(500, 300); // Initialize player with starting coordinates
    }

    /**
     * Tests the constructor of the Player class.
     *
     * This method validates that the Player's initial X and Y positions, lives, and score match the expected values.
     */
    @Test
    public void testConstructor() {
        assertEquals(500, player.getX());
        assertEquals(300, player.getY());
        assertEquals(10, player.getLives());
        assertEquals(0, player.getScore());
    }

    /**
     * Tests the move method of the Player class.
     *
     * This method checks whether the Player's position changes correctly when moving in different directions.
     */
    @Test
    public void testMove() {
        player.move(1, 0); // Move player right
        assertEquals(510, player.getX(), "Player X coordinate should be 510 after moving right.");
        player.move(0, 1); // Move player down
        assertEquals(310, player.getY(), "Player Y coordinate should be 310 after moving down.");
    }

    /**
     * Tests boundary conditions for the move method of the Player class.
     *
     * This method checks whether the Player's position is constrained within the screen boundaries when moving.
     */
    @Test
    public void testBoundaryConditions() {
        player.move(100, 100); // Attempt to move player beyond the boundary
        assertTrue(player.getX() <= 1000 - Player.WIDTH);
        assertTrue(player.getY() <= 600 - Player.HEIGHT);
    }

    /**
     * Tests the decreaseLives and increaseLives methods of the Player class.
     *
     * This method validates that the Player's lives decrease and increase by the specified amount.
     */
    @Test
    public void testDecreaseIncreaseLives() {
        player.decreaseLives(1);
        assertEquals(9, player.getLives());
        player.increaseLives(2);
        assertEquals(11, player.getLives());
    }

    /**
     * Tests the increaseScore method of the Player class.
     *
     * This method validates that the Player's score increases by the specified amount.
     */
    @Test
    public void testIncreaseScore() {
        player.increaseScore(100);
        assertEquals(100, player.getScore());
    }

    /**
     * Tests the getX and getY methods of the Player class.
     *
     * This method validates that the Player's getX and getY methods return the expected positions.
     */
    @Test
    public void testGetXGetY() {
        assertEquals(500, player.getX());
        assertEquals(300, player.getY());
    }

    /**
     * Tests the getLives and getScore methods of the Player class.
     *
     * This method validates that the Player's getLives and getScore methods return the expected values.
     */
    @Test
    public void testGetLivesGetScore() {
        assertEquals(10, player.getLives());
        assertEquals(0, player.getScore());
    }

    /**
     * Tests the toString method of the Player class.
     *
     * This method validates that the Player's toString method returns a string representation that accurately reflects the player's current state.
     */
    @Test
    public void testToString() {
        String expected = "Player {x=500, y=300, speed=10, width=120, height=100, lives=10, score=0}";
        assertEquals(expected, player.toString(), "toString should return the correct representation of the player.");
    }
}
