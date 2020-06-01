/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import Entita.Game;
import Entita.Room;
import Entita.Characters.Character;
import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import General.Eventi.CarEventHandler;
import General.Eventi.CoffeEventHandler;
import General.Eventi.DibDoorEventHandler;
import General.Eventi.GenericEventHandler;
import General.Eventi.HelicopterEventHandler;
import General.Eventi.MagazineEventHandler;
import General.Eventi.PackageEventHandler;
import General.Eventi.PanelEventHandler;
import General.Eventi.PhysicsEventHandler;
import General.Eventi.PropulsorEventHandler;
import General.Eventi.Enigmi.Coffe;
import General.Eventi.Enigmi.Panel;
import Parser.ParserOutput;
import Resources.Dialogs;

/**
 *
 * @author Elio
 */
public class ActionsHandler extends ActionsHandlerEssentials {

	private final Set<String> pickPrepositions = new HashSet<>();
	private final Set<String> dropPrepositions = new HashSet<>();
	private final Set<String> combinePrepositions = new HashSet<>();
	private final Set<String> talkPrepositions = new HashSet<>();

	public ActionsHandler(Game game, MessagesHandlerEssentials printer) {
		super(game, printer);
		pickPrepositions.add("dal");
		pickPrepositions.add("da");
		pickPrepositions.add("dalla");
		dropPrepositions.add("nel");
		dropPrepositions.add("in");
		dropPrepositions.add("nella");
		combinePrepositions.add("con");
		combinePrepositions.add("e");
		talkPrepositions.add("con");
		talkPrepositions.add("all");
		talkPrepositions.add("a");
		talkPrepositions.add("ad");
		Command nord = new Command(CommandType.NORD, "nord");
		nord.setAlias(new String[] { "n", "Nord" });
		commands.add(nord);
		Command ovest = new Command(CommandType.OVEST, "ovest");
		ovest.setAlias(new String[] { "o", "Ovest" });
		commands.add(ovest);
		Command sud = new Command(CommandType.SUD, "sud");
		sud.setAlias(new String[] { "s", "Sud" });
		commands.add(sud);
		Command est = new Command(CommandType.EST, "est");
		est.setAlias(new String[] { "e", "Est" });
		commands.add(est);
		Command inventario = new Command(CommandType.INVENTARIO, "inventario");
		commands.add(inventario);
		Command guarda = new Command(CommandType.GUARDA, "guarda");
		guarda.setAlias(new String[] { "osserva", "analizza", "ispeziona" });
		commands.add(guarda);
		Command prendi = new Command(CommandType.PRENDI, "prendi");
		prendi.setAlias(new String[] { "afferra" });
		commands.add(prendi);
		Command usa = new Command(CommandType.USA, "usa");
		usa.setAlias(new String[] { "utilizza" });
		commands.add(usa);
		Command lascia = new Command(CommandType.LASCIA, "lascia");
		lascia.setAlias(new String[] { "poggia", "posa" });
		commands.add(lascia);
		Command apri = new Command(CommandType.APRI, "apri");
		apri.setAlias(new String[] {});
		commands.add(apri);
		Command chiudi = new Command(CommandType.CHIUDI, "chiudi");
		commands.add(chiudi);
		Command combina = new Command(CommandType.COMBINA, "combina");
		commands.add(combina);
		Command parla = new Command(CommandType.PARLA, "parla");
		commands.add(parla);
		Command metti = new Command(CommandType.METTI, "metti");
		metti.setAlias(new String[] { "versa", "inserisci"});
		commands.add(metti);
		Command abbassa = new Command(CommandType.ABBASSA, "abbassa");
		abbassa.setAlias(new String[] {"tira"});
		commands.add(abbassa);
		Command alza = new Command(CommandType.ALZA, "alza");
		alza.setAlias(new String[] {"spingi"});
		commands.add(alza);
		Command dai = new Command(CommandType.DAI, "dai");
		dai.setAlias(new String[] { "consegna" });
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
			boolean takeFound = false;
			if (protagonist.getActualInventorySize() > 6) {
				printer.fullInventoryMessage();
				break;
			}

			if (firstObject != null && secondObject == null) {
				if (!firstObject.isTakeable()) {
					printer.notTakeableObjectMessage(firstObject);
					break;
				}

				if (game.getCurrentRoom().isInRoom(firstObject)) {
					game.getCurrentRoom().removeObject(firstObject);
					protagonist.addObject(firstObject);
					printer.printTakenObject(firstObject);
					takeFound = true;
				} else {
					for (i = 0; i < currentObjects.size(); i++) {
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

			} else if (firstObject != null && secondObject != null) {
				if (!firstObject.isTakeable()) {
					printer.objectNotInRoomMessage();
					break;
				}
				if (secondObject instanceof GenericObjectContainer
						&& pickPrepositions.contains(action.getPreposition())) {
					lasciaOPrendiOggetto(firstObject, (GenericObjectContainer)secondObject, protagonist, false);
				} else {
					printer.objectNotInRoomMessage();
				}
			} else {
				printer.notUnderstoodMessage();
			}

			break;


		case DAI:
			if (firstObject != null) {

				if(eventHandler != null) {

					if(!eventHandler.isCompleted()) {

						if (eventHandler instanceof PackageEventHandler) {

							if (eventHandler.endEvent(protagonist, firstObject))
								printer.printMessage(Dialogs.BAKER_C);
							else
								printer.printMessage(Dialogs.BAKER_D);

						} else if (eventHandler instanceof MagazineEventHandler) {

							if (eventHandler.endEvent(protagonist, firstObject))
								printer.printMessage(Dialogs.JANITOR_EVENT);
							else
								printer.printMessage(Dialogs.JANITOR_C);
						} else if (eventHandler instanceof PropulsorEventHandler) {

							if (eventHandler.endEvent(protagonist, firstObject))
								printer.printMessage(Dialogs.VOLPE_C);
							else
								printer.printMessage(Dialogs.VOLPE_D);

						} else if (eventHandler.isStarted() && eventHandler instanceof HelicopterEventHandler) {

							if (eventHandler.endEvent(protagonist, firstObject)) {
								printer.printMessage(Dialogs.PILOT_C);
								gameCompleted = true;
							} else
								printer.printMessage(Dialogs.PILOT_D);
						}

					}else
						printer.notUnderstoodMessage();
				}else
					printer.cannotGiveObjectMessage();
			} else
				printer.notUnderstoodMessage();
			break;

		case LASCIA:
			if (firstObject != null && secondObject == null) {

				if (protagonist.isInInventory(firstObject)) {
					game.getCurrentRoom().addObject(firstObject);
					protagonist.removeObject(firstObject);
					printer.printLeftObject(firstObject);
				} else
					printer.objectNotInInventoryMessage();

			} else if (firstObject != null && secondObject != null && secondObject instanceof GenericObjectContainer
					&& dropPrepositions.contains(action.getPreposition())) {
				if (protagonist.isInInventory(firstObject))
					lasciaOPrendiOggetto(firstObject, (GenericObjectContainer)secondObject, protagonist, true);
				else
					printer.objectNotInInventoryMessage();
			} else
				printer.notUnderstoodMessage();

			break;
		case APRI:
			apriOChiudiOggetto(firstObject, secondObject, protagonist, action.getPreposition(), true);
			break;
		case CHIUDI:
			apriOChiudiOggetto(firstObject, secondObject, protagonist, action.getPreposition(), false);
			break;
		case GUARDA:
			boolean lookFound = false;

			if (firstObject != null && secondObject == null) {
				if (roomObjects.contains(firstObject) || protagonist.isInInventory(firstObject)) {
					printer.printObjectDescription(firstObject);
					if (firstObject instanceof GenericObjectContainer && ((GenericObjectContainer) firstObject).isOpened()) {
						printer.printMessage(((GenericObjectContainer) firstObject).getContainer().toString());
					}
					lookFound = true;
				} else {
					for (i = 0; i < roomObjects.size(); i++) {
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

			} else if (firstObject == null && secondObject == null) {
				ArrayList<GenericObject> loot = game.getCurrentRoom().getRoomObjects().getContainer();
				if (!loot.isEmpty()) {
					printer.aroundYouMessage();
					for (GenericObject genericObject : loot)
						printer.objectWithDescriptionMessage(genericObject);

				}

				if (!game.getCurrentRoom().getCharacters().isEmpty()) {
					printer.aroundYouNpc();
					for (Character character : game.getCurrentRoom().getCharacters())
						printer.printMessage(character.getName().getName());

				}
			}
			break;

		case COMBINA:
			if (firstObject != null && secondObject != null
			&& (action.getPreposition() == null || combinePrepositions.contains(action.getPreposition()))) {

				if (protagonist.isInInventory(firstObject) && protagonist.isInInventory(secondObject)) {
					GenericObject oggettoCombinato = Combinations.getCombination(firstObject, secondObject);

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
			if (action.getCharacter() != null
			&& (action.getPreposition() == null || talkPrepositions.contains(action.getPreposition()))
			&& action.getCharacter() instanceof Npc) {

				if (((Npc) action.getCharacter()).getCurrentDialogue().equals("Che succede?")) {
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.browse(new URI("https://www.youtube.com/watch?v=otbUSPQiet8"));
					} catch (IOException | URISyntaxException e) {
						e.printStackTrace();
					}
				}
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

			if(eventHandler == null && currentRoom.getName().contains("Executive")
					&& firstObject.getObjectName().equals("server")) {
				JOptionPane.showMessageDialog(null,
						"Devi scappare, sono gia' qui!\nNon ho tempo per i dettagli, abbandona la citta' al piu' presto!",
						"Nuova Email", JOptionPane.PLAIN_MESSAGE);

			} else if (eventHandler != null && !eventHandler.isCompleted()) {

				if (eventHandler instanceof CoffeEventHandler && firstObject.equals(eventHandler.getEvent().getEnigma())) {
					printer.printMessage(((Coffe) (firstObject)).switchOn());
					eventHandler.endEvent(protagonist, firstObject);

				} else if (eventHandler instanceof PanelEventHandler && firstObject.equals(eventHandler.getEvent().getEnigma())){
					printer.printMessage(((Panel) (firstObject)).switchOn());
					eventHandler.endEvent(protagonist, firstObject);

				} else if (eventHandler instanceof CarEventHandler) {

					if(eventHandler.endEvent(protagonist, firstObject))
						printer.printOpenedObject(firstObject);
					else
						printer.wrongOpeningToolMessage();

				} else if (eventHandler instanceof PhysicsEventHandler || eventHandler instanceof DibDoorEventHandler){

					if(eventHandler.endEvent(protagonist, firstObject))
						printer.printOpenedObject(firstObject);
					else
						printer.wrongOpeningToolMessage();
				}

			}else
				printer.notUnderstoodMessage();

			break;

		case METTI:
			if (eventHandler instanceof CoffeEventHandler && firstObject != null && secondObject != null) {

				if (firstObject.getObjectName().equals("caffe") && secondObject.getObjectName().equals("macchinetta")
						&& protagonist.isInInventory(firstObject)) {
					printer.printMessage(((Coffe) (secondObject)).addCoffee());
					protagonist.getInventory().removeFromContainer(firstObject);

				}else if (firstObject.getObjectName().equals("acqua") && secondObject.getObjectName().equals("macchinetta")
						&& protagonist.isInInventory(firstObject)) {
					printer.printMessage(((Coffe) (secondObject)).addWater());
					protagonist.getInventory().removeFromContainer(firstObject);
				}
				else
					printer.notUnderstoodMessage();
			} else
				printer.unrecognisedCommandMessage();
			break;

		case ABBASSA:
			if (eventHandler != null && eventHandler instanceof PanelEventHandler && firstObject != null
			&& firstObject.getObjectName().equals("leva") && action.getFirstAdjective() != null) {

				Panel lowerPanel = ((Panel) eventHandler.getEvent().getEnigma());

				switch (action.getFirstAdjective()) {
				case "rossa":
					if (!lowerPanel.getLeversState(0))
						lowerPanel.switchFirstToggle();
					else
						printer.alreadyLoweredLeverMessage();
					break;

				case "gialla":
					if (!lowerPanel.getLeversState(1))
						lowerPanel.switchSecondToggle();
					else
						printer.alreadyLoweredLeverMessage();
					break;

				case "verde":
					if (!lowerPanel.getLeversState(2))
						lowerPanel.switchThirdToggle();
					else
						printer.alreadyLoweredLeverMessage();
					break;

				case "blu":
					if (!lowerPanel.getLeversState(3))
						lowerPanel.switchFourthToggle();
					else
						printer.alreadyLoweredLeverMessage();
					break;

				case "nera":
					if (!lowerPanel.getLeversState(4))
						lowerPanel.switchFifthToggle();
					else
						printer.alreadyLoweredLeverMessage();
				}
				printer.printMessage(lowerPanel.showVoltage());

			} else
				printer.notUnderstoodMessage();

			break;

		case ALZA:
			if (eventHandler != null && eventHandler instanceof PanelEventHandler && firstObject != null
			&& firstObject.getObjectName().equals("leva") && action.getFirstAdjective() != null) {

				Panel raisePanel = ((Panel) eventHandler.getEvent().getEnigma());

				switch (action.getFirstAdjective()) {
				case "rossa":
					if (raisePanel.getLeversState(0))
						raisePanel.switchFirstToggle();
					else
						printer.alreadyRaisedLeverMessage();
					break;

				case "gialla":
					if (raisePanel.getLeversState(1))
						raisePanel.switchSecondToggle();
					else
						printer.alreadyRaisedLeverMessage();
					break;

				case "verde":
					if (raisePanel.getLeversState(2))
						raisePanel.switchThirdToggle();
					else
						printer.alreadyRaisedLeverMessage();
					break;

				case "blu":
					if (raisePanel.getLeversState(3))
						raisePanel.switchFourthToggle();
					else
						printer.alreadyRaisedLeverMessage();
					break;

				case "nera":
					if (raisePanel.getLeversState(4))
						raisePanel.switchFifthToggle();
					else
						printer.alreadyRaisedLeverMessage();
				}
				printer.printMessage(raisePanel.showVoltage());

			} else
				printer.notUnderstoodMessage();
		}

	}

	@Override
	public void moveNord() {
		if (game.getCurrentRoom().getUp() != null) {
			if (game.getCurrentRoom().getUp().getAccessible()) {
				game.setCurrentRoom(game.getCurrentRoom().getUp());
				printer.printPlaceName(game.getCurrentRoom().getName().toUpperCase());
				printer.printMessage(game.getCurrentRoom().getDescription());
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
				printer.printPlaceName(game.getCurrentRoom().getName().toUpperCase());
				printer.printMessage(game.getCurrentRoom().getDescription());
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
				printer.printPlaceName(game.getCurrentRoom().getName().toUpperCase());
				printer.printMessage(game.getCurrentRoom().getDescription());
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
				printer.printPlaceName(game.getCurrentRoom().getName().toUpperCase());
				printer.printMessage(game.getCurrentRoom().getDescription());
				if (game.getCurrentRoom().getEventHandler() != null
						&& !game.getCurrentRoom().getEventHandler().isStarted())
					printer.printEventDescription(game.getCurrentRoom().getEventHandler().startEvent());
			} else
				printer.closedRoomMessage();
		} else
			printer.inexistentRoomMessage();

	}

	private void lasciaOPrendiOggetto(GenericObject firstObject, GenericObjectContainer secondObject, Protagonist protagonist,
			boolean drop) {
		if (game.getCurrentRoom().isInRoom(secondObject)) {

			if (secondObject.isOpened()) {

				if (drop) {
					protagonist.removeObject(firstObject);
					secondObject.addToContainer(firstObject);
					printer.printObjectLeftIn(firstObject, secondObject);
				} else{
					if (secondObject.contains(firstObject)) {
						secondObject.removeFromContainer(firstObject);
						protagonist.addObject(firstObject);
						printer.printTakenObjectFrom(firstObject, secondObject);
					} else
						printer.notAvaiableObjectMessage();
				}

			} else
				printer.closedObjectMessage(secondObject);

		} else if (protagonist.isInInventory(secondObject)) {
			GenericObjectContainer containerObjectInventory = secondObject;

			if (containerObjectInventory.isOpened()) {

				if (drop) {
					protagonist.removeObject(firstObject);
					containerObjectInventory.addToContainer(firstObject);
					printer.printObjectLeftIn(firstObject, secondObject);
				} else {
					if (containerObjectInventory.contains(firstObject)) {
						containerObjectInventory.removeFromContainer(firstObject);
						protagonist.addObject(firstObject);
						printer.printTakenObjectFrom(firstObject, secondObject);
					} else
						printer.notAvaiableObjectMessage();
				}

			} else
				printer.closedObjectMessage(secondObject);

		} else
			printer.objectNotFoundMessage();

	}

	private void apriOChiudiOggetto(GenericObject firstObject, GenericObject secondObject, Protagonist protagonist,
			String preposition, boolean open) {
		if (firstObject != null && secondObject == null) {

			if (firstObject instanceof GenericObjectContainer) {
				GenericObjectContainer containerObject = (GenericObjectContainer) firstObject;

				if (game.getCurrentRoom().isInRoom(containerObject)) {

					if (open) {

						if (!containerObject.isBlocked()) {

							if (!containerObject.isOpened()) {
								containerObject.open();
								printer.printOpenedObject(firstObject);
							} else
								printer.alreadyOpenedObjectMessage(firstObject);

						} else
							printer.blockedObjectMessage();

					} else {

						if (containerObject.isOpened()) {
							containerObject.close();
							printer.printClosedObject(firstObject);
						} else
							printer.alreadyClosedObjectMessage(firstObject);
					}

				} else if (protagonist.isInInventory(containerObject)) {

					if (open) {

						if (!containerObject.isBlocked()) {

							if (!containerObject.isOpened()) {
								containerObject.open();
								printer.printOpenedObject(firstObject);
							} else
								printer.alreadyOpenedObjectMessage(firstObject);
						} else
							printer.blockedObjectMessage();

					} else {

						if (containerObject.isOpened()) {
							containerObject.close();
							printer.printClosedObject(firstObject);
						} else
							printer.alreadyClosedObjectMessage(firstObject);

					}
				} else
					printer.objectNotFoundMessage();

			} else {

				if (open) //TODO spostare sopra
					printer.notOpenableObjectMessage();
				else
					printer.notClosableObjectMessage();
			}

		} else if (firstObject != null && secondObject != null && (preposition == null || combinePrepositions.contains(preposition))) {

			if (firstObject instanceof GenericObjectContainer) {
				GenericObjectContainer containerObject = (GenericObjectContainer) firstObject;

				if ((game.getCurrentRoom().isInRoom(containerObject) || protagonist.isInInventory(containerObject))
						&& containerObject.getObjectName().equals("pacco")) {

					if (secondObject.getObjectName().equals("taglierino")) {
						protagonist.removeObject(secondObject);
						containerObject.setBlocked(false);
						printer.printOpenedObject(firstObject);
						containerObject.open();
					} else
						printer.objectCannotBeOpenedWithItemMessage();
				}else
					printer.objectNotFoundMessage();
			}else
				printer.notOpenableObjectMessage();

		}else
			printer.notUnderstoodMessage();

	}

}
