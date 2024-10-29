package spaceInvaders.Controller;

/**
 * Enum representing the various states of the Space Invaders game.
 * Each state corresponds to a different phase or condition of the game.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public enum GameState {
    /**
     * The initial state of the game, typically displaying the start menu.
     */
    START_SCREEN,

    /**
     * Indicates that the game is actively in progress.
     */
    RUNNING,

    /**
     * The game is temporarily halted, usually due to user input.
     */
    PAUSED,

    /**
     * The game has ended, typically after the player has lost all lives or completed all levels.
     */
    GAME_OVER
}
