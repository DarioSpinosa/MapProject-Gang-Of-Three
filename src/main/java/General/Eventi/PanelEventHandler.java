package General.Eventi;

import Entita.Characters.Protagonist;
import General.GenericObject;
import General.Eventi.Enigmi.Panel;
import Resources.Places;

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
