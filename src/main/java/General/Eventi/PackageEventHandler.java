package General.Eventi;

import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import General.GenericObject;
import Resources.Dialogs;

public class PackageEventHandler extends GenericEventHandler {

	public  PackageEventHandler(Event event){
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {
		if(!completed && obj.equals(event.getEnigma()) && protagonist.getInventory().contains(event.getEnigma())) {
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
