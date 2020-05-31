package General.Eventi;

import Entita.Room;
import Entita.Characters.Protagonist;
import General.GenericObjectContainer;

public class CarEventHandler extends GenericEventHandler{

	public CarEventHandler(Event event, Room room) {
		super(event,room);
	}

	@Override
	public void endEvent(Protagonist protagonist) {
		if(protagonist.isInInventory(event.getEnigma())) {
			setCompleted();
			protagonist.removeObject(event.getEnigma());
			((GenericObjectContainer)eventRoom.getRoomObject("automobile")).setBlocked(false);
			((GenericObjectContainer)eventRoom.getRoomObject("automobile")).open();
		}
	}

}
