/**
 * 
 */
package it.wanderlust.core.ui;

import java.util.List;

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
     * Displays the main menu and returns the user input
     * 
     * @param commands
     *            the {@link List} of commands of the menu
     * @return <code>true</code> if the game should be closed
     */
    public Integer mainMenu(List<String> commands);

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
     * Displays the in-game menu and returns the user's next action
     *
     * @param options
     *            the {@link List} of options
     * @return the user's choice
     */
    public Integer getNextPlayerAction(List<String> options);

    /**
     * @return the index of next {@link Area} of the map to explore
     */
    public Integer getNextArea(Map map);

    /**
     * Quits the game
     */
    public void quit();
}
