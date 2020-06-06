package handlers.events;

import entities.GenericObject;
import entities.characters.Protagonist;
import entities.enigmas.Panel;
import resources.Places;

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
