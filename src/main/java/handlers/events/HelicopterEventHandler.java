package handlers.events;

import entities.GenericObject;
import entities.characters.Protagonist;
/**
 * <Control> Responsabilità: Gestisce le condizioni di terminazione di un evento specifico
 * e i cambiamenti che comporterà all'interno del gioco, quali aggiunta e rimozione di oggetti
 * nell'inventario, aggiornamento dei dialoghi degli npc e modifica della loro posizione.
 */
public class HelicopterEventHandler extends GenericEventHandler {

	public  HelicopterEventHandler(Event event, GenericEventHandler condition){
		super(event, condition);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {

		if(protagonist.isInInventory(obj) && obj.equals(event.getEnigma())){
			completed = true;
		}

		return completed;

	}


}
