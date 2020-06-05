package General.Eventi;

import Entita.Characters.Protagonist;
import General.GenericObject;

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
