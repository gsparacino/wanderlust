/**
 * 
 */
package it.wanderlust.core.combat;

import it.wanderlust.core.character.Character;

/**
 * A duel between {@link Character}s.
 * 
 * @author Gabriele Sparacino
 *
 */
public class Fight {

    private final Character attacker;
    private final Character defender;

    /**
     * Creates a new fight.
     * 
     * @param attacker
     *            the attacking {@link Character}
     * @param defender
     *            the defending {@link Character}
     */
    public Fight(Character attacker, Character defender) {
	this.attacker = attacker;
	this.defender = defender;
    }

    /**
     * @return the attacking {@link Character}
     */
    public Character getAttacker() {
	return attacker;
    }

    /**
     * @return the defending {@link Character}
     */
    public Character getDefender() {
	return defender;
    }

    /**
     * Resolve one round of the fight
     */
    public RoundOutcome clash() {
	Move attackerMove = attacker.attack();
	Move defenderMove = defender.attack();
	Character winner = null;

	if (attackerMove.beats(defenderMove)) {
	    winner = attacker;
	    Integer attackPower = attackerMove.getPower();
	    defender.hurt(attackPower);
	} else if (defenderMove.beats(attackerMove)) {
	    winner = defender;
	    Integer attackPower = defenderMove.getPower();
	    attacker.hurt(attackPower);
	}

	return new RoundOutcome(attacker, defender, winner, attackerMove, defenderMove);
    }

    /**
     * @return <code>true</code> if one of the two opponents folds or dies, else
     *         otherwise
     */
    public boolean isOver() {
	return attacker.getHp() == 0 || defender.getHp() == 0;
    }

}
