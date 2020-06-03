package General.Eventi;

import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import General.GenericObject;
import General.Eventi.Enigmi.Coffe;
import Resources.Dialogs;

public class CoffeEventHandler extends GenericEventHandler {

	public CoffeEventHandler(Event event){
		super(event);
	}

	@Override
	public boolean endEvent(Protagonist protagonist, GenericObject obj) {

		if(!completed && ((Coffe)event.getEnigma()).isCompleted()){
			setCompleted();
			((Npc)event.getEventRoom().getCharacters().get(0)).setDialogue(Dialogs.CANNAVACCIUOLO_B);
			protagonist.getInventory().addToContainer(event.getReward());

		}
		return completed;
	}
}
