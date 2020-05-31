package General.Eventi;

import Entita.Room;
import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import Resources.Dialogs;

public class magazineEventHandler extends GenericEventHandler {

	public magazineEventHandler(Event event, Room room) {
		super(event, room);
	}

	@Override
	public void endEvent(Protagonist protagonist) {
		if (protagonist.getInventory().contains(event.getEnigma())) {
			((Npc) eventRoom.getCharacters().get(0)).setDialogue(Dialogs.JANITOR_B);
			protagonist.removeObject(event.getEnigma());
			protagonist.addObject(reward);
		}

	}

}
