package spaceInvaders.View;

import spaceInvaders.Controller.IGameController;

/**
 * The <code>IGameView</code> interface defines the functionalities of the view component
 * for the Space Invaders game.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public interface IGameView {
    /**
     * Sets the controller for this view.
     *
     * @param controller The game controller to be associated with this view.
     */
    void setController(IGameController controller);

    /**
     * Updates the view to reflect the current state of the game.
     */
    void updateView();


}
