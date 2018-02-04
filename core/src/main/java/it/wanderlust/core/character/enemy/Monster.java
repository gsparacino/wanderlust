/**
 * 
 */
package it.wanderlust.core.character.enemy;

import java.util.Random;

import it.wanderlust.core.character.Character;
import it.wanderlust.core.combat.Move;
import it.wanderlust.core.combat.Move.Type;

/**
 * A dreadful monster.
 * 
 * @author Gabriele Sparacino
 *
 */
public class Monster extends Character {

    /**
     * Creates a new monster
     */
    public Monster(String name, Integer maxHp) {
	super(name, maxHp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see it.wanderlust.core.character.Character#attack()
     */
    @Override
    public Move attack() {
	int pick = new Random().nextInt(Move.Type.values().length);
	Type attackMove = Move.Type.values()[pick];

	return new Move(attackMove, 1);
    }

}
