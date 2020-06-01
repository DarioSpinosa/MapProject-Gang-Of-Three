package General.Eventi;

import Entita.Characters.Protagonist;
import General.GenericObject;

public class PhysicsEventHandler extends GenericEventHandler{

	public PhysicsEventHandler(Event event){
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		GenericObject enigma = event.getEnigma();

		if(!completed && obj.equals(enigma) && obj.getAdjectives().equals(enigma.getAdjectives())
				&& protagonist.getInventory().contains(enigma)) {
			completed = true;
			protagonist.getInventory().removeFromContainer((event.getEnigma()));
			event.getEventRoom().getLeft().setAccessible(true);
       }

		return completed;
	}
}
