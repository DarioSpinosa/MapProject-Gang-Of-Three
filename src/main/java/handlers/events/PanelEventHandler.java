package handlers.events;

import entities.GenericObject;
import entities.characters.Protagonist;
import entities.enigmas.Panel;
import resources.Places;
/**
 * <Control> Responsabilità: Gestisce le condizioni di terminazione di un evento specifico
 * e i cambiamenti che comporterà all'interno del gioco, quali aggiunta e rimozione di oggetti
 * nell'inventario, aggiornamento dei dialoghi degli npc e modifica della loro posizione.
 */
public class PanelEventHandler extends GenericEventHandler{

	public  PanelEventHandler(Event event){
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {

		if(!completed && ((Panel)(event.getEnigma())).isCompleted()){
			setCompleted();
			event.getEventRoom().getLeft().setAccessible(true);
			event.getEventRoom().setDescription(Places.PHYSICS);
		}

		return completed;
	}

}
