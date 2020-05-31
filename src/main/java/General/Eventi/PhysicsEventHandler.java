package General.Eventi;

import Entita.Room;
import Entita.Characters.Protagonist;

public class PhysicsEventHandler extends GenericEventHandler{

	public PhysicsEventHandler(Event event, Room room){
		super(event, room);
	}

	@Override
	public void endEvent(Protagonist protagonist) {
		if(protagonist.getInventory().contains(event.getEnigma()))
			protagonist.getInventory().removeFromContainer((event.getEnigma()));
			eventRoom.getLeft().setAccessible(true);
	}
}
