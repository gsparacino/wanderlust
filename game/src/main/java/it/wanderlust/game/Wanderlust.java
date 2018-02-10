/**
 * 
 */
package it.wanderlust.game;

import java.util.List;

import it.wanderlust.core.character.enemy.Monster;
import it.wanderlust.core.character.player.Player;
import it.wanderlust.core.combat.Fight;
import it.wanderlust.core.combat.RoundOutcome;
import it.wanderlust.core.exploration.Area;
import it.wanderlust.core.exploration.Map;
import it.wanderlust.core.ui.InGameAction;
import it.wanderlust.core.ui.MainMenuAction;
import it.wanderlust.core.ui.WanderlustUI;
import it.wanderlust.io.ui.ConsoleUI;

/**
 * The main class of the application
 * 
 * @author Gabriele Sparacino
 *
 */
public class Wanderlust {

    private Wanderlust() {

    }

    /**
     * The main method
     */
    public static void main(String[] args) {
	WanderlustUI ui = new ConsoleUI();

	ui.showTitle();

	MainMenuAction action = ui.mainMenu();

	switch (action) {
	case NEW_GAME:
	    Player player = createPlayerCharacter(ui);
	    Map map = new Map();
	    play(map, player, ui);
	    break;
	case LOAD:
	    // TODO: persistence
	    break;
	case EXIT:
	    quit(ui);
	    break;
	default:
	    break;
	}

    }

    /**
     * Plays the game
     * 
     * @param map
     *            the game map
     * @param player
     *            the player's character
     * @param ui
     *            the User Interface
     */
    private static void play(Map map, Player player, WanderlustUI ui) {
	boolean exit = false;

	do {
	    Area currentArea = map.getCurrentArea();
	    ui.showCurrentAreaInfo(currentArea);

	    List<Monster> monsters = currentArea.getMonsters();
	    if (!monsters.isEmpty()) {
		fight(player, monsters, ui);
	    }

	    if (player.isAlive()) {
		InGameAction nextAction = ui.inGameMenu();
		exit = performNextAction(nextAction, map, player, ui);
	    }
	} while (player.isAlive() && !exit);

	ui.gameOver();
    }

    /**
     * Performs the next in-game action chosen by the player
     * 
     * @param nextAction
     *            the action chosen by the player
     * @param map
     *            the current game map
     * @param player
     *            the player's character
     * @param ui
     *            the game's User Interface
     * @return <code>true</code> if the game should return to the main menu,
     *         <code>false</code> otherwise
     */
    private static boolean performNextAction(InGameAction nextAction, Map map, Player player, WanderlustUI ui) {
	boolean exit = false;

	switch (nextAction) {
	case EXPLORE:
	    Integer nextArea = ui.getNextArea(map);
	    map.explore(nextArea, player);
	    break;
	case SAVE:
	    // TODO: persistence
	    break;
	case QUIT:
	    exit = true;
	    break;
	default:
	    break;
	}

	return exit;
    }

    private static void fight(Player player, List<Monster> monsters, WanderlustUI ui) {

	for (int i = 0; player.isAlive() && i < monsters.size(); i++) {
	    Monster monster = monsters.get(i);
	    ui.showMessage("You'll fight against a " + monster.getName());
	    Fight fight = new Fight(monster, player);

	    do {
		ui.showFightInfo(fight);
		RoundOutcome clash = fight.clash();
		ui.showRoundOutcomeInfo(clash);
	    } while (!fight.isOver());

	    if (player.isAlive()) {
		player.addXp(1);
	    }
	}
    }

    /**
     * Player creation dialog
     * 
     * @param ui
     *            the game User Interface
     * @return the newly created {@link Player}
     */
    public static Player createPlayerCharacter(WanderlustUI ui) {
	String input = ui.getInput("Character name");
	return new Player(input, 10, ui);
    }

    /**
     * Quits the game
     * 
     * @param ui
     *            the User Interface
     */
    private static void quit(WanderlustUI ui) {
	ui.quit();
    }

}
