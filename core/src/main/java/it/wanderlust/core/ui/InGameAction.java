/**
 * 
 */
package it.wanderlust.core.ui;

/**
 * The actions that can be performed by the player while in a game
 * 
 * @author Gabriele Sparacino
 *
 */
public enum InGameAction {

    /**
     * Keep exploring the map
     */
    EXPLORE("Explore"),

    /**
     * Save the current game
     */
    SAVE("Save game"),

    /**
     * Quit the game
     */
    QUIT("Quit");

    private String label;

    private InGameAction(String label) {
	this.label = label;
    }

    @Override
    public String toString() {
	return this.label;
    }
}
