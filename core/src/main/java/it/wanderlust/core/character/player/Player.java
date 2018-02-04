/**
 * 
 */
package it.wanderlust.core.character.player;

import it.wanderlust.core.character.Character;
import it.wanderlust.core.combat.Move;
import it.wanderlust.core.combat.Move.Type;
import it.wanderlust.core.ui.WanderlustUI;

/**
 * The character used by the player.
 * 
 * @author Gabriele Sparacino
 *
 */
public class Player extends Character {

    private Integer xp;
    private final WanderlustUI ui;

    /**
     * Creates a new character for the player with the provided name
     */
    public Player(String name, Integer maxHp, WanderlustUI ui) {
	super(name, maxHp);
	this.xp = 0;
	this.ui = ui;
    }

    /**
     * Adds eXperience Points to the character
     * 
     * @param xp
     *            the amount of XP to add
     */
    public void addXp(Integer xp) {
	ui.showMessage("You gained " + xp + "XP");
	this.xp += xp;
    }

    /**
     * @return the eXperience Points of the character
     */
    public Integer getXp() {
	return this.xp;
    }

    @Override
    public Move attack() {
	Type combatMove = ui.getCombatMove();
	return new Move(combatMove, 1);
    }

    @Override
    public void hurt(Integer hp) {
	super.hurt(hp);
	ui.showMessage("You lost " + hp + " HP");
    }

    @Override
    public void heal(Integer hp) {
	super.heal(hp);
	ui.showMessage("You gained " + hp + " HP");
    }
}
