package General.Eventi;

import Entita.Room;
import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import Resources.Dialogs;

public class PackageEventHandler extends GenericEventHandler {

	public  PackageEventHandler(Event event, Room room){
		super(event, room);
	}

	@Override
	public void endEvent(Protagonist protagonist) {
		if(protagonist.getInventory().contains(event.getEnigma())) {
			eventRoom.getRight().setAccessible(true);
			((Npc)eventRoom.getCharacters().get(0)).setDialogue(Dialogs.BAKER_B);
			eventRoom.getRight().addCharacter(eventRoom.getCharacters().get(0));
			eventRoom.getCharacters().remove(0);
			protagonist.removeObject(event.getEnigma());
			protagonist.getInventory().addToContainer(reward);

		}
	}

}
