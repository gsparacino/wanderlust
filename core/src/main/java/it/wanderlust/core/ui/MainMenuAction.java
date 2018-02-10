/**
 * 
 */
package it.wanderlust.core.ui;

/**
 * The actions available for the player in the main menu.
 * 
 * @author Gabriele Sparacino
 *
 */
public enum MainMenuAction {

    /**
     * Start a new game
     */
    NEW_GAME("New game"),

    /**
     * Load a previously saved game
     */
    LOAD("Load game"),

    /**
     * Quit the game
     */
    EXIT("Quit");

    private String label;

    private MainMenuAction(String label) {
	this.label = label;
    }

    @Override
    public String toString() {
	return this.label;
    }
}
