package General.Eventi;

import Entita.Room;
import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import General.Eventi.Enigmi.Coffe;
import Resources.Dialogs;

public class CoffeEventHandler extends GenericEventHandler{

	public CoffeEventHandler(Event event, Room room){
		super(event, room);
	}

	@Override
	public void endEvent(Protagonist protagonist) {

		if(((Coffe)(event.getEnigma())).isCompleted()){
			((Npc)eventRoom.getCharacters().get(0)).setDialogue(Dialogs.CANNAVACCIUOLO_B);
			protagonist.getInventory().addToContainer(reward);

		}
	}


}
