package fr.iut.zen.game.MainPart;

import java.awt.Color;

import java.awt.geom.Point2D;

import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.GameManagementFile;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;

/**
 * @version 1.2.6
 *
 */
public class LoopHeroMultiController  {
	private LoopHeroData data = new LoopHeroData(12, 21);
	private final LoopHeroView view = LoopHeroView.initGameGraphics(200, 50, 600, data);
	private final TimeData timeData = new TimeData();
	private final static int USER_ACTION_DELAY = 200; // attention, ne doit pas dépasser BOB_DELAY

	private static void printScreenInfo(ApplicationContext context) {
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
		System.out.println("size of the screen (%.0f x %.0f)".formatted(width, height));
	}

	private void doKeyAction(ApplicationContext context, Event event) {
		switch (event.getKey()) {
		case SPACE -> {
			System.out.println("Fin du jeu");
			context.exit(0);
			throw new AssertionError("ne devrait pas arriver");
		}

		case O -> GameManagementFile.SaveGame(data);
		case I -> {
			LoopHeroData dataSave = GameManagementFile.LoadGame();
			if (dataSave != null) {
				data = dataSave;
			}
			else {
				System.out.println("Impossible de charger la partie");
			}
		}
		case S -> timeData.stop();
		case D -> timeData.start();
		case E -> data.equip();
		case DOWN -> timeData.changeGameSpeed(2);
		case LEFT -> timeData.changeGameSpeed(1);
		case RIGHT -> timeData.changeGameSpeed(3);
		default -> System.out.println("Touche inactive : " + event.getKey());
		}
	}

	private void doMouseAction(ApplicationContext context, Event event) {


		Point2D.Float location = event.getLocation();
		if (!data.hasASelectedCard()) {
			data.selectCard(view.checkCard(location, data.getHand()));
		} else if (!data.hasASelectedCell()) { // no cell is selected
			if (view.checkXY(location)) {
				data.selectCell(view.lineFromY(location.y), view.columnFromX(location.x));
			} else {
				data.unselect();
			}
		}

		if (!data.hasASelectedEquipment()){
			data.selectEquipment(view.checkBagEquipmentPart(location, data.getHero().getBagEquipmentParts()));
		}
		else {
			data.selectEquipment(null);
		}
		if (!data.hasASelectedEquipmentEquip()){
			data.setSelectedEquipmentEquip(view.checkEquipmentsEquip(location, data.getHero().getEquipEquipmentParts()));
		}
		else {
			data.setSelectedEquipmentEquip(null);
		}
	}

	private void doBobActionAndDraw(ApplicationContext context) {
		Hero bob = data.getHero();
		if (timeData.elapsedBob() >= TimeData.BOB_DELAY) {
			data.moveHero();
			view.draw(context, data, timeData);
			timeData.resetElapsedBob();
		}
	}

	private void doEventActionAndDraw(ApplicationContext context) {
		Event event = context.pollOrWaitEvent(USER_ACTION_DELAY);
		if (event == null) { // no event
			return;
		}

		switch (event.getAction()) {
		case KEY_PRESSED:
			doKeyAction(context, event);
			break;
		case POINTER_DOWN:
			if (timeData.stopped()) {
				doMouseAction(context, event);
			}
			break;
		}
		view.draw(context, data, timeData);
	}

	private void simpleGame(ApplicationContext context) { // le type de méthode que prend run() en paramètre
		printScreenInfo(context);

		data.fillMatrix();
		view.draw(context, data, timeData);
		boolean newDay = true;
		while (true) {
			if (timeData.newDay() && newDay) { // gere les effets appliqué chaque jours
				data.newDay();
				newDay = false;
			} else if (!timeData.newDay()) {
				newDay = true;
			}
			if (timeData.stopped()) {
				data.updateData();
			}
			doBobActionAndDraw(context);
			doEventActionAndDraw(context);
		}
	}

	public static void main(String[] args) {
		// pour changer de jeu, remplacer stupidGame par le nom de la méthode de jeu
		// (elle doit avoir extactement la même en-tête).

		LoopHeroMultiController controller = new LoopHeroMultiController();

		Application.run(Color.LIGHT_GRAY, controller::simpleGame); // attention, utilisation d'une lambda.
		System.out.println("ne doit pas s'afficher");
	}

}
