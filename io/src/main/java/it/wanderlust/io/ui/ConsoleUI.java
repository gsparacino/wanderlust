/**
 * 
 */
package it.wanderlust.io.ui;

import java.util.List;
import java.util.Scanner;

import it.wanderlust.core.character.Character;
import it.wanderlust.core.character.enemy.Monster;
import it.wanderlust.core.combat.Fight;
import it.wanderlust.core.combat.Move;
import it.wanderlust.core.combat.Move.Type;
import it.wanderlust.core.combat.RoundOutcome;
import it.wanderlust.core.exploration.Area;
import it.wanderlust.core.exploration.Map;
import it.wanderlust.core.persistence.GameData;
import it.wanderlust.core.persistence.PersistenceManager;
import it.wanderlust.core.ui.InGameAction;
import it.wanderlust.core.ui.MainMenuAction;
import it.wanderlust.core.ui.WanderlustUI;

/**
 * A console based User Interface
 * 
 * @author Gabriele Sparacino
 *
 */
public class ConsoleUI implements WanderlustUI {

    private final Scanner scanner;

    /**
     * Creates a new console based user interface
     */
    public ConsoleUI() {
	this.scanner = new Scanner(System.in);
    }

    private void showSeparatorLine() {
	System.out.println("-----------------------------------------------------");
    }

    private void printCombatMoves() {

    }

    @Override
    public void showTitle() {
	System.out.println(" _    _                 _           _           _    ");
	System.out.println("| |  | |               | |         | |         | |   ");
	System.out.println("| |  | | __ _ _ __   __| | ___ _ __| |_   _ ___| |_  ");
	System.out.println("| |/\\| |/ _` | '_ \\ / _` |/ _ \\ '__| | | | / __| __| ");
	System.out.println("\\  /\\  / (_| | | | | (_| |  __/ |  | | |_| \\__ \\ |_  ");
	System.out.println(" \\/  \\/ \\__,_|_| |_|\\__,_|\\___|_|  |_|\\__,_|___/\\__| ");
	System.out.println("");
	showSeparatorLine();
    }

    @Override
    public void showMessage(String message) {
	System.out.println(message);
    }

    @Override
    public String getInput(String message) {
	if (message == null) {
	    message = "";
	}

	System.out.print(message + "> ");

	return scanner.next();
    }

    @Override
    public void quit() {
	scanner.close();
	System.out.println();
	System.out.println("Bye!");
	System.out.println();
    }

    @Override
    public Type getCombatMove() {
	Type result = null;

	do {
	    String input = this.getInput("[R]ock [P]aper [S]cissors");

	    switch (input) {
	    case "R":
		result = Type.ROCK;
		break;
	    case "P":
		result = Type.PAPER;
		break;
	    case "S":
		result = Type.SCISSORS;
		break;
	    default:
		break;
	    }
	} while (result == null);

	return result;
    }

    @Override
    public void showFightInfo(Fight fight) {
	Character attacker = fight.getAttacker();
	Character defender = fight.getDefender();

	System.out.println();
	System.out.println(attacker.getName() + " - " + attacker.getHp());
	System.out.println("- VS -");
	System.out.println(defender.getName() + " - " + defender.getHp());
    }

    @Override
    public void showCurrentAreaInfo(Area area) {
	it.wanderlust.core.exploration.Area.Type type = area.getType();
	List<Monster> monsters = area.getMonsters();

	System.out.println("You're now in a " + type.toString());
	System.out.println("There are " + monsters.size() + " monsters here!");
    }

    @Override
    public void showRoundOutcomeInfo(RoundOutcome outcome) {
	String attackerName = outcome.getAttacker().getName();
	Move attackerMove = outcome.getAttackerMove();

	String defenderName = outcome.getDefender().getName();
	Move defenderMove = outcome.getDefenderMove();

	System.out.println(attackerName + " picked " + attackerMove.getType().toString());
	System.out.println(defenderName + " picked " + defenderMove.getType().toString());

	Character winner = outcome.getWinner();
	if (winner != null) {
	    System.out.println(winner.getName() + " won this round!");
	} else {
	    System.out.println("It's a draw");
	}

	System.out.println();
    }

    @Override
    public void gameOver() {
	System.out.println(" --- GAME OVER ---");
    }

    @Override
    public Integer getNextArea(Map map) {
	List<Area> neighboringAreas = map.getNeighboringAreas();
	Integer choice = null;

	System.out.println("There are " + neighboringAreas.size() + " areas that can be explored from this region");

	do {
	    for (int i = 0; i < neighboringAreas.size(); i++) {
		Area area = neighboringAreas.get(i);
		System.out.println("[" + i + "] " + area.getType().toString());
	    }
	    String input = getInput("Explore");
	    choice = Integer.valueOf(input);
	} while (choice < 0 || choice > neighboringAreas.size());

	System.out.println();
	return choice;
    }

    @Override
    public MainMenuAction mainMenu() {
	MainMenuAction choice = null;

	do {
	    int i = 0;

	    System.out.println();
	    for (MainMenuAction action : MainMenuAction.values()) {
		System.out.println("[" + i + "] " + action.toString());
		i++;
	    }
	    System.out.println();

	    try {
		String input = getInput(null);
		Integer value = Integer.valueOf(input);
		if (value >= 0 && value < MainMenuAction.values().length) {
		    choice = MainMenuAction.values()[value];
		}
	    } catch (Exception e) {
		System.out.println("Invalid choice");
	    }

	} while (choice == null);

	return choice;
    }

    @Override
    public InGameAction inGameMenu() {
	InGameAction choice = null;

	do {
	    int i = 0;

	    System.out.println();
	    System.out.println("What do you want to do next?");
	    System.out.println();
	    for (InGameAction action : InGameAction.values()) {
		System.out.println("[" + i + "] " + action.toString());
		i++;
	    }
	    System.out.println();

	    String input = getInput(null);

	    try {
		Integer value = Integer.valueOf(input);
		if (value >= 0 && value < InGameAction.values().length) {
		    choice = InGameAction.values()[value];
		}
	    } catch (Exception e) {
		System.out.println("Invalid choice");
	    }

	} while (choice == null);

	return choice;
    }

    @Override
    public void save(PersistenceManager fileManager, GameData game) {
	System.out.println();

	String name = game.getPlayer().getName();
	boolean done = fileManager.save(name, game);

	if (done) {
	    System.out.println("Created a new save named ".concat(name));
	}
    }

    @Override
    public GameData load(PersistenceManager fileManager) {
	System.out.println();
	GameData game = null;

	do {
	    String save = getInput("Name of the character");
	    game = fileManager.load(save);
	} while (game == null);

	return game;
    }

}
