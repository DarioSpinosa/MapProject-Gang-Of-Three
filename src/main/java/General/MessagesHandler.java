/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import Entita.Characters.Npc;
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
		gui.printMessage("Sei da solo sul bus piu' scassato del mondo.\nLa temperatura e' insopportabile e l'odore e' indescrivibile.\nRiesci a sentire ogni irregolarita' del manto stradale.\n"
						+ "CRASH! Il bus ha urtato qualcosa! Meglio scendere al piu' presto!\n" + "\nLUOGO: " + name
						+ "\n\n" + description);
	}

	@Override
	public void printMessage(String message) {
		gui.printMessage(message);
	}

	@Override
	public void inexistentRoomMessage() {
		int rand = (int) (Math.random() * 4);
		switch (rand) {
		case 0:
			gui.printMessage("A meno che tu non sia un fantasma, non puoi attraversare quella parete!");
			break;
		case 1:
			gui.printMessage("WHAT YOU DOING MARCELO?!");
			break;
		case 2:
			gui.printMessage("Senti una voce... \"Hey you, you are finally awake\"");
                        break;
		case 3:
			gui.printMessage("NULLPOINTEREXCEPTION!");
                        break;
		}
	}

	@Override
	public void alreadyLoweredLeverMessage() {
		gui.printMessage("La leva e' gia abbassata");
	}

	@Override
	public void alreadyRaisedLeverMessage() {
		gui.printMessage("La leva e' gia alzata");
	}


	@Override
	public void closedRoomMessage() {
		int rand = (int) (Math.random() * 2);
		switch (rand) {
		case 0:
			gui.printMessage("La porta non si apre, non posso proseguire!");
			break;
		case 1:
			gui.printMessage("Questa porta e' chiusa, forse dovrei fare altro prima di andare avanti!");
		}
	}

	@Override
	public void unrecognisedCommandMessage() {
		gui.printMessage("Comando non riconosciuto");
	}

	@Override
	public void printRoom(String name, String descrizione) {
		 gui.printMessage(("\nLuogo:" + name).toUpperCase());
		gui.printMessage(descrizione);
	}

	@Override
	public void objectNotInInventoryMessage() {
		gui.printMessage("Non hai questo oggetto nel tuo inventario");
	}

	@Override
	public void fullInventoryMessage() {
		gui.printMessage("Il tuo invetario e' pieno");
	}

	@Override
	public void objectNotInRoomMessage() {
		gui.printMessage("Non vedo questo oggetto da nessuna parte, hai bisogno di un oculista?");
	}

	@Override
	public void notOpenableObjectMessage() {
		gui.printMessage("Spiegami come vorresti aprire questo oggetto");
	}

	@Override
	public void notClosableObjectMessage() {
		gui.printMessage("Oggetto non chiudibile");
	}

	@Override
	public void objectNotFoundMessage() {
		gui.printMessage("Oggetto non presente ne' nella stanza ne' nell'inventario");
	}

	@Override
	public void printObjectDescription(GenericObject object) {
		gui.printMessage("\n-" + object.getDescription());
	}

	@Override
	public void printTakenObject(GenericObject object) {
		gui.printMessage("\nHai preso: " + object);
	}

	@Override
	public void printLeftObject(GenericObject object) {
		gui.printMessage("\nHai lasciato: " + object);
	}

	@Override
	public void printOpenedObject(GenericObject object) {
		gui.printMessage("\nHai aperto: " + object);
	}

	@Override
	public void printClosedObject(GenericObject object) {
		gui.printMessage("\nHai chiuso: " + object);
	}

	@Override
	public void notUnderstoodMessage() {
		gui.printMessage("Non ho capito le tue intenzioni, spiegati meglio!");
	}

	@Override
	public void printTakenObjectFrom(GenericObject firstObject, GenericObject secondObject) {
		gui.printMessage("\nHai preso:  " + firstObject + " da " + secondObject);
	}

	@Override
	public void notAvaiableObjectMessage() {
		gui.printMessage("L'oggetto non contiene cio' che cerchi");
	}

	@Override
	public void closedObjectMessage(GenericObject object) {
		gui.printMessage("\nL'oggetto " + object + " e' chiuso");
	}

	@Override
	public void printObjectLeftIn(GenericObject firstObject, GenericObject secondObject) {
		gui.printMessage("\nHai lasciato l'oggetto " + firstObject + " in " + secondObject);
	}

	@Override
	public void unavaiableCombinationMessage(GenericObject firstObject, GenericObject secondObject) {
		gui.printMessage("Non e' possibile combinare " + firstObject + " con " + secondObject);
	}

	@Override
	public void successfulCombinationMessage(GenericObject firstObject, GenericObject secondObject, GenericObject resultantObject) {
		gui.printMessage("\nHai combinato " + firstObject + " con " + secondObject + " e ottenuto: " + resultantObject);
	}

	@Override
	public void specificObjectNotInInventoryMessage(GenericObject object) {
		gui.printMessage("L'oggetto " + object + " non e' presente nel tuo inventario");
	}

	@Override
	public void objectsNotFoundMessage() {
		gui.printMessage("Oggetti non presenti nel tuo inventario");
	}

	@Override
	public void alreadyOpenedObjectMessage(GenericObject object) {
		gui.printMessage("L'oggetto " + object + " e' gia'  aperto");
	}

	@Override
	public void alreadyClosedObjectMessage(GenericObject object) {
		gui.printMessage("L'oggetto " + object + " e' gia'  chiuso");
	}

	@Override
	public void aroundYouMessage() {
		gui.printMessage("\nIntorno a te vedi: ");
	}

	@Override
	public void aroundYouNpc() {
		gui.printMessage("\nNella stanza ci sono:");
	}

	@Override
	public void notTakeableObjectMessage(GenericObject object) {
		gui.printMessage("Non puoi prendere " + object + "!");
	}

	@Override
	public void objectWithDescriptionMessage(GenericObject object) {
		gui.printMessage("-" + object.getObjectName() + ": " + object.getDescription());
	}

        @Override
        public void blockedObjectMessage(){
            gui.printMessage("Oggetto chiuso, ti serve qualcosa per aprirlo");
        }

        @Override
        public void cannotGiveObjectMessage(){
            gui.printMessage("Non puoi dare questo oggetto a nessuno");
        }

        @Override
        public void wrongOpeningToolMessage(){
            gui.printMessage("Stai utilizzando l'oggetto errato o non hai l'oggetto giusto nell'inventario");
        }

        @Override
        public void printEventDescription(String description){
        	if(!description.equals(""))
                gui.printMessage("\n" + description);
        }

        @Override
        public void objectCannotBeOpenedWithItemMessage(){
            gui.printMessage("Non sembra sia possibile aprirlo con questo oggetto");
        }

        @Override
        public void printNpcDialogue(Npc npc, String dialogue){
            gui.printMessage("\n" + npc + ": " + dialogue);
        }

		@Override
		public void printObjectInside(GenericObjectContainer container) {
			for(GenericObject obj: container.getContainer())
				gui.printMessage(obj.getName().getName());
		}
}
