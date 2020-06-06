package handlers.events;

import entities.GenericObject;
import entities.characters.Npc;
import entities.characters.Protagonist;
import resources.Dialogs;

public class MagazineEventHandler extends GenericEventHandler {

	public MagazineEventHandler(Event event) {
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		if (protagonist.isInInventory(obj) && obj.equals(event.getEnigma())) {
			completed = true;
			((Npc) event.getEventRoom().getCharacters().get(0)).setDialogue(Dialogs.JANITOR_B);
			protagonist.removeObject(event.getEnigma());
			protagonist.addObject(event.getReward());
		}
		return completed;
	}

}
