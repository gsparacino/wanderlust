/**
 * 
 */
package it.wanderlust.core.character.player;

import it.wanderlust.core.combat.Move;

/**
 * Abstracts the interactions between the user interface and the game logic.
 * 
 * @author Gabriele Sparacino
 *
 */
public interface PlayerUI {

    /**
     * @return the combat move chosen by the player
     */
    public Move.Type getCombatMove();

    /**
     * Notifies an event to the player
     * 
     * @param message
     *            the message of the event to notify
     */
    public void notifyEvent(String message);
}
