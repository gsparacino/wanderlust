/**
 * 
 */
package it.wanderlust.game;

import java.util.ArrayList;
import java.util.List;

import it.wanderlust.core.character.enemy.Monster;
import it.wanderlust.core.character.player.Player;
import it.wanderlust.core.combat.Fight;
import it.wanderlust.core.combat.RoundOutcome;
import it.wanderlust.core.exploration.Area;
import it.wanderlust.core.exploration.Map;
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
	List<String> commands = getMainMenuCommands(ui);

	Integer input = -1;

	do {
	    input = ui.mainMenu(commands);
	} while (!validMenuInput(input, commands));

	switch (input) {
	case 0:
	    Player player = createPlayerCharacter(ui);
	    Map map = new Map();
	    play(map, player, ui);
	    break;
	case 1:
	    break;
	default:
	    break;
	}

	quit(ui);
    }

    private static void play(Map map, Player player, WanderlustUI ui) {
	boolean exit = false;

	do {
	    Area currentArea = map.getCurrentArea();
	    ui.showCurrentAreaInfo(currentArea);

	    List<Monster> monsters = currentArea.getMonsters();
	    if (!monsters.isEmpty()) {
		fight(player, monsters, ui);
	    }

	    if (player.getHp() > 0) {
		System.out.println();
		List<String> actions = getAvailablePlayerActions();
		Integer nextPlayerAction = ui.getNextPlayerAction(actions);
		exit = performNextAction(nextPlayerAction, map, player, ui);

	    }
	} while (!exit && player.getHp() > 0);

	ui.gameOver();
    }

    private static boolean performNextAction(Integer nextPlayerAction, Map map, Player player, WanderlustUI ui) {
	boolean exit = false;

	switch (nextPlayerAction) {
	case 0:
	    Integer nextArea = ui.getNextArea(map);
	    map.explore(nextArea, player);
	    break;
	case 1:
	    // TODO: game saving
	    break;
	case 2:
	    exit = true;
	    break;
	default:
	    break;
	}

	return exit;
    }

    private static List<String> getAvailablePlayerActions() {
	List<String> options = new ArrayList<>();

	options.add("Explore");
	options.add("Save");
	options.add("Exit");

	return options;
    }

    private static void fight(Player player, List<Monster> monsters, WanderlustUI ui) {
	boolean playerAlive = true;

	for (int i = 0; playerAlive && i < monsters.size(); i++) {
	    Monster monster = monsters.get(i);
	    ui.showMessage("You'll fight against a " + monster.getName());
	    Fight fight = new Fight(monster, player);

	    do {
		ui.showFightInfo(fight);
		RoundOutcome clash = fight.clash();
		ui.showRoundOutcomeInfo(clash);
	    } while (!fight.isOver());

	    if (player.getHp() == 0) {
		playerAlive = false;
	    } else {
		player.addXp(1);
	    }
	}
    }

    private static boolean validMenuInput(Integer input, List<String> commands) {
	return input >= 0 && input < commands.size();
    }

    private static List<String> getMainMenuCommands(WanderlustUI ui) {
	List<String> commands = new ArrayList<>();

	commands.add("New Game");
	commands.add("Exit");

	return commands;
    }

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
