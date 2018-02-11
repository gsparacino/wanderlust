/**
 * 
 */
package it.wanderlust.core.exploration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.wanderlust.core.character.enemy.Monster;

/**
 * An area of the map.
 * 
 * @author Gabriele Sparacino
 *
 */
public class Area implements Serializable {

    private static final long serialVersionUID = -1790888380675244210L;
    public final List<Monster> monsters = new ArrayList<>();

    public enum Type {
	FOREST, PLAINS, MOUNTAINS, SWAMP
    }

    private final Type type;

    /**
     * Creates a new area (IE region) of the map
     */
    public Area(Type type) {
	this.type = type;
    }

    /**
     * @return the type of the area
     */
    public Type getType() {
	return type;
    }

    /**
     * Adds a {@link Monster} to this area
     * 
     * @param monster
     *            the {@link Monster} to add
     */
    public void addMonster(Monster monster) {
	this.monsters.add(monster);
    }

    /**
     * @return the monsters of this area
     */
    public List<Monster> getMonsters() {
	return monsters;
    }

}
