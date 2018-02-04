/**
 * 
 */
package it.wanderlust.core.combat;

/**
 * A combat move.
 * 
 * @author Gabriele Sparacino
 *
 */
public class Move {

    public enum Type {
	ROCK, PAPER, SCISSORS;
    }

    private final Type type;
    private final Integer power;

    /**
     * Creates a new combat move
     */
    public Move(Type type, Integer power) {
	this.type = type;
	this.power = power;
    }

    /**
     * @return the type of the combat move
     */
    public Type getType() {
	return type;
    }

    /**
     * @return the power of the combat move
     */
    public Integer getPower() {
	return power;
    }

    /**
     * Determines if this move beats the move of the opponent
     * 
     * @param opponentMove
     *            the move of the opponent
     * @return true if this move beats the move of the opponent, else otherwise
     */
    public boolean beats(Move opponentMove) {
	return (this.type.equals(Move.Type.PAPER) && opponentMove.type.equals(Move.Type.ROCK))
		|| (this.type.equals(Move.Type.ROCK) && opponentMove.type.equals(Move.Type.SCISSORS))
		|| (this.type.equals(Move.Type.SCISSORS) && opponentMove.type.equals(Move.Type.PAPER));
    }

}
