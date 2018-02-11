/**
 * 
 */
package it.wanderlust.core.persistence;

import java.io.Serializable;

import it.wanderlust.core.character.player.Player;
import it.wanderlust.core.exploration.Map;

/**
 * The game data that should be saved or loaded
 * 
 * @author Gabriele Sparacino
 *
 */
public class GameData implements Serializable {

    private static final long serialVersionUID = -5000773198535771390L;
    private final Player player;
    private final Map map;

    public GameData(Player player, Map map) {
	this.player = player;
	this.map = map;
    }

    /**
     * @return the player data
     */
    public Player getPlayer() {
	return player;
    }

    /**
     * @return the map data
     */
    public Map getMap() {
	return map;
    }

}
