package General.Eventi;

import Entita.Characters.Protagonist;
import General.GenericObject;

public class HelicopterEventHandler extends GenericEventHandler {

	public  HelicopterEventHandler(Event event, GenericEventHandler condition){
		super(event, condition);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {

		if(protagonist.isInInventory(obj) && obj.equals(event.getEnigma())){
			completed = true;
		}

		return completed;

	}


}
