/**
 * 
 */
package it.wanderlust.core.persistence;

/**
 * @author Gabriele Sparacino
 *
 */
public interface PersistenceManager {
    /**
     * Saves the game data to a file
     *
     * @param name
     *            the name of the save
     * @param game
     *            the game data to save
     * @return <code>true</code> if the game was saved correctly, <code>false</code>
     *         otherwise
     */
    public boolean save(String name, GameData game);

    /**
     * Loads a game from a file
     * 
     * @param save
     *            the save to load
     * @return the game data of the save
     */
    public GameData load(String save);
}
