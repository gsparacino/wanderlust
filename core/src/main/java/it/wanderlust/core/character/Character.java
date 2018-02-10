/**
 * 
 */
package it.wanderlust.core.character;

import it.wanderlust.core.combat.Move;

/**
 * @author Gabriele Sparacino
 *
 */
public abstract class Character {

    private final String name;
    private Integer hp;

    /**
     * Creates a new character with the provided name
     */
    public Character(String name, Integer maxHp) {
	this.name = name;
	this.hp = maxHp;
    }

    /**
     * @return the name of the character
     */
    public String getName() {
	return name;
    }

    /**
     * @return the current Health Points of the character
     */
    public Integer getHp() {
	return this.hp;
    }

    /**
     * Decreases the Health Points of the character
     * 
     * @param hp
     *            the amount of HP to subtract from the character
     */
    public void hurt(Integer hp) {
	this.hp -= hp;
    }

    /**
     * Increases the Health Points of the character
     * 
     * @param hp
     *            the amount of HP to add to the character
     */
    public void heal(Integer hp) {
	this.hp += hp;
    }

    /**
     * @return the attack {@link Move} performed by the character
     */
    public abstract Move attack();

    /**
     * @return <code>true</code> if the character is alive, else otherwise
     */
    public boolean isAlive() {
	return this.getHp() > 0;
    };

}
