/**
 * 
 */
package it.wanderlust.core.character.enemy;

/**
 * A factory for {@link Monster}s.
 * 
 * @author Gabriele Sparacino
 *
 */
public class MonsterFactory {

    private MonsterFactory() {

    }

    /**
     * Generates a new giant mosquito
     * 
     * @return a giant mosquito {@link Monster}
     */
    public static Monster getGiantMosquito() {
	return new Monster("Giant mosquito", 1);
    }

    /**
     * Generates a new bear
     * 
     * @return a bear {@link Monster}
     */
    public static Monster getBear() {
	return new Monster("Bear", 5);
    }

    /**
     * Generates a new snake
     * 
     * @return a snake {@link Monster}
     */
    public static Monster getSnake() {
	return new Monster("Snake", 2);
    }

    /**
     * Generates a new snow leopard
     * 
     * @return a snow leopard {@link Monster}
     */
    public static Monster getSnowLeopard() {
	return new Monster("Snow leopard", 3);
    }

}
