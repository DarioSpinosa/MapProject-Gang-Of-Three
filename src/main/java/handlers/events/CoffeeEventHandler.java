package handlers.events;

import entities.GenericObject;
import entities.characters.Npc;
import entities.characters.Protagonist;
import entities.enigmas.Coffee;
import resources.Dialogs;
/**
 * <Control> Responsabilità: Gestisce le condizioni di terminazione di un evento specifico
 * e i cambiamenti che comporterà all'interno del gioco, quali aggiunta e rimozione di oggetti
 * nell'inventario, aggiornamento dei dialoghi degli npc e modifica della loro posizione.
 *
 */
public class CoffeeEventHandler extends GenericEventHandler {

	public CoffeeEventHandler(Event event){
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {

		if(!completed && ((Coffee)event.getEnigma()).isCompleted()){
			setCompleted();
			((Npc)event.getEventRoom().getCharacters().get(0)).setDialogue(Dialogs.CANNAVACCIUOLO_B);
			protagonist.getInventory().addToContainer(event.getReward());

		}
		return completed;
	}
}
