/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import Main.AdventureGUI;

/**
 *
 * @author Elio
 */
public abstract class MessagesHandlerEssentials {
	protected AdventureGUI gui;

	public MessagesHandlerEssentials(AdventureGUI gui) {
		this.gui = gui;
	}

	public void printLine() {
		gui.stampaMessaggio("-----------------------------------------------");
	}

	public void printInventory(GenericObject object) {
		if (object.getAdjectives() == null) {
			gui.stampaMessaggio("- " + object);
		} else {
			gui.stampaMessaggio("- " + object + " " + object.getAdjectives().toArray()[0]);
		}
	}

	public abstract void beginningOfTheGameMessage(String name, String description);

	public abstract void aroundYouMessage();

	public abstract void printMessage(String messaggio);

	public abstract void inexistentRoomMessage();

	public abstract void closedRoomMessage();

	public abstract void unrecognisedCommandMessage();

	public abstract void printRoom(String name, String description);

	public abstract void objectNotInInventoryMessage();

	public abstract void fullInventoryMessage();

	public abstract void objectNotInRoomMessage();

	public abstract void notOpenableObjectMessage();

	public abstract void notClosableObjectMessage();

	public abstract void objectNotFoundMessage();

	public abstract void printObjectDescription(GenericObject object);

	public abstract void printTakenObject(GenericObject object);

	public abstract void printLeftObject(GenericObject object);

	public abstract void printOpenedObject(GenericObject object);

	public abstract void printClosedObject(GenericObject object);

	public abstract void notUnderstoodMessage();

	public abstract void printTakenObjectFrom(GenericObject firstObject, GenericObject secondObject);

	public abstract void notAvaiableObjectMessage();

	public abstract void closedObjectMessage(GenericObject object);

	public abstract void printObjectLeftIn(GenericObject firstObject, GenericObject secondObject);

	public abstract void unavaiableCombinationMessage(GenericObject firstObject, GenericObject secondObject);

	public abstract void successfulCombinationMessage(GenericObject firstObject, GenericObject secondObject, GenericObject resultantObject);

	public abstract void specificObjectNotInInventoryMessage(GenericObject object);

	public abstract void objectsNotFoundMessage();

	public abstract void alreadyOpenedObjectMessage(GenericObject object);

	public abstract void alreadyClosedObjectMessage(GenericObject object);

	public abstract void notTakeableObjectMessage(GenericObject object);

	public abstract void objectWithDescriptionMessage(GenericObject object);

	public abstract void alreadyLoweredLeverMessage();

	public abstract void alreadyRaisedLeverMessage();

	public abstract void aroundYouNpc();
}
