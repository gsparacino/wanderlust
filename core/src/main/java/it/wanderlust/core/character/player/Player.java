/**
 * 
 */
package it.wanderlust.core.character.player;

import it.wanderlust.core.character.Character;
import it.wanderlust.core.combat.Move;
import it.wanderlust.core.combat.Move.Type;

/**
 * The character used by the player.
 * 
 * @author Gabriele Sparacino
 *
 */
public class Player extends Character {

    private Integer xp;
    private final PlayerUI playerUI;

    /**
     * Creates a new character for the player with the provided name
     */
    public Player(String name, Integer maxHp, PlayerUI playerUI) {
	super(name, maxHp);
	this.xp = 0;
	this.playerUI = playerUI;
    }

    /**
     * Adds eXperience Points to the character
     * 
     * @param xp
     *            the amount of XP to add
     */
    public void addXp(Integer xp) {
	this.xp += xp;
    }

    /**
     * @return the eXperience Points of the character
     */
    public Integer getXp() {
	return this.xp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see it.wanderlust.core.character.Character#attack()
     */
    @Override
    public Move attack() {
	Type combatMove = playerUI.getCombatMove();
	return new Move(combatMove, 1);
    }

}
