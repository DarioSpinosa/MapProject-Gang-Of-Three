package General.Eventi;

import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import General.GenericObject;
import Resources.Dialogs;

public class PropulsorEventHandler extends GenericEventHandler{

	public PropulsorEventHandler(Event event) {
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		if(protagonist.isInInventory(obj) && obj.equals(event.getEnigma())) {
			completed = true;
			((Npc)event.getEventRoom().getCharacters().get(0)).setDialogue(Dialogs.VOLPE_B);
			event.getEventRoom().getRight().getRight().getDown().getDown().addCharacter(event.getEventRoom().getCharacters().get(0));
			event.getEventRoom().getCharacters().remove(0);
			((Npc)event.getEventRoom().getRight().getRight().getUp().getRight().getCharacters().get(1)).setDialogue(Dialogs.PILOT_B);
			event.getEventRoom().getRight().getRight().getDown().getDown().addCharacter(event.getEventRoom().getRight().getRight().getUp().getRight().getCharacters().get(1));
			event.getEventRoom().getRight().getRight().getUp().getRight().getCharacters().remove(1);
			((Npc)event.getEventRoom().getRight().getRight().getDown().getDown().getCharacters().get(0)).setDialogue(Dialogs.MORGAN_B);
			protagonist.removeObject(event.getEnigma());
		}

		return completed;

	}

}
