package General.Eventi;

import Entita.Characters.Protagonist;
import General.GenericObject;

public class PhysicsDoorEventHandler extends GenericEventHandler{

	public PhysicsDoorEventHandler(Event event){
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		GenericObject enigma = event.getEnigma();

		if(protagonist.isInInventory(obj) && obj.equals(enigma)) {
			completed = true;
			protagonist.getInventory().removeFromContainer((event.getEnigma()));
			event.getEventRoom().getLeft().setAccessible(true);
       }

		return completed;
	}
}
