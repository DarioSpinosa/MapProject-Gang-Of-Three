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

		if(!completed && obj.equals(enigma) && protagonist.getInventory().getContainer().size() != 0
				&& protagonist.getInventory().contains(enigma)) {
			completed = true;
			protagonist.getInventory().removeFromContainer((event.getEnigma()));
			event.getEventRoom().getRight().setAccessible(true);
       }
		return completed;
	}
}
