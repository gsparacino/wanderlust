/**
 * 
 */
package it.wanderlust.io.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import it.wanderlust.core.character.player.Player;
import it.wanderlust.core.exploration.Map;
import it.wanderlust.core.persistence.GameData;

/**
 * @author Gabriele Sparacino
 *
 */
public class FileManagerTest extends Assert {

    private static final String TEST_FILE_NAME = "WRITE_TEST";

    /**
     * Test method for
     * {@link it.wanderlust.io.persistence.FileManager#save(it.wanderlust.core.persistence.GameData, String)}.
     * 
     * @throws IOException
     */
    @Test
    public void testSave() throws IOException {
	OutputStream os = Mockito.mock(OutputStream.class);
	Path dirPath = mockDirPathFileSystem(os, null);

	FileManager fileManager = new FileManager(dirPath);

	GameData data = createDummyGameData();

	fileManager.save(TEST_FILE_NAME, data);

	Mockito.verify(dirPath).resolve(TEST_FILE_NAME);
	Mockito.verify(os, Mockito.atLeastOnce()).write(Mockito.any(), Mockito.anyInt(), Mockito.anyInt());
    }

    /**
     * Creates a dummy {@link GameData}
     * 
     * @return a dummy {@link GameData}
     */
    private GameData createDummyGameData() {
	Map map = Mockito.mock(Map.class);
	Player player = Mockito.mock(Player.class);
	GameData data = new GameData(player, map);
	return data;
    }

    /**
     * Test method for
     * {@link it.wanderlust.io.persistence.FileManager#load(java.lang.String)}.
     * 
     * @throws IOException
     */
    @Test
    public void testLoad() throws IOException {
	InputStream is = createDummySaveReader();
	Path dirPath = mockDirPathFileSystem(null, is);

	FileManager fileManager = new FileManager(dirPath);

	GameData game = fileManager.load(TEST_FILE_NAME);

	Mockito.verify(dirPath).resolve(TEST_FILE_NAME);
	assertNotNull(game);
    }

    /**
     * Creates a dummy save reader
     * 
     * @return an {@link InputStream} that returns some dummy game data
     * @throws IOException
     */
    private InputStream createDummySaveReader() throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ObjectOutputStream os = new ObjectOutputStream(baos);
	os.writeObject(createDummyGameData());
	os.flush();
	os.close();

	InputStream is = new ByteArrayInputStream(baos.toByteArray());
	return is;
    }

    /**
     * Mocks the save directory path
     * 
     * @param os
     *            the {@link OutputStream} used by the mock to write the save file
     * @param is
     *            TODO
     * @return the mocked directory {@link Path}
     * @throws IOException
     */
    private Path mockDirPathFileSystem(OutputStream os, InputStream is) throws IOException {
	Path filePath = Mockito.mock(Path.class);
	mockFileSystem(filePath, os, is);

	Path dirPath = Mockito.mock(Path.class);
	mockFileSystem(dirPath, Mockito.mock(OutputStream.class), null);
	Mockito.when(dirPath.resolve(TEST_FILE_NAME)).thenReturn(filePath);
	return dirPath;
    }

    /**
     * Mocks the file system provider of a {@link Path}.
     * 
     * @param path
     *            the path whose {@link FileSystemProvider} should be mocked
     * @param os
     *            the {@link OutputStream} used by the mock
     * @param is
     *            TODO
     * @throws IOException
     */
    private void mockFileSystem(Path path, OutputStream os, InputStream is) throws IOException {
	FileSystem fileSystem = Mockito.mock(FileSystem.class);
	FileSystemProvider provider = Mockito.mock(FileSystemProvider.class);

	Mockito.when(provider.newOutputStream(path)).thenReturn(os);
	Mockito.when(provider.newInputStream(path)).thenReturn(is);
	Mockito.when(fileSystem.provider()).thenReturn(provider);
	Mockito.when(path.getFileSystem()).thenReturn(fileSystem);
    }

}
