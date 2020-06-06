package handlers.events;

import entities.GenericObject;
import entities.characters.Npc;
import entities.characters.Protagonist;
import resources.Dialogs;

public class PackageEventHandler extends GenericEventHandler {

	public  PackageEventHandler(Event event){
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		if(protagonist.isInInventory(obj) && obj.equals(event.getEnigma())) {
			completed = true;
			event.getEventRoom().getRight().setAccessible(true);
			((Npc)event.getEventRoom().getCharacters().get(0)).setDialogue(Dialogs.BAKER_B);
			event.getEventRoom().getRight().addCharacter(event.getEventRoom().getCharacters().get(0));
			event.getEventRoom().getCharacters().remove(0);
			protagonist.removeObject(event.getEnigma());
			protagonist.getInventory().addToContainer(event.getReward());

		}
		return completed;
	}

}
