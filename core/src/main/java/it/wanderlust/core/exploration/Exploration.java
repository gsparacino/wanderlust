/**
 * 
 */
package it.wanderlust.core.exploration;

import java.util.Random;
import java.util.function.Supplier;

import it.wanderlust.core.character.enemy.Monster;
import it.wanderlust.core.character.enemy.MonsterFactory;
import it.wanderlust.core.character.player.Player;
import it.wanderlust.core.exploration.Area.Type;

/**
 * The exploration of an {@link Area}
 * 
 * @author Gabriele Sparacino
 *
 */
public class Exploration {

    /**
     * Creates a new exploration result
     * 
     * @param player
     *            the exploring {@link Player}
     * @param area
     *            the {@link Area} to explore
     */
    public Exploration(Player player, Area area) {
	int chanceToSpawnMonster;
	int chanceToHeal;
	int chanceToHurt;
	Supplier<Monster> monsterGenerationFn;

	Type type = area.getType();

	switch (type) {
	case FOREST:
	    chanceToHeal = 50;
	    chanceToHurt = 30;
	    chanceToSpawnMonster = 70;
	    monsterGenerationFn = MonsterFactory::getSnake;
	    break;
	case MOUNTAINS:
	    chanceToHeal = 30;
	    chanceToHurt = 50;
	    chanceToSpawnMonster = 10;
	    monsterGenerationFn = MonsterFactory::getSnowLeopard;
	    break;
	case PLAINS:
	    chanceToHeal = 70;
	    chanceToHurt = 10;
	    chanceToSpawnMonster = 30;
	    monsterGenerationFn = MonsterFactory::getBear;
	    break;
	case SWAMP:
	    chanceToHeal = 10;
	    chanceToHurt = 70;
	    chanceToSpawnMonster = 50;
	    monsterGenerationFn = MonsterFactory::getGiantMosquito;
	    break;
	default:
	    throw new IllegalArgumentException("Area type not supported");
	}

	spawnMonster(area, monsterGenerationFn, chanceToSpawnMonster);
	healPlayer(player, 1, chanceToHeal);
	hurtPlayer(player, 1, chanceToHurt);
    }

    private void spawnMonster(Area area, Supplier<Monster> monsterGenerationFn, int chance) {
	Runnable runnable = () -> {
	    Monster monster = monsterGenerationFn.get();
	    area.addMonster(monster);
	};

	rollAndExecute(runnable, chance);
    }

    private void healPlayer(Player player, Integer hp, int chance) {
	Runnable runnable = () -> player.heal(hp);
	rollAndExecute(runnable, chance);
    }

    private void hurtPlayer(Player player, Integer hp, int chance) {
	Runnable runnable = () -> player.hurt(hp);
	rollAndExecute(runnable, chance);
    }

    private void rollAndExecute(Runnable fn, int chance) {
	int roll = new Random().nextInt(100);
	if (roll < chance) {
	    fn.run();
	}
    }

}
