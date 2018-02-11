/**
 * 
 */
package it.wanderlust.core.combat;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import it.wanderlust.core.character.Character;
import it.wanderlust.core.character.enemy.Monster;

/**
 * @author Gabriele Sparacino
 *
 */
public class FightTest extends Assert {

    private static final String DEFENDER_NAME = "Defender";
    private static final String ATTACKER_NAME = "Attacker";

    static Character dummyCharacter(String name) {
	return new Monster(name, 1);
    }

    static Fight dummyFight() {
	Character attacker = dummyCharacter(ATTACKER_NAME);
	Character defender = dummyCharacter(DEFENDER_NAME);

	Fight fight = new Fight(attacker, defender);
	return fight;
    }

    /**
     * Test method for
     * {@link it.wanderlust.core.combat.Fight#Fight(it.wanderlust.core.character.Character, it.wanderlust.core.character.Character)}.
     */
    @Test
    public void testFight() {
	Fight fight = dummyFight();

	assertNotNull("Failed to create a new Fight", fight);
    }

    /**
     * Test method for {@link it.wanderlust.core.combat.Fight#getAttacker()}.
     */
    @Test
    public void testGetAttacker() {
	Fight fight = dummyFight();

	Character attacker = fight.getAttacker();

	assertNotNull("Fight.getAttacker() returned null", attacker);
	String name = attacker.getName();
	assertTrue("Fight.getAttacker() returned the wrong Character", ATTACKER_NAME.equals(name));
    }

    /**
     * Test method for {@link it.wanderlust.core.combat.Fight#getDefender()}.
     */
    @Test
    public void testGetDefender() {
	Fight fight = dummyFight();

	Character defender = fight.getDefender();

	assertNotNull("Fight.getDefender() returned null", defender);
	String name = defender.getName();
	assertTrue("Fight.getDefender() returned the wrong Character", DEFENDER_NAME.equals(name));
    }

    /**
     * Test method for {@link it.wanderlust.core.combat.Fight#attackerTurn()}.
     */
    @Test
    public void testClash() {
	Character attacker = Mockito.mock(Character.class);
	Character defender = Mockito.mock(Character.class);

	Mockito.when(attacker.attack()).thenReturn(new Move(Move.Type.ROCK, 1));
	Mockito.when(defender.attack()).thenReturn(new Move(Move.Type.PAPER, 1));

	Fight fight = new Fight(attacker, defender);

	RoundOutcome outcome = fight.clash();

	Mockito.verify(attacker).attack();
	Mockito.verify(defender).attack();
	assertTrue("Fight.clash() did not return the expected outcome", outcome.getWinner().equals(defender));
    }

}
