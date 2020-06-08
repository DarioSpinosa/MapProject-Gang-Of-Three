package handlers.events;

import entities.GenericObject;
import entities.characters.Protagonist;
/**
 * <Control> Responsabilità: Gestisce le condizioni di terminazione di un evento specifico
 * e i cambiamenti che comporterà all'interno del gioco, quali aggiunta e rimozione di oggetti
 * nell'inventario, aggiornamento dei dialoghi degli npc e modifica della loro posizione.
 */
public class DibDoorEventHandler extends GenericEventHandler {

	public DibDoorEventHandler(Event event){
		super(event);
	}
	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		GenericObject enigma = event.getEnigma();

		if(protagonist.isInInventory(obj) && obj.equals(enigma)) {
			completed = true;
			protagonist.getInventory().removeFromContainer((event.getEnigma()));
			event.getEventRoom().getRight().setAccessible(true);
       }
		return completed;
	}
}
