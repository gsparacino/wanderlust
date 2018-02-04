/**
 * 
 */
package it.wanderlust.io.system;

import java.io.File;

/**
 * @author Gabriele Sparacino
 *
 */
public interface GameLoader {

    /**
     * Saves the game data to a file
     * 
     * @param game
     *            the game data to save
     */
    public void save(GameData game);

    /**
     * Loads a game from a file
     * 
     * @param file
     *            the file to load
     * @return the game data of the file
     */
    public GameData load(File file);
}
