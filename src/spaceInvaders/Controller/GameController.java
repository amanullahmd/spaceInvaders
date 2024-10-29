package spaceInvaders.Controller;

import spaceInvaders.Model.IGameModel;
import spaceInvaders.View.IGameView;

/**
 * The GameController class is part of the spaceInvaders.Controller package.
 * It acts as a mediator between the game model and view, handling game logic
 * and user input. This class is responsible for updating the game state,
 * processing player and enemy actions, and ensuring that the game view is
 * consistently updated to reflect the current state of the game.
 * Example usage:
 * {@code
 *     IGameModel model = new GameModel();
 *     IGameView view = new GameView();
 *     IGameController controller = new GameController(model, view);
 *     controller.startGame();
 * }
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class GameController implements IGameController {
    /**
     * The game model representing the state and logic of the game.
     */
    private IGameModel model;

    /**
     * The game view responsible for rendering the game's visuals.
     */
    private IGameView view;

    /**
     * The current state of the game (e.g., START_SCREEN, RUNNING).
     */
    private GameState gameState;

    /**
     * Keycode for the left arrow key.
     */
    private static final int KEY_LEFT = 37;

    // Similar comments for KEY_UP, KEY_RIGHT, KEY_DOWN

    /**
     * Keycode for the up arrow key.
     */
    private static final int KEY_UP = 38;
    /**
     * Keycode for the right arrow key.
     */
    private static final int KEY_RIGHT = 39;
    /**
     * Keycode for the down arrow key.
     */
    private static final int KEY_DOWN = 40;

    /**
     * Constructor to initialize the GameController with a specific game model and view.
     * Sets the initial game state to START_SCREEN.
     *
     * @param model The game model to be used for game logic.
     * @param view  The game view to be used for rendering.
     */
    public GameController(IGameModel model, IGameView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        gameState = GameState.START_SCREEN;
    }

    /**
     * Validates the movement inputs for the player and enemy. Ensures that the movement
     * deltas are within the allowed range.
     *
     * @param dx The delta in the x-direction, should be -1, 0, or 1.
     * @param dy The delta in the y-direction, should be -1, 0, or 1.
     * @throws IllegalArgumentException If either dx or dy is outside the range of -1 to 1.
     */
    private void validateMoveInputs(int dx, int dy) throws IllegalArgumentException {
        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            throw new IllegalArgumentException("Movement deltas should be -1, 0, or 1.");
        }
    }
    /**
     * Moves the player in the game based on the input directions. This method is responsible
     * for updating the player's position in response to user input, within the constraints
     * of the game's rules (e.g., the game must be running and not over).
     *
     * @param dx The change in the x-direction (-1 for left, 1 for right, 0 for no horizontal movement).
     * @param dy The change in the y-direction (-1 for up, 1 for down, 0 for no vertical movement).
     * @throws IllegalArgumentException If the provided movement deltas are invalid (outside the range of -1 to 1).
     */
    @Override
    public void movePlayer(int dx, int dy) {
        try {
            validateMoveInputs(dx, dy);
            if (gameState == GameState.RUNNING && !model.isGameOver()) {
                model.movePlayer(dx, dy);
                view.updateView();
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid move input: " + e.getMessage());
        }
    }

    /**
     * Moves the enemy based on specified directions. This method is similar to movePlayer
     * but controls the movement of the enemy character. The method checks if the game
     * is running and not over before allowing the enemy to move.
     *
     * @param dx The change in the x-direction (-1, 0, or 1).
     * @param dy The change in the y-direction (-1, 0, or 1).
     * @throws IllegalArgumentException If the provided movement deltas are invalid.
     */
    @Override
    public void moveEnemy(int dx, int dy) {
        try {
            validateMoveInputs(dx, dy);
            if (gameState == GameState.RUNNING && !model.isGameOver()) {
                model.moveEnemy(dx, dy);
                view.updateView();
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid move input: " + e.getMessage());
        }
    }

    /**
     * Triggers the player to shoot a bullet. This method is responsible for handling
     * the player's shooting action within the game. The player can shoot only if the
     * game is currently running and not over.
     */
    @Override
    public void shootBullet() {
        if (gameState == GameState.RUNNING && !model.isGameOver()) {
            model.shootBullet();
            view.updateView();
        }
    }
    /**
     * Triggers the enemy to shoot a bullet. This action is only allowed if the game is running and not over.
     */
    @Override
    public void shootEnemyBullet() {
        if (gameState == GameState.RUNNING && !model.isGameOver()) {
            model.shootEnemyBullet();
            view.updateView();
        }
    }


    /**
     * Sets the game state to the specified value and updates the view to reflect this change.
     *
     * @param state The new state to set the game to.
     */
    @Override
    public void setGameState(GameState state) {
        this.gameState = state;
        view.updateView();
    }

    /**
     * Retrieves the x-coordinate of the player.
     *
     * @return The x-coordinate of the player.
     */
    @Override
    public int getPlayerX() {
        return model.getPlayer().getX();
    }

    /**
     * Retrieves the y-coordinate of the player.
     *
     * @return The y-coordinate of the player.
     */
    @Override
    public int getPlayerY() {
        return model.getPlayer().getY();
    }

    /**
     * Gets the total count of bullets currently on the screen.
     *
     * @return The number of bullets.
     */
    @Override
    public int getBulletCount() {
        return model.getBullets().size();
    }

    /**
     * Retrieves the x-coordinate of the enemy.
     *
     * @return The x-coordinate of the enemy.
     */
    @Override
    public int getEnemyX() {
        return model.getEnemy().getX();
    }

    /**
     * Retrieves the y-coordinate of the enemy.
     *
     * @return The y-coordinate of the enemy.
     */
    @Override
    public int getEnemyY() {
        return model.getEnemy().getY();
    }

    /**
     * Gets the total count of enemy bullets currently on the screen.
     *
     * @return The number of enemy bullets.
     */
    @Override
    public int getEnemyBulletCount() {
        return model.getEnemyBullets().size();
    }
    /**
     * Retrieves the position of a specific bullet based on its index.
     *
     * @param index The index of the bullet in the bullet list.
     * @return An array containing the x and y coordinates of the bullet, or null if index is invalid.
     */
    @Override
    public int[] getBulletPosition(int index) {
        try {
            if (index >= 0 && index < model.getBullets().size()) {
                var bullet = model.getBullets().get(index);
                return new int[]{bullet.getX(), bullet.getY()};
            }
        } catch (Exception e) {
            System.err.println("Error getting bullet position: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves the position of a specific enemy bullet based on its index.
     *
     * @param index The index of the enemy bullet in the enemy bullet list.
     * @return An array containing the x and y coordinates of the enemy bullet, or null if index is invalid.
     */
    @Override
    public int[] getEnemyBulletPosition(int index) {
        try {
            if (index >= 0 && index < model.getEnemyBullets().size()) {
                var bullet = model.getEnemyBullets().get(index);
                return new int[]{bullet.getX(), bullet.getY()};
            }
        } catch (Exception e) {
            System.err.println("Error getting enemy bullet position: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates the game state and view. This method is called periodically to ensure the game state is current.
     */
    @Override
    public void updateGame() {
        try {
            model.updateGame();
            view.updateView();

            if (model.isGameOver()) {
                setGameState(GameState.GAME_OVER);
            }
        } catch (Exception e) {
            System.err.println("Error updating game: " + e.getMessage());
            setGameState(GameState.GAME_OVER);
        }
    }

    /**
     * Resets the game to its initial state.
     */
    @Override
    public void resetGame() {
        model.resetGame();
        // Uncomment to set the game state to START_SCREEN upon reset.
        // setGameState(GameState.START_SCREEN);
    }

    /**
     * Checks if a star exists in the game. Stars are special objects that may have unique effects.
     *
     * @return true if a star exists, false otherwise.
     */
    @Override
    public boolean isStarExists() {
        return model.isStarExists();
    }

    /**
     * Retrieves the position of the star if it exists.
     *
     * @return An array with the x and y coordinates of the star, or null if the star does not exist.
     */
    @Override
    public int[] getStarPosition() {
        if (model.isStarExists()) {
            var star = model.getStar();
            return new int[]{star.getX(), star.getY()};
        }
        return null;
    }

    /**
     * Retrieves the current score of the enemy.
     *
     * @return The score of the enemy.
     */
    @Override
    public int getEnemyScore() {
        return model.getEnemyScore();
    }

    /**
     * Retrieves the number of lives remaining for the enemy.
     *
     * @return The number of lives of the enemy.
     */
    @Override
    public int getEnemyLives() {
        return model.getEnemyLives();
    }

    /**
     * Retrieves the number of lives remaining for the player.
     *
     * @return The number of lives of the player.
     */
    @Override
    public int getPlayerLives() {
        return model.getPlayer().getLives();
    }

    /**
     * Retrieves the current score of the player.
     *
     * @return The score of the player.
     */
    @Override
    public int getScore() {
        return model.getScore();
    }

    /**
     * Retrieves the count of remaining lives for the player.
     *
     * @return The life count of the player.
     */
    @Override
    public int getLifeCount() {
        return model.getLifeCount();
    }


    /**
     * Checks if the game is over, which can occur when the player loses all lives or certain conditions are met.
     *
     * @return true if the game is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        return model.isGameOver();
    }

// Key handling methods

    /**
     * Handles key press events to control game functionality.
     * This includes starting the game, pausing/resuming, and controlling player and enemy actions.
     *
     * @param key The character of the key pressed.
     * @param keyCode The keycode of the key pressed.
     */
    @Override
    public void handleKeyPress(char key, int keyCode) {
        if (gameState == GameState.START_SCREEN && key == ' ') {
            setGameState(GameState.RUNNING); // Start the game
        } else if ((gameState == GameState.RUNNING || gameState == GameState.PAUSED) && key != ' ') {
            handleGameplayKeys(key, keyCode); // Handle gameplay keys
        } else if (gameState == GameState.GAME_OVER && key == ' ') {
            resetGame(); // Reset the game
            setGameState(GameState.RUNNING);
        } else if (gameState == GameState.RUNNING) {
            handleMovementAndShooting(key, keyCode); // Handle movement and shooting
        }
    }

    /**
     * Handles gameplay keys such as pausing and in-game actions.
     *
     * @param key The character of the key pressed.
     * @param keyCode The keycode of the key pressed.
     */
    private void handleGameplayKeys(char key, int keyCode) {
        if (key == 'P' || key == 'p') {
            togglePause(); // Toggle pause state
        } else if (gameState == GameState.RUNNING) {
            handleMovementAndShooting(key, keyCode); // Handle movement and shooting
        }
    }

    /**
     * Toggles the game state between paused and running. If the game is running, it will be paused; if paused, it will resume.
     */
    private void togglePause() {
        if (gameState == GameState.RUNNING) {
            setGameState(GameState.PAUSED); // Pause the game
        } else if (gameState == GameState.PAUSED) {
            setGameState(GameState.RUNNING); // Resume the game
        }
    }

    /**
     * Handles player movement and shooting based on key presses.
     * It interprets the key codes for movement (up, down, left, right) and shooting (spacebar).
     *
     * @param key The character of the key pressed.
     * @param keyCode The keycode of the key pressed.
     */
    private void handleMovementAndShooting(char key, int keyCode) {
        // Player movement handling
        switch (keyCode) {
            case KEY_LEFT: movePlayer(-1, 0); break;
            case KEY_RIGHT: movePlayer(1, 0); break;
            case KEY_UP: movePlayer(0, -1); break;
            case KEY_DOWN: movePlayer(0, 1); break;
        }

        // Player shooting - Triggered when the spacebar is pressed
        if (key == ' ') shootBullet();

        // Enemy movement and shooting
        if (key == 'w') moveEnemy(0, -1);
        else if (key == 's') moveEnemy(0, 1);
        else if (key == 'a') moveEnemy(-1, 0);
        else if (key == 'd') moveEnemy(1, 0);
        else if (key == 'f') shootEnemyBullet();
    }
    /**
     * Checks if the game is currently running. This is used to determine the active state of the game.
     *
     * @return true if the game is running, false otherwise.
     */
    @Override
    public boolean isGameRunning() {
        return gameState == GameState.RUNNING;
    }

    /**
     * Checks if the game is currently paused. Useful for determining if game actions should be temporarily halted.
     *
     * @return true if the game is paused, false otherwise.
     */
    @Override
    public boolean isGamePaused() {
        return gameState == GameState.PAUSED;
    }

    /**
     * Checks if the start screen of the game is currently being displayed.
     * This can be used to trigger game start actions.
     *
     * @return true if the start screen is displayed, false otherwise.
     */
    @Override
    public boolean isStartScreen() {
        return gameState == GameState.START_SCREEN;
    }

    /**
     * Converts the state of the GameController to a string representation.
     * This method is particularly useful for debugging, providing a quick snapshot of the controller's current state.
     *
     * @return A string representation of the GameController state, including model, view, and current game state.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GameController {\n");
        sb.append("  Model: ").append(model).append("\n");
        sb.append("  View: ").append(view).append("\n");
        sb.append("  GameState: ").append(gameState).append("\n");
        sb.append("}\n");

        return sb.toString();
    }

}