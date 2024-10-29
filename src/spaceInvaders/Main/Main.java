package spaceInvaders.Main;

import processing.core.PApplet;
import spaceInvaders.Controller.GameController;
import spaceInvaders.Model.GameModel;
import spaceInvaders.View.GameView;

/**
 * Main class for the Space Invaders game. This class sets up the game's model, view, and controller,
 * and starts the Processing sketch.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class Main {

    /**
     * Default constructor for the Main class.
     */
    public Main() {
        // Default constructor
    }

    /**
     * The main method is the entry point of the application. It initializes the game's
     * model, view, and controller, and starts the Processing sketch to run the game.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize the game model.
        GameModel model = new GameModel();

        // Initialize the game view.
        GameView view = new GameView();

        // Initialize the game controller and link it with the model and view.
        GameController controller = new GameController(model, view);

        // Set the controller in the view.
        view.setController(controller);

        // Start the Processing sketch with the game view.
        PApplet.runSketch(new String[]{"Space Invaders"}, view);
    }
}
