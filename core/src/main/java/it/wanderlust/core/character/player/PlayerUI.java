/**
 * 
 */
package it.wanderlust.core.character.player;

import it.wanderlust.core.combat.Move;

/**
 * Defines the interactions between the user interface and the game logic.
 * 
 * @author Gabriele Sparacino
 *
 */
public interface PlayerUI {

    /**
     * @return the combat move chosen by the player
     */
    public Move.Type getCombatMove();
}
