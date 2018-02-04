/**
 * 
 */
package it.wanderlust.core.combat;

import it.wanderlust.core.character.Character;

/**
 * The outcome of one round of a fight.
 * 
 * @author Gabriele Sparacino
 *
 */
public class RoundOutcome {

    private final Character attacker;
    private final Move attackerMove;

    private final Character defender;
    private final Move defenderMove;

    private final Character winner;

    /**
     * 
     */
    public RoundOutcome(Character attacker, Character defender, Character winner, Move attackerMove,
	    Move defenderMove) {
	this.attacker = attacker;
	this.attackerMove = attackerMove;

	this.defender = defender;
	this.defenderMove = defenderMove;

	this.winner = winner;
    }

    /**
     * @return the attacking character
     */
    public Character getAttacker() {
	return attacker;
    }

    /**
     * @return the move performed by the attacker
     */
    public Move getAttackerMove() {
	return attackerMove;
    }

    /**
     * @return the defending character
     */
    public Character getDefender() {
	return defender;
    }

    /**
     * @return the move performed by the defender
     */
    public Move getDefenderMove() {
	return defenderMove;
    }

    /**
     * @return the winner of the round
     */
    public Character getWinner() {
	return winner;
    }

}
