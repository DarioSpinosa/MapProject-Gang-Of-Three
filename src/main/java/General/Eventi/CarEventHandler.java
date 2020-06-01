package General.Eventi;

import Entita.Characters.Protagonist;
import General.GenericObject;
import General.GenericObjectContainer;

public class CarEventHandler extends GenericEventHandler{

	public CarEventHandler(Event event) {
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		if(!completed && obj.equals(event.getEnigma()) && protagonist.isInInventory(event.getEnigma())) {
			completed = true;
			protagonist.removeObject(event.getEnigma());
			((GenericObjectContainer)event.getEventRoom().getRoomObject("automobile")).setBlocked(false);
			((GenericObjectContainer)event.getEventRoom().getRoomObject("automobile")).open();
		}
		return completed;
	}

}
