package General.Eventi;

import Entita.Room;
import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import Resources.Dialogs;

public class PropulsorEventHandler extends GenericEventHandler{

	public PropulsorEventHandler(Event event, Room room) {
		super(event,room);
	}

	@Override
	public void endEvent(Protagonist protagonist) {
		if(protagonist.getInventory().contains(event.getEnigma())) {
			setCompleted();
			((Npc)eventRoom.getCharacters().get(0)).setDialogue(Dialogs.VOLPE_B);
			eventRoom.getRight().getRight().getDown().getDown().addCharacter(eventRoom.getCharacters().get(0));
			eventRoom.getCharacters().remove(0);
			((Npc)eventRoom.getRight().getRight().getUp().getRight().getCharacters().get(1)).setDialogue(Dialogs.PILOT_B);
			eventRoom.getRight().getRight().getDown().getDown().addCharacter(eventRoom.getRight().getRight().getUp().getRight().getCharacters().get(1));
			eventRoom.getRight().getRight().getUp().getRight().getCharacters().remove(1);
			protagonist.removeObject(event.getEnigma());
			protagonist.getInventory().addToContainer(reward);

		}

	}

}
