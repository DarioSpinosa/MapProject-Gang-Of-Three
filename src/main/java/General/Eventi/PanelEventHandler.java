package General.Eventi;

import Entita.Room;
import Entita.Characters.Protagonist;
import General.Eventi.Enigmi.Panel;
import Resources.Places;

public class PanelEventHandler extends GenericEventHandler{

	public  PanelEventHandler(Event event, Room room){
		super(event, room);
	}

	@Override
	public void endEvent(Protagonist protagonist) {

		if(((Panel)(event.getEnigma())).isCompleted()){
			eventRoom.getLeft().setAccessible(true);
			eventRoom.setDescription(Places.PHYSICS);
		}
	}

}
