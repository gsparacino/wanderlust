/**
 * 
 */
package it.wanderlust.core.exploration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.wanderlust.core.character.player.Player;
import it.wanderlust.core.exploration.Area.Type;

/**
 * The game map.
 * 
 * @author Gabriele Sparacino
 *
 */
public class Map implements Serializable {

    private static final long serialVersionUID = 6133108113919098380L;
    private Area currentArea;
    private List<Area> neighboringAreas;

    /**
     * Creates a new game map
     */
    public Map() {
	this.currentArea = generateNewArea();
	this.neighboringAreas = generateNeighboringAreas();
    }

    /**
     * Explores one of the neighboring {@link Area}.
     * 
     * @param index
     *            the index of the {@link Area} to explore
     * @param player
     *            the exploring {@link Player}
     * @return the outcome of the exploration
     */
    public Exploration explore(int index, Player player) {
	Area areaToExplore = neighboringAreas.get(index);
	currentArea = areaToExplore;
	neighboringAreas = generateNeighboringAreas();
	return new Exploration(player, areaToExplore);
    }

    /**
     * @return a {@link List} of randomly generated neighboring {@link Area}
     */
    private List<Area> generateNeighboringAreas() {
	List<Area> result = new ArrayList<>();
	int size = new Random().nextInt(2) + 1;

	for (int i = 0; i < size; i++) {
	    result.add(generateNewArea());
	}

	return result;
    }

    /**
     * @return the {@link List} of {@link Area} that can be explored
     * 
     */
    public List<Area> getNeighboringAreas() {
	return neighboringAreas;
    }

    /**
     * @return the current {@link Area} of the map
     */
    public Area getCurrentArea() {
	return currentArea;
    }

    /**
     * @return a new {@link Area} for the map
     */
    private Area generateNewArea() {
	int pick = new Random().nextInt(Area.Type.values().length);
	Type newAreaType = Area.Type.values()[pick];

	return new Area(newAreaType);
    }

}
