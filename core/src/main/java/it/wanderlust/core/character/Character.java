/**
 * 
 */
package it.wanderlust.core.character;

/**
 * @author Gabriele Sparacino
 *
 */
public abstract class Character {

    private final String name;

    /**
     * Creates a new character with the provided name
     */
    public Character(String name) {
	this.name = name;
    }

    /**
     * @return the name of the character
     */
    public String getName() {
	return name;
    }

}
