/**
 * 
 */
package it.wanderlust.io.persistence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import it.wanderlust.core.persistence.GameData;
import it.wanderlust.core.persistence.PersistenceManager;

/**
 * {@link PersistenceManager} that saves and loads games from files
 * 
 * @author Gabriele Sparacino
 *
 */
public class FileManager implements PersistenceManager {

    private final Path directory;

    /**
     * Creates a new {@link PersistenceManager} that writes and reads from the
     * filesystem
     * 
     * @param directory
     *            the directory that will hold the save files
     * @throws IOException
     */
    public FileManager(Path directory) throws IOException {
	this.directory = Files.createDirectories(directory);
    }

    @Override
    public boolean save(String name, GameData game) {
	boolean outcome = false;

	Path path = directory.resolve(name);

	try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(path))) {
	    os.writeObject(game);
	    outcome = true;
	} catch (Exception e) {
	    System.out.println("Game saving failed");
	}

	return outcome;
    }

    @Override
    public GameData load(String save) {
	GameData result = null;

	Path path = directory.resolve(save);

	try (ObjectInputStream is = new ObjectInputStream(Files.newInputStream(path))) {
	    result = (GameData) is.readObject();
	} catch (Exception e) {
	    System.out.println("Game loading failed");
	}

	return result;
    }

}
