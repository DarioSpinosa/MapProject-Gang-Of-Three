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
public final class MessagesHandler extends MessagesHandlerEssentials {

	public MessagesHandler(AdventureGUI gui) {
		super(gui);
	}

	@Override
	public void beginningOfTheGameMessage(String name, String description) {
		gui.stampaMessaggio("Sei da solo sul bus piu' scassato del mondo.\nLa temperatura e' insopportabile e l'odore e' indescrivibile.\nRiesci a sentire ogni irregolarita' del manto stradale.\n"
						+ "CRASH! Il bus ha urtato qualcosa! Meglio scendere al piu' presto!\n" + "\nLUOGO: " + name
						+ "\n\n" + description);
	}

	@Override
	public void printMessage(String message) {
		gui.stampaMessaggio(message);
	}

	@Override
	public void inexistentRoomMessage() {
		int rand = (int) (Math.random() * 4);
		switch (rand) {
		case 0:
			gui.stampaMessaggio("A meno che tu non sia un fantasma, non puoi attraversare quella parete!");
			break;
		case 1:
			gui.stampaMessaggio("WHAT YOU DOING MARCELO?!");
			break;
		case 2:
			gui.stampaMessaggio("Senti una voce... \"Hey you, you are finally awake\"");
                        break;
		case 3:
			gui.stampaMessaggio("NULLPOINTEREXCEPTION!");
                        break;
		}
	}

	@Override
	public void alreadyLoweredLeverMessage() {
		gui.stampaMessaggio("La leva e' gia abbassata");
	}

	@Override
	public void alreadyRaisedLeverMessage() {
		gui.stampaMessaggio("La leva e' gia alzata");
	}


	@Override
	public void closedRoomMessage() {
		int rand = (int) (Math.random() * 2);
		switch (rand) {
		case 0:
			gui.stampaMessaggio("La porta non si apre, non posso proseguire!");
			break;
		case 1:
			gui.stampaMessaggio("Questa porta e' chiusa, forse dovrei fare altro prima di andare avanti!");
		}
	}

	@Override
	public void unrecognisedCommandMessage() {
		gui.stampaMessaggio("Comando non riconosciuto");
	}

	@Override
	public void printRoom(String nome, String descrizione) {
		gui.stampaMessaggio(nome);
		gui.stampaMessaggio(descrizione);
	}

	@Override
	public void objectNotInInventoryMessage() {
		gui.stampaMessaggio("Non hai questo oggetto nel tuo inventario");
	}

	@Override
	public void fullInventoryMessage() {
		gui.stampaMessaggio("Il tuo invetario e' pieno");
	}

	@Override
	public void objectNotInRoomMessage() {
		gui.stampaMessaggio("Non vedo questo oggetto da nessuna parte, hai bisogno di un oculista?");
	}

	@Override
	public void notOpenableObjectMessage() {
		gui.stampaMessaggio("Spiegami come vorresti aprire questo oggetto");
	}

	@Override
	public void notClosableObjectMessage() {
		gui.stampaMessaggio("Oggetto non chiudibile");
	}

	@Override
	public void objectNotFoundMessage() {
		gui.stampaMessaggio("Oggetto non presente ne' nella stanza ne' nell'inventario");
	}

	@Override
	public void printObjectDescription(GenericObject object) {
		gui.stampaMessaggio("-" + object.getDescription());
	}

	@Override
	public void printTakenObject(GenericObject object) {
		gui.stampaMessaggio("Hai preso: " + object);
	}

	@Override
	public void printLeftObject(GenericObject object) {
		gui.stampaMessaggio("Hai lasciato: " + object);
	}

	@Override
	public void printOpenedObject(GenericObject object) {
		gui.stampaMessaggio("Hai aperto: " + object);
	}

	@Override
	public void printClosedObject(GenericObject object) {
		gui.stampaMessaggio("Hai chiuso: " + object);
	}

	@Override
	public void notUnderstoodMessage() {
		gui.stampaMessaggio("Non ho capito le tue intenzioni, spiegati meglio!");
	}

	@Override
	public void printTakenObjectFrom(GenericObject firstObject, GenericObject secondObject) {
		gui.stampaMessaggio("Hai preso:  " + firstObject + " da " + secondObject);
	}

	@Override
	public void notAvaiableObjectMessage() {
		gui.stampaMessaggio("L'oggetto non contiene cio' che cerchi");
	}

	@Override
	public void closedObjectMessage(GenericObject object) {
		gui.stampaMessaggio("L'oggetto " + object + " e' chiuso");
	}

	@Override
	public void printObjectLeftIn(GenericObject firstObject, GenericObject secondObject) {
		gui.stampaMessaggio("Hai lasciato l'oggetto " + firstObject + " in " + secondObject);
	}

	@Override
	public void unavaiableCombinationMessage(GenericObject firstObject, GenericObject secondObject) {
		gui.stampaMessaggio("Non e' possibile combinare " + firstObject + " con " + secondObject);
	}

	@Override
	public void successfulCombinationMessage(GenericObject firstObject, GenericObject secondObject, GenericObject resultantObject) {
		gui.stampaMessaggio("Hai combinato " + firstObject + " con " + secondObject + " e ottenuto: " + resultantObject);
	}

	@Override
	public void specificObjectNotInInventoryMessage(GenericObject object) {
		gui.stampaMessaggio("L'oggetto " + object + " non e' presente nel tuo inventario");
	}

	@Override
	public void objectsNotFoundMessage() {
		gui.stampaMessaggio("Oggetti non presenti nel tuo inventario");
	}

	@Override
	public void alreadyOpenedObjectMessage(GenericObject object) {
		gui.stampaMessaggio("L'oggetto " + object + " e' gia'  aperto");
	}

	@Override
	public void alreadyClosedObjectMessage(GenericObject object) {
		gui.stampaMessaggio("L'oggetto " + object + " e' gia'  chiuso");
	}

	@Override
	public void aroundYouMessage() {
		gui.stampaMessaggio("Intorno a te vedi: ");
	}

	@Override
	public void notTakeableObjectMessage(GenericObject object) {
		gui.stampaMessaggio("Non puoi prendere " + object + "!");
	}

	@Override
	public void objectWithDescriptionMessage(GenericObject object) {
		gui.stampaMessaggio(object.getObjectName() + ": " + object.getDescription());
	}
}
