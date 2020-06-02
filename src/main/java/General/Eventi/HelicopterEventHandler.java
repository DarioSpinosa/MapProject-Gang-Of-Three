package General.Eventi;

import Entita.Room;
import Entita.Characters.Protagonist;
import General.GenericObject;

public class HelicopterEventHandler extends GenericEventHandler {

	public  HelicopterEventHandler(Event event, Room condition){
		super(event, condition);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {

		if(!completed && obj.equals(event.getEnigma())){
			completed = true;
		}

		return completed;

	}


}
