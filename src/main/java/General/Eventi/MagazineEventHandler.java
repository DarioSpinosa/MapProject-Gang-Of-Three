package General.Eventi;

import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import General.GenericObject;
import Resources.Dialogs;

public class MagazineEventHandler extends GenericEventHandler {

	public MagazineEventHandler(Event event) {
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		if (!completed && obj.equals(event.getEnigma())) {
			completed = true;
			((Npc) event.getEventRoom().getCharacters().get(0)).setDialogue(Dialogs.JANITOR_B);
			protagonist.removeObject(event.getEnigma());
			protagonist.addObject(event.getReward());
		}
		return completed;
	}

}
