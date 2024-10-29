package spaceInvaders.View;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import spaceInvaders.Controller.IGameController;




/**
 * The <code>GameView</code> class is responsible for rendering the game's graphical
 * interface using Processing.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class GameView extends PApplet implements IGameView {
    /**
     * The game controller responsible for managing game logic.
     */
    private IGameController controller;

    /**
     * Images used in the game:
     * - startScreenImage: The image displayed at the start of the game.
     * - playerImage: The image representing the player character.
     * - starImage: The image representing stars in the background.
     * - enemyImage: The image representing the enemy character.
     * - gameOverScreenImage: The image displayed at the end of the game.
     */
    private PImage startScreenImage, playerImage, starImage, enemyImage, gameOverScreenImage;

    /**
     * Font used for displaying text in the game.
     */
    private PFont font;


    /**
     * Arrays to store star positions and speeds:
     * - starX: X-coordinates of stars.
     * - starY: Y-coordinates of stars.
     * - starSpeeds: Speeds of stars.
     */
    private float[] starX, starY, starSpeeds;

    /**
     * Number of stars in the background.
     */
    private int numStars = 100;


    /**
     * Flag to indicate whether the information screen is currently being displayed.
     * It is set to {@code true} when the "Game Info" button is clicked and set to {@code false}
     * either when the "Back" button is clicked or when the spacebar is pressed.
     */
    private boolean showInfoScreen = false;

    /**
     * The X-coordinate for the "Game Info" button's position on the screen. This determines
     * where the button is placed horizontally.
     */
    private final int gameInfoButtonX = 10;

    /**
     * The Y-coordinate for the "Game Info" button's position on the screen. This determines
     * where the button is placed vertically.
     */
    private final int gameInfoButtonY = 10;

    /**
     * The X-coordinate for the "Back" button's position on the information screen. This
     * determines where the button is placed horizontally. Adjust this value if the button
     * appears off-screen due to canvas size.
     */
    private final int backButtonX = width + 740;

    /**
     * The Y-coordinate for the "Back" button's position on the information screen. This
     * determines where the button is placed vertically.
     */
    private final int backButtonY = 10;

    /**
     * The width of the interactive buttons ("Game Info" and "Back") displayed on the screen.
     */
    private final int buttonWidth = 150;

    /**
     * The height of the interactive buttons ("Game Info" and "Back") displayed on the screen.
     */
    private final int buttonHeight = 50;





    /**
     * Default constructor for the GameView class.
     * This constructor initializes the GameView object.
     */
    public GameView() {
        // default constructor.
    }



    /**
     * Configures the size and pixel density of the game window.
     */
    @Override
    public void settings() {
        size(1000, 600);
        pixelDensity(1);
    }

    /**
     * Initializes the game view by loading images, setting the font, and initializing stars.
     */
    @Override
    public void setup() {
        try {
            loadImages(); // Load game images
            font = createFont("Arial", 32); // Create a font for text display
            textFont(font);
            initStars(); // Initialize the starry background
        } catch (Exception e) {
            System.err.println("Error in setup: " + e.getMessage());
        }
    }

    /**
     * Loads game images including start screen, player, enemy, star, and game over screen.
     * Displays an error message if there is an issue loading any image.
     */
    private void loadImages() {
        try {
            startScreenImage = loadImage("startScreen.jpg");
            playerImage = loadImage("player.png");
            enemyImage = loadImage("enemy.png");
            starImage = loadImage("star.png");
            gameOverScreenImage = loadImage("gameOverScreen.jpg");
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    /**
     * Initializes the starry background by creating arrays for star positions and speeds.
     * Randomly generates initial positions and speeds for stars.
     */
    private void initStars() {
        starX = new float[numStars];
        starY = new float[numStars];
        starSpeeds = new float[numStars];
        for (int i = 0; i < numStars; i++) {
            starX[i] = random(width);
            starY[i] = random(height);
            starSpeeds[i] = random(1, 3);
        }
    }

    /**
     * Renders the game's graphical interface based on the current game state. This method is called repeatedly
     * and is responsible for the main game loop rendering.
     *
     * The method performs the following actions:
     * - Updates the game state by calling {@code controller.updateGame()}.
     * - Checks the current game state (info screen, game running, paused, game over, or start screen)
     *   and renders the appropriate screen.
     * - The rendering for each state is handled by separate methods: {@code drawInfoScreen()},
     *   {@code drawGame()}, {@code drawPausedScreen()}, {@code drawGameOverScreen()}, and
     *   {@code drawStartScreen()}.
     * - In the case of the start screen, it additionally renders the "Game Info" button.
     * - Exception handling is implemented to catch and report errors that occur during the rendering process.
     */
    @Override
    public void draw() {
        try {
            controller.updateGame();
            if (showInfoScreen) {
                drawInfoScreen();
            } else if (controller.isGameRunning()) {
                drawGame();
            } else if (controller.isGamePaused()) {
                drawPausedScreen();
            } else if (controller.isGameOver()) {
                drawGameOverScreen();
            } else if (controller.isStartScreen()) {
                drawStartScreen();
                drawGameInfoButton();
            }
        } catch (Exception e) {
            System.err.println("Error in draw: " + e.getMessage());
        }
    }

    /**
     * Draws the "Game Info" button on the screen. This method is responsible for rendering a rectangular button
     * with the text "Game Info". The button is drawn at a predefined position on the screen, with specific width
     * and height, and a set color scheme.
     *
     * The method performs the following actions:
     * - Sets the fill color for the button's background (orange color in this case).
     * - Draws a rectangle at the specified position ({@code gameInfoButtonX}, {@code gameInfoButtonY})
     *   with the specified dimensions ({@code buttonWidth}, {@code buttonHeight}).
     * - Sets the fill color for the text (black in this case).
     * - Adjusts the text size to 16.
     * - Sets the text alignment to center.
     * - Draws the text "Game Info" centered within the button.
     *
     * The position and size of the button are determined by the {@code gameInfoButtonX}, {@code gameInfoButtonY},
     * {@code buttonWidth}, and {@code buttonHeight} variables.
     */

    private void drawGameInfoButton() {
        fill(255, 200, 0); // Button color
        rect(gameInfoButtonX, gameInfoButtonY, buttonWidth, buttonHeight);
        fill(0);
        textSize(16);
        textAlign(CENTER, CENTER);
        text("Game Info", gameInfoButtonX + buttonWidth / 2, gameInfoButtonY + buttonHeight / 2);
    }


    /**
     * Draws the information screen which displays the game instructions and a "Back" button.
     * This screen is shown when the "Game Info" button is pressed and provides the player
     * with details on how to play the game, including controls and gameplay mechanics.
     *
     * The method performs the following actions:
     * - Sets a background color for the information screen.
     * - Defines the text color (white) and size (16) for the main instruction text.
     * - Aligns the text to the top-left corner and displays the gameplay instructions hardcoded within the method.
     * - Changes the text color to green and increases the size to 32 for an additional message,
     *   guiding the player to press SPACE to return to the game start screen.
     * - Draws a "Back" button that allows the player to return to the main game screen.
     *   This button is positioned at the top right of the screen and is styled with a specific color, size, and label.
     *
     * The method ensures the text and the "Back" button are clearly visible and easy to interact with,
     * enhancing the user experience in understanding the game mechanics.
     */
    private void drawInfoScreen() {
        background(100);
        fill(255);
        textSize(16);
        textAlign(LEFT, TOP);
        text("*** Gameplay Instructions:\n\n" +
                        "- Start the Game: Press the spacebar.\n" +
                        "- Player Controls: Navigate with arrow keys, shoot with the spacebar.\n" +
                        "- Enemy Controls: Move using 'WASD', shoot with 'F'.\n" +
                        "- Pause the Game: Press 'P'.\n" +
                        "- Life Increase: Collect stars to gain additional lives and enhance shooting capabilities.\n" +
                        "- Scoring and Victory: Score points by hitting the enemy or player. Game Over when someone runs out of lives.\n" +
                        "- Game Over: Revealing the Winner (Player or Enemy) and Winner final scores.\n" +
                        "- Restart the Game: Press the spacebar.",
                10, 10);

        // Additional message about starting the game
        fill(0, 255, 0); // Green color
        textSize(32); // Larger font size
        text("Alternatively, press SPACE to return to the Game Start Screen.", 10, 250); // Adjust the Y coordinate as needed

        // Reset text size and color for other elements
        fill(255);
        textSize(16);

        // Back button
        fill(200, 100, 100);
        rect(backButtonX, backButtonY, buttonWidth, buttonHeight);
        fill(255);
        textSize(16);
        textAlign(CENTER, CENTER);
        text("Back", backButtonX + buttonWidth / 2, backButtonY + buttonHeight / 2);
    }




    /**
     * Handles mouse press events within the game view.
     *
     * This method overrides the mousePressed method from the PApplet class.
     * It is called whenever the mouse is pressed and is responsible for handling
     * interactions with the "Game Info" and "Back" buttons.
     *
     * When the "Game Info" button is clicked, it toggles the visibility of the information
     * screen, displaying or hiding the game instructions. The method checks if the mouse
     * coordinates are within the bounds of the "Game Info" button to determine if it has been clicked.
     *
     * Similarly, when the "Back" button is clicked on the information screen, it hides the information
     * screen and returns to the main game view. The method checks if the mouse coordinates are within
     * the bounds of the "Back" button to determine if it has been clicked.
     */
    @Override
    public void mousePressed() {
        // Toggle the info screen when Game Info button is clicked
        if (mouseX > gameInfoButtonX && mouseX < gameInfoButtonX + buttonWidth && mouseY > gameInfoButtonY && mouseY < gameInfoButtonY + buttonHeight) {
            showInfoScreen = !showInfoScreen;
        }
        // Toggle back to the main screen when Back button is clicked
        else if (mouseX > backButtonX && mouseX < backButtonX + buttonWidth && mouseY > backButtonY && mouseY < backButtonY + buttonHeight) {
            showInfoScreen = false;
        }
    }



    /**
     * Draws the game elements including the starry background, player, bullets, enemy,
     * enemy bullets, and star (if exists). Also displays the player's score and lives,
     * and the enemy's score and lives.
     */
    private void drawGame() {
        drawStarryBackground();
        drawPlayer();
        drawBullets();
        drawEnemy();
        drawEnemyBullets();
        if (controller.isStarExists()) {
            drawStar();
        }
        displayScoreAndLives();
        displayEnemyScoreAndLives();
    }

    /**
     * Draws the starry background with moving stars.
     * It updates the position of stars and redraws them.
     */
    private void drawStarryBackground() {
        background(0);
        fill(255);
        noStroke();
        for (int i = 0; i < numStars; i++) {
            starX[i] -= starSpeeds[i];
            if (starX[i] < 0) {
                starX[i] = width;
                starY[i] = random(height);
            }
            ellipse(starX[i], starY[i], 2, 2);
        }
    }

    /**
     * Draws the start screen using the provided start screen image.
     */
    private void drawStartScreen() {
        background(startScreenImage);
    }

    /**
     * Draws the player character at its current position.
     */
    private void drawPlayer() {
        image(playerImage, controller.getPlayerX(), controller.getPlayerY(), 120, 100);
    }

    /**
     * Draws the enemy character at its current position.
     */
    private void drawEnemy() {
        int enemyX = controller.getEnemyX();
        int enemyY = controller.getEnemyY();
        image(enemyImage, enemyX, enemyY, 120, 100);
    }

    /**
     * Draws bullets fired by the player. Each bullet is represented as an ellipse.
     */
    private void drawBullets() {
        fill(255, 0, 0);
        for (int i = 0; i < controller.getBulletCount(); i++) {
            int[] bulletPos = controller.getBulletPosition(i);
            if (bulletPos != null) {
                ellipse(bulletPos[0], bulletPos[1], 10, 20);
            }
        }
    }

    /**
     * Draws bullets fired by the enemy. Each bullet is represented as an ellipse.
     */
    private void drawEnemyBullets() {
        fill(0, 0, 255);
        for (int i = 0; i < controller.getEnemyBulletCount(); i++) {
            int[] bulletPos = controller.getEnemyBulletPosition(i);
            if (bulletPos != null) {
                ellipse(bulletPos[0], bulletPos[1], 10, 20);
            }
        }
    }

    /**
     * Draws the star object if it exists in the game.
     */
    private void drawStar() {
        int[] starPosition = controller.getStarPosition();
        if (starPosition != null && starImage != null) {
            image(starImage, starPosition[0], starPosition[1], 50, 50);
        }
    }

    /**
     * Displays the player's current score and remaining lives on the screen.
     */
    private void displayScoreAndLives() {
        fill(255);
        textSize(25);
        textAlign(LEFT, TOP);
        text("Player Score: " + controller.getScore(), 10, 10);
        text("Player Lives: " + controller.getLifeCount(), 10, 50);
    }

    /**
     * Displays the enemy's current score and remaining lives on the screen.
     */
    private void displayEnemyScoreAndLives() {
        fill(0, 255, 0); // Green color
        textSize(25);
        textAlign(RIGHT, TOP);
        text("Enemy Score: " + controller.getEnemyScore(), width - 10, 10);
        text("Enemy Lives: " + controller.getEnemyLives(), width - 20, 40);
    }

    /**
     * Draws the "Game Paused" message at the center of the screen when the game is paused.
     */
    private void drawPausedScreen() {
        fill(255);
        textAlign(CENTER, CENTER);
        textSize(32);
        text("Game Paused", width / 2, height / 2);
    }

    /**
     * Draws the game over screen with the winner's name and final score.
     */
    private void drawGameOverScreen() {
        background(gameOverScreenImage);
        fill(255, 0, 0); //red color
        textSize(50);
        textAlign(CENTER, CENTER);

        int playerScore = controller.getScore();
        int enemyScore = controller.getEnemyScore();

        String winner = (playerScore > enemyScore) ? "Player" : "Enemy";

        text("Game Over - " + winner + " Won!", width / 2, height / 2 - 40);
        text(winner  + " - Final Scores " +  " : " + ((winner.equals("Player")) ? playerScore : enemyScore), width / 2, height / 2);
    }

    /**
     * Called when a key is pressed. Delegates the key event to the game controller.
     */
    @Override
    public void keyPressed() {
        if (key == ' ' && showInfoScreen) {
            showInfoScreen = false;

        } else {
            // Existing key press handling logic
            controller.handleKeyPress(key, keyCode);
        }
    }


    /**
     * Sets the game controller for this view.
     *
     * @param controller The game controller to set.
     */
    @Override
    public void setController(IGameController controller) {
        this.controller = controller;
    }

    /**
     * This method is left empty as it is part of the interface contract. It does not have any specific functionality in this class.
     */
    @Override
    public void updateView() {

    }

    /**
     * Generates a string representation of the GameView object, including information about the controller, player image, star image, enemy image, and font used.
     *
     * @return A string representation of the GameView.
     */
    @Override
    public String toString() {
        return "GameView {" +
                "\n  Controller: " + controller +
                "\n  Player Image: " + playerImage +
                "\n  Star Image: " + starImage +
                "\n  Enemy Image: " + enemyImage +
                "\n  Font: " + font +
                "\n}";
    }

}

