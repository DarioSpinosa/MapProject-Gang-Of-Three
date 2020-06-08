/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.Command;
import entities.CommandType;
import entities.Game;
import entities.GenericObject;
import entities.GenericObjectContainer;
import entities.Prepositions;
import entities.Room;
import entities.characters.Character;
import entities.characters.Npc;
import entities.characters.Protagonist;
import entities.enigmas.Coffee;
import entities.enigmas.Panel;
import handlers.events.CoffeeEventHandler;
import handlers.events.DibDoorEventHandler;
import handlers.events.GenericEventHandler;
import handlers.events.HelicopterEventHandler;
import handlers.events.MagazineEventHandler;
import handlers.events.PackageEventHandler;
import handlers.events.PanelEventHandler;
import handlers.events.PhysicsDoorEventHandler;
import handlers.events.PropulsorEventHandler;
import parser.ParserOutput;
import resources.Descriptions;
import resources.Dialogs;
import resources.Events;
import resources.Names;
import resources.Places;

/**
 * <Control> Responsabilità: gestisce i comandi del gioco, ricevuti in input dal giocatore.
 * Contiene la logica relativa ai cambiamenti che avvengono all'interno del gioco in base
 * al comando inserito. Conosce la posizione corrente del giocatore, il suo inventario, la mappa di gioco, i
 * personaggi, gli oggetti di gioco e lo stato della partita.
 */
public class ActionsHandler extends ActionsHandlerEssentials {

	public ActionsHandler(Game game, MessagesEssentials printer, Prepositions prepositions) {
		super(game, printer, prepositions);
		Command nord = new Command(CommandType.NORD, "nord");
		nord.setAlias(new String[] { "n", "sopra" });
		commands.add(nord);
		Command ovest = new Command(CommandType.OVEST, "ovest");
		ovest.setAlias(new String[] { "o", "sinistra" });
		commands.add(ovest);
		Command sud = new Command(CommandType.SUD, "sud");
		sud.setAlias(new String[] { "s", "sotto" });
		commands.add(sud);
		Command est = new Command(CommandType.EST, "est");
		est.setAlias(new String[] { "e", "destra" });
		commands.add(est);
		Command inventario = new Command(CommandType.INVENTARIO, "inventario");
		inventario.setAlias(new String[] { "oggetti", "zaino", "roba", "borsello" });
		commands.add(inventario);
		Command guarda = new Command(CommandType.GUARDA, "guarda");
		guarda.setAlias(new String[] { "osserva", "analizza", "ispeziona", "scruta", "esamina", "controlla" });
		commands.add(guarda);
		Command prendi = new Command(CommandType.PRENDI, "prendi");
		prendi.setAlias(new String[] { "afferra", "piglia" });
		commands.add(prendi);
		Command usa = new Command(CommandType.USA, "usa");
		usa.setAlias(new String[] { "utilizza" });
		commands.add(usa);
		Command lascia = new Command(CommandType.LASCIA, "lascia");
		lascia.setAlias(new String[] { "poggia", "posa", "butta" });
		commands.add(lascia);
		Command apri = new Command(CommandType.APRI, "apri");
		apri.setAlias(new String[] {});
		commands.add(apri);
		Command chiudi = new Command(CommandType.CHIUDI, "chiudi");
		commands.add(chiudi);
		Command combina = new Command(CommandType.COMBINA, "combina");
		combina.setAlias(new String[] { "unisci" });
		commands.add(combina);
		Command parla = new Command(CommandType.PARLA, "parla");
		commands.add(parla);
		Command metti = new Command(CommandType.METTI, "metti");
		metti.setAlias(new String[] { "versa", "inserisci" });
		commands.add(metti);
		Command abbassa = new Command(CommandType.ABBASSA, "abbassa");
		abbassa.setAlias(new String[] { "tira" });
		commands.add(abbassa);
		Command alza = new Command(CommandType.ALZA, "alza");
		alza.setAlias(new String[] { "spingi" });
		commands.add(alza);
		Command dai = new Command(CommandType.DAI, "dai");
		dai.setAlias(new String[] { "consegna", "porgi" });
		commands.add(dai);
	}

	@Override
	public void processAction(ParserOutput action) {
		Protagonist protagonist = (Protagonist) game.getProtagonist();
		Room currentRoom = game.getCurrentRoom();
		ArrayList<GenericObject> roomObjects = currentRoom.getRoomObjects().getContainer();
		GenericObject firstObject = action.getFirstObject();
		GenericObject secondObject = action.getSecondObject();
		GenericEventHandler eventHandler = game.getCurrentRoom().getEventHandler();
		switch (action.getCommand().getCommandType()) {
		case NORD:
			moveNord();
			break;
		case SUD:
			moveSud();
			break;
		case EST:
			moveEst();
			break;
		case OVEST:
			moveOvest();
			break;
		case INVENTARIO:
			int i = 0;
			printer.printLine();
			for (GenericObject genericObject : protagonist.getInventory().getContainer()) {
				printer.printInventory(genericObject);
				i++;
			}
			for (int j = i; j < protagonist.getInventoryMaxSize(); j++) {
				printer.printMessage("- ");
			}
			printer.printLine();
			break;
		case PRENDI:

			if (protagonist.getActualInventorySize() == protagonist.getInventoryMaxSize()) {
				printer.fullInventoryMessage();
				break;
			}

			if (firstObject == null && secondObject == null) {
				printer.notUnderstoodMessage();
				break;
			}

			if (!firstObject.isTakeable()) {
				printer.notTakeableObjectMessage(firstObject);
				break;
			}

			if (firstObject != null && secondObject == null)
				takeItem(firstObject, protagonist);

			else if (firstObject != null && secondObject != null) {

				if (!firstObject.isTakeable()) {
					printer.objectNotInRoomMessage();
					break;
				}

				if (secondObject instanceof GenericObjectContainer && prepositions
						.isGoodCombination(action.getPreposition(), action.getCommand().getCommandType())) {
					dropOrTakeItem(firstObject, (GenericObjectContainer) secondObject, protagonist, false);

				} else {
					printer.objectNotInRoomMessage();
				}
			}

			break;

		case DAI:

			if (firstObject == null) {
				printer.notUnderstoodMessage();
				return;
			}

			if (eventHandler == null) {
				printer.cannotGiveObjectMessage();
				return;
			}

			if (eventHandler.isCompleted()) {

				printer.cannotGiveObjectMessage();
				return;
			}

			boolean completed = eventHandler.endEvent(protagonist, firstObject);
			dialogsEventItems(eventHandler, completed);

			break;

		case LASCIA:

			if (firstObject == null && secondObject == null) {
				printer.notUnderstoodMessage();
				break;
			}

			if (firstObject != null && secondObject == null) {

				if (protagonist.isInInventory(firstObject)) {
					game.getCurrentRoom().addObject(firstObject);
					protagonist.removeObject(firstObject);
					printer.printLeftObject(firstObject);
				} else
					printer.objectNotInInventoryMessage();

			} else if (firstObject != null && secondObject != null && secondObject instanceof GenericObjectContainer
					&& (prepositions.isGoodCombination(action.getPreposition(), action.getCommand().getCommandType())
							|| action.getPreposition() == null)) {

				if (((GenericObjectContainer) secondObject).getContainer()
						.size() == ((GenericObjectContainer) secondObject).getMaxSize()) {
					printer.objectContainerIsFull(secondObject);
					break;
				}

				if (protagonist.isInInventory(firstObject))
					dropOrTakeItem(firstObject, (GenericObjectContainer) secondObject, protagonist, true);
				else
					printer.objectNotInInventoryMessage();
			} else
				printer.notUnderstoodMessage();

			break;

		case APRI:
			if (action.getPreposition() == null
					|| prepositions.isGoodCombination(action.getPreposition(), action.getCommand().getCommandType())) {
				openOrCloseitem(firstObject, secondObject, protagonist, action.getPreposition(), true);
			}
			break;
		case CHIUDI:
			if (action.getPreposition() == null
					|| prepositions.isGoodCombination(action.getPreposition(), action.getCommand().getCommandType())) {
				openOrCloseitem(firstObject, secondObject, protagonist, action.getPreposition(), false);
			}
			break;
		case GUARDA:

			if (firstObject != null && secondObject == null)
				lookItem(firstObject, protagonist, roomObjects);
			else if (firstObject == null && secondObject == null)
				lookRoom();
			break;

		case COMBINA:
			if (firstObject != null && secondObject != null && (action.getPreposition() == null
					|| prepositions.isGoodCombination(action.getPreposition(), action.getCommand().getCommandType()))) {

				if (protagonist.isInInventory(firstObject) && protagonist.isInInventory(secondObject)) {
					GenericObject oggettoCombinato = game.getCombinations().getResultantObject(firstObject,
							secondObject);

					if (oggettoCombinato != null) {
						protagonist.removeObject(firstObject);
						protagonist.removeObject(secondObject);
						protagonist.addObject(oggettoCombinato);
						printer.successfulCombinationMessage(firstObject, secondObject, oggettoCombinato);
					} else
						printer.unavaiableCombinationMessage(firstObject, secondObject);

				} else {
					if (!protagonist.isInInventory(firstObject)) {
						printer.specificObjectNotInInventoryMessage(firstObject);
					}
					if (!protagonist.isInInventory(secondObject))
						printer.specificObjectNotInInventoryMessage(secondObject);

				}
			} else
				printer.notUnderstoodMessage();

			break;
		case PARLA:
			if (action.getCharacter() != null && (action.getPreposition() == null
					|| prepositions.isGoodCombination(action.getPreposition(), action.getCommand().getCommandType()))
					&& action.getCharacter() instanceof Npc) {

				easterEggs(action);

				Npc npc = (Npc) game.getCurrentRoom().getCharacter(action.getCharacter());
				printer.printNpcDialogue(npc, npc.getDialogue());

			} else
				printer.notUnderstoodMessage();

			break;
		case USA:
			if (firstObject == null) {
				printer.notUnderstoodMessage();
				break;
			}

			if (currentRoom.getName().contains(Places.EXECUTIVE_NAME)
					&& firstObject.getObjectName().equals(Names.SERVER)) {
				JOptionPane.showMessageDialog(null, Descriptions.EMAIL, Descriptions.NEW_EMAIL,
						JOptionPane.PLAIN_MESSAGE);

			} else if (eventHandler != null)
				useEventItem(firstObject, protagonist, eventHandler);
			else
				printer.notUnderstoodMessage();

			break;

		case METTI:
			if (eventHandler instanceof CoffeeEventHandler && firstObject != null && secondObject != null) {
				putInCoffeMachine(firstObject, secondObject, protagonist);
			} else
				printer.unrecognisedCommandMessage();
			break;

		case ABBASSA:
			if (eventHandler != null && eventHandler instanceof PanelEventHandler && firstObject != null
					&& firstObject.getObjectName().equals(Names.LEVER) && action.getFirstAdjective() != null) {
				pullLever(eventHandler, action);
			} else
				printer.notUnderstoodMessage();

			break;

		case ALZA:
			if (eventHandler != null && eventHandler instanceof PanelEventHandler && firstObject != null
					&& firstObject.getObjectName().equals(Names.LEVER) && action.getFirstAdjective() != null) {
				pushLever(eventHandler, action);
			} else
				printer.notUnderstoodMessage();
		}

	}

	@Override
	public void moveNord() {
		if (game.getCurrentRoom().getUp() != null) {
			if (game.getCurrentRoom().getUp().getAccessible()) {
				game.setCurrentRoom(game.getCurrentRoom().getUp());
				printer.printRoom(game.getCurrentRoom().getName(), game.getCurrentRoom().getDescription());
				if (game.getCurrentRoom().getEventHandler() != null
						&& !game.getCurrentRoom().getEventHandler().isStarted())
					printer.printEventDescription(game.getCurrentRoom().getEventHandler().startEvent());
			} else
				printer.closedRoomMessage();
		} else
			printer.inexistentRoomMessage();

	}

	@Override
	public void moveSud() {
		if (game.getCurrentRoom().getDown() != null) {
			if (game.getCurrentRoom().getDown().getAccessible()) {
				game.setCurrentRoom(game.getCurrentRoom().getDown());
				printer.printRoom(game.getCurrentRoom().getName(), game.getCurrentRoom().getDescription());
				if (game.getCurrentRoom().getEventHandler() != null
						&& !game.getCurrentRoom().getEventHandler().isStarted())
					printer.printEventDescription(game.getCurrentRoom().getEventHandler().startEvent());
			} else
				printer.closedRoomMessage();
		} else
			printer.inexistentRoomMessage();

	}

	@Override
	public void moveEst() {
		if (game.getCurrentRoom().getRight() != null) {
			if (game.getCurrentRoom().getRight().getAccessible()) {
				game.setCurrentRoom(game.getCurrentRoom().getRight());
				printer.printRoom(game.getCurrentRoom().getName(), game.getCurrentRoom().getDescription());
				if (game.getCurrentRoom().getEventHandler() != null
						&& !game.getCurrentRoom().getEventHandler().isStarted())
					printer.printEventDescription(game.getCurrentRoom().getEventHandler().startEvent());
			} else {
				printer.closedRoomMessage();

			}
		} else
			printer.inexistentRoomMessage();

	}

	@Override
	public void moveOvest() {
		if (game.getCurrentRoom().getLeft() != null) {
			if (game.getCurrentRoom().getLeft().getAccessible()) {
				game.setCurrentRoom(game.getCurrentRoom().getLeft());
				printer.printRoom(game.getCurrentRoom().getName(), game.getCurrentRoom().getDescription());
				if (game.getCurrentRoom().getEventHandler() != null
						&& !game.getCurrentRoom().getEventHandler().isStarted())
					printer.printEventDescription(game.getCurrentRoom().getEventHandler().startEvent());
			} else
				printer.closedRoomMessage();
		} else
			printer.inexistentRoomMessage();

	}

	private void pullLever(GenericEventHandler eventHandler, ParserOutput action) {

		Panel lowerPanel = ((Panel) eventHandler.getEvent().getEnigma());

		switch (action.getFirstAdjective()) {
		case Names.RED:
			if (!lowerPanel.getLeversState(0))
				lowerPanel.switchFirstToggle();
			else
				printer.alreadyLoweredLeverMessage();
			break;

		case Names.YELLOW:
			if (!lowerPanel.getLeversState(1))
				lowerPanel.switchSecondToggle();
			else
				printer.alreadyLoweredLeverMessage();
			break;

		case Names.GREEN:
			if (!lowerPanel.getLeversState(2))
				lowerPanel.switchThirdToggle();
			else
				printer.alreadyLoweredLeverMessage();
			break;

		case Names.BLUE:
			if (!lowerPanel.getLeversState(3))
				lowerPanel.switchFourthToggle();
			else
				printer.alreadyLoweredLeverMessage();
			break;

		case Names.BLACK:
			if (!lowerPanel.getLeversState(4))
				lowerPanel.switchFifthToggle();
			else
				printer.alreadyLoweredLeverMessage();
		}
		printer.printMessage(lowerPanel.showVoltage());

	}

	private void pushLever(GenericEventHandler eventHandler, ParserOutput action) {

		Panel raisePanel = ((Panel) eventHandler.getEvent().getEnigma());

		switch (action.getFirstAdjective()) {
		case Names.RED:
			if (raisePanel.getLeversState(0))
				raisePanel.switchFirstToggle();
			else
				printer.alreadyRaisedLeverMessage();
			break;

		case Names.YELLOW:
			if (raisePanel.getLeversState(1))
				raisePanel.switchSecondToggle();
			else
				printer.alreadyRaisedLeverMessage();
			break;

		case Names.GREEN:
			if (raisePanel.getLeversState(2))
				raisePanel.switchThirdToggle();
			else
				printer.alreadyRaisedLeverMessage();
			break;

		case Names.BLUE:
			if (raisePanel.getLeversState(3))
				raisePanel.switchFourthToggle();
			else
				printer.alreadyRaisedLeverMessage();
			break;

		case Names.BLACK:
			if (raisePanel.getLeversState(4))
				raisePanel.switchFifthToggle();
			else
				printer.alreadyRaisedLeverMessage();
		}
		printer.printMessage(raisePanel.showVoltage());

	}

	private void useEventItem(GenericObject firstObject, Protagonist protagonist, GenericEventHandler eventHandler) {

		if (eventHandler instanceof CoffeeEventHandler) {
			if (firstObject.equals(eventHandler.getEvent().getEnigma())) {
				if (protagonist.getActualInventorySize() == protagonist.getInventoryMaxSize()) {
					printer.cantUseCoffeMachine();
				} else {
					printer.printMessage(((Coffee) (game.getCurrentRoom().getRoomObject(firstObject))).switchOn());
					eventHandler.endEvent(protagonist, firstObject);
				}
			}

		} else if (eventHandler instanceof PanelEventHandler) {
			if (firstObject.equals(eventHandler.getEvent().getEnigma())) {
				printer.printMessage(((Panel) game.getCurrentRoom().getRoomObject(firstObject)).switchOn());
				eventHandler.endEvent(protagonist, firstObject);
			}

		} else if (eventHandler instanceof PhysicsDoorEventHandler || eventHandler instanceof DibDoorEventHandler) {

			if (eventHandler.isCompleted()) {
				printer.notUnderstoodMessage();
				return;
			}

			if (eventHandler.endEvent(protagonist, firstObject))
				printer.printUsedObject(firstObject.getName().getName());
			else
				printer.wrongOpeningToolMessage();
		} else
			printer.notUnderstoodMessage();
	}

	private void easterEggs(ParserOutput action) {
		if (((Npc) action.getCharacter()).getName().getName().equals(Names.MORGAN_NAME)) {
			if (((Npc) action.getCharacter()).getCurrentDialogue().equals("Che succede?")) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(Events.EASTER_EGG));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			} else if (((Npc) action.getCharacter()).getCurrentDialogue().equals("Bugo?\nBugo 'ndo vai?")) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(Events.EASTER_EGG_2));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void lookItem(GenericObject firstObject, Protagonist protagonist, ArrayList<GenericObject> roomObjects) {

		boolean lookFound = false;

		if (roomObjects.contains(firstObject) || protagonist.isInInventory(firstObject)) {
			printer.printObjectDescription(firstObject);
			if (firstObject instanceof GenericObjectContainer && ((GenericObjectContainer) firstObject).isOpened()) {
				printer.printObjectInside(((GenericObjectContainer) firstObject));
			}
			lookFound = true;
		} else {
			for (int i = 0; i < roomObjects.size(); i++) {
				if (roomObjects.get(i) instanceof GenericObjectContainer) {
					if (((GenericObjectContainer) (roomObjects.get(i))).contains(firstObject)
							&& ((GenericObjectContainer) (roomObjects.get(i))).isOpened()) {
						printer.printObjectDescription(firstObject);
						lookFound = true;
					}
				}
			}
		}

		if (lookFound == false) {
			printer.objectNotFoundMessage();
		}

	}

	private void lookRoom() {

		ArrayList<GenericObject> loot = game.getCurrentRoom().getRoomObjects().getContainer();
		if (!loot.isEmpty()) {
			printer.aroundYouMessage();
			for (GenericObject genericObject : loot)
				printer.objectWithDescriptionMessage(genericObject);

		} else {
			printer.nobodyHere();
		}

		if (!game.getCurrentRoom().getCharacters().isEmpty()) {
			printer.aroundYouNpc();
			for (Character character : game.getCurrentRoom().getCharacters())
				printer.printMessage(character.getName().getName());

		} else {
			printer.noObjectsHere();
		}
	}

	private void takeItem(GenericObject firstObject, Protagonist protagonist) {

		boolean takeFound = false;

		if (game.getCurrentRoom().isInRoom(firstObject)) {
			game.getCurrentRoom().removeObject(firstObject);
			protagonist.addObject(firstObject);
			printer.printTakenObject(firstObject);
			takeFound = true;
		} else {
			for (int i = 0; i < currentObjects.size(); i++) {
				if (currentObjects.get(i) instanceof GenericObjectContainer) {
					if (((GenericObjectContainer) (currentObjects.get(i))).contains(firstObject)
							&& ((GenericObjectContainer) (currentObjects.get(i))).isOpened()) {
						((GenericObjectContainer) (currentObjects.get(i))).removeFromContainer(firstObject);
						protagonist.addObject(firstObject);
						printer.printTakenObject(firstObject);
						takeFound = true;
					}
				}
			}
		}

		if (!takeFound) {
			printer.objectNotInRoomMessage();
		}

	}

	private void dropOrTakeItem(GenericObject firstObject, GenericObjectContainer secondObject, Protagonist protagonist,
			boolean drop) {
		if (!(game.getCurrentRoom().isInRoom(secondObject)) && !(protagonist.isInInventory(secondObject))) {
			printer.objectNotFoundMessage();
			return;
		}
		if (!secondObject.isOpened()) {
			printer.closedObjectMessage(secondObject);
			return;
		}

		if (drop) {
			protagonist.removeObject(firstObject);
			secondObject.addToContainer(firstObject);
			printer.printObjectLeftIn(firstObject, secondObject);
		} else {
			if (secondObject.contains(firstObject)) {
				secondObject.removeFromContainer(firstObject);
				protagonist.addObject(firstObject);
				printer.printTakenObjectFrom(firstObject, secondObject);
			} else {
				printer.notAvaiableObjectMessage();
			}
		}
	}

	private void dialogsEventItems(GenericEventHandler eventHandler, boolean completed) {

		if (eventHandler instanceof PackageEventHandler) {

			if (completed)
				printer.printMessage("\n" + Dialogs.BAKER_C);
			else
				printer.printMessage("\n" + Dialogs.BAKER_D);

		} else if (eventHandler instanceof MagazineEventHandler) {

			if (completed)
				printer.printMessage("\n" + Dialogs.JANITOR_D);
			else
				printer.printMessage("\n" + Dialogs.JANITOR_C);

		} else if (eventHandler instanceof PropulsorEventHandler) {

			if (completed)
				printer.printMessage("\n" + Dialogs.VOLPE_C);
			else
				printer.printMessage("\n" + Dialogs.VOLPE_D);

		} else if (eventHandler.isStarted() && eventHandler instanceof HelicopterEventHandler) {
			if (completed) {
				printer.printMessage("\n" + Dialogs.PILOT_C);
				gameCompleted = true;
			} else
				printer.printMessage("\n" + Dialogs.PILOT_D);
		}

	}

	private void openOrCloseitem(GenericObject firstObject, GenericObject secondObject, Protagonist protagonist,
			String preposition, boolean open) {

		if (firstObject.getName().getName().equals(Names.DOOR)
				&& (game.getCurrentRoom().getEventHandler() instanceof DibDoorEventHandler
						|| game.getCurrentRoom().getEventHandler() instanceof PhysicsDoorEventHandler)) {

			if (game.getCurrentRoom().getEventHandler().isCompleted()) {
				printer.notUnderstoodMessage();
				return;
			}

			if (game.getCurrentRoom().getEventHandler().endEvent(protagonist, protagonist.getObject(secondObject)))
				printer.printOpenedObject(firstObject);
			else
				printer.wrongOpeningToolMessage();

		} else if (firstObject != null && secondObject == null) {

			if (open) {
				openContainer(firstObject, protagonist);
			} else {
				closeContainer(firstObject, protagonist);
			}

		} else if (firstObject != null && secondObject != null) {

			if (!(firstObject instanceof GenericObjectContainer)) {
				printer.notOpenableObjectMessage();
				return;
			}

			if ((firstObject.getName().getName().equals(Names.PACKAGE)
					&& secondObject.getName().getName().equals(Names.CUTTER))
					|| (firstObject.getName().getName().equals(Names.CAR)
							&& secondObject.getName().getName().equals(Names.LOCKPICK))) {
				openContainerBlocked(firstObject, secondObject, protagonist);

			} else {
				printer.wrongOpeningToolMessage();
			}

		} else {
			printer.notUnderstoodMessage();
		}

	}

	private void openContainerBlocked(GenericObject firstObject, GenericObject secondObject, Protagonist protagonist) {

		GenericObjectContainer containerObject = (GenericObjectContainer) firstObject;
		if ((game.getCurrentRoom().isInRoom(containerObject) || protagonist.isInInventory(containerObject))) {

			if (!protagonist.isInInventory(secondObject)) {

				printer.specificObjectNotInInventoryMessage(secondObject);

				return;
			}

			protagonist.removeObject(secondObject);
			containerObject.setBlocked(false);
			printer.printOpenedObject(firstObject);
			containerObject.open();
		}
	}

	private void putInCoffeMachine(GenericObject firstObject, GenericObject secondObject, Protagonist protagonist) {
		if (firstObject.getObjectName().equals(Names.COFFE) && secondObject.getObjectName().equals(Names.COFFE_MACHINE)
				&& protagonist.isInInventory(firstObject)) {
			printer.printMessage(((Coffee) (game.getCurrentRoom().getRoomObject(secondObject))).addCoffee());
			protagonist.getInventory().removeFromContainer(firstObject);

		} else if (firstObject.getObjectName().equals(Names.WATER)
				&& secondObject.getObjectName().equals(Names.COFFE_MACHINE) && protagonist.isInInventory(firstObject)) {
			printer.printMessage(((Coffee) (game.getCurrentRoom().getRoomObject(secondObject))).addWater());
			protagonist.getInventory().removeFromContainer(firstObject);
		} else
			printer.notUnderstoodMessage();
	}

	private void openContainer(GenericObject firstObject, Protagonist protagonist) {
		if (!(firstObject instanceof GenericObjectContainer)) {
			printer.notOpenableObjectMessage();
			return;
		}
		GenericObjectContainer containerObject = (GenericObjectContainer) firstObject;
		if (!game.getCurrentRoom().isInRoom(containerObject) && !protagonist.isInInventory(containerObject)) {
			printer.objectNotFoundMessage();
			return;
		}
		if (containerObject.isBlocked()) {
			printer.blockedObjectMessage();
			return;
		}
		if (containerObject.isOpened()) {
			printer.alreadyOpenedObjectMessage(firstObject);
			return;
		}
		containerObject.open();
		printer.printOpenedObject(firstObject);
	}

	private void closeContainer(GenericObject firstObject, Protagonist protagonist) {
		if (!(firstObject instanceof GenericObjectContainer)) {
			printer.notClosableObjectMessage();
			return;
		}
		GenericObjectContainer containerObject = (GenericObjectContainer) firstObject;
		if (!game.getCurrentRoom().isInRoom(containerObject) && !protagonist.isInInventory(containerObject)) {
			printer.objectNotFoundMessage();
			return;
		}
		if (!containerObject.isOpened()) {
			printer.alreadyClosedObjectMessage(firstObject);
			return;
		}
		containerObject.close();
		printer.printClosedObject(firstObject);
	}

}
