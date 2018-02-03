/**
 * 
 */
package it.wanderlust.core.character.player;

import it.wanderlust.core.character.Character;

/**
 * The character used by the player.
 * 
 * @author Gabriele Sparacino
 *
 */
public class Player extends Character {

    private Integer xp;

    /**
     * Creates a new character for the player with the provided name
     */
    public Player(String name) {
	super(name);
	this.xp = 0;
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
}
