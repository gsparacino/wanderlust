/**
 * 
 */
package it.wanderlust.core.ui;

import it.wanderlust.core.combat.Fight;
import it.wanderlust.core.combat.Move;
import it.wanderlust.core.combat.RoundOutcome;
import it.wanderlust.core.exploration.Area;
import it.wanderlust.core.exploration.Map;

/**
 * Abstracts the basic interactions between the core game logic and the User
 * Interface.
 * 
 * @author Gabriele Sparacino
 *
 */
/**
 * @author Gabriele Sparacino
 *
 */
public interface WanderlustUI {

    /**
     * Displays the game title
     */
    public void showTitle();

    /**
     * Shows a generic message
     * 
     * @param message
     *            the text of the message
     */
    public void showMessage(String message);

    /**
     * Displays the main menu and returns the action chosen by the player
     * 
     * @return the action chosen by the player
     */
    public MainMenuAction mainMenu();

    /**
     * Displays the in-game menu and returns the one chosen by the player
     * 
     * @return the action chosen by the player
     */
    public InGameAction inGameMenu();

    /**
     * Gets the input from the user and executes the provided interpreter
     * 
     * @param message
     *            the message to display
     * @return the user input
     */
    public String getInput(String message);

    /**
     * Displays the information on a {@link Fight}
     * 
     * @param fight
     *            the {@link Fight} to display
     */
    public void showFightInfo(Fight fight);

    /**
     * Displays the information on a combat round
     * 
     * @param outcome
     *            the outcome of the combat round
     */
    public void showRoundOutcomeInfo(RoundOutcome outcome);

    /**
     * Displays the information on the current {@link Area}
     * 
     * @param area
     *            the current {@link Area}
     */
    public void showCurrentAreaInfo(Area area);

    /**
     * Displays the GAME OVER message
     */
    public void gameOver();

    /**
     * @return the combat move chosen by the player
     */
    public Move.Type getCombatMove();

    /**
     * @return the index of next {@link Area} of the map to explore
     */
    public Integer getNextArea(Map map);

    /**
     * Quits the game
     */
    public void quit();
}
