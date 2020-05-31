package General.Eventi;

import Entita.Room;
import Entita.Characters.Npc;
import Entita.Characters.Character;
import Entita.Characters.Protagonist;
import General.GenericObject;
import Resources.Dialogs;

public class HelicopterEventHandler extends GenericEventHandler{

	public  HelicopterEventHandler(Event event, Room room){
		super(event, room);
	}

	@Override
	public String startEvent() {

		String temp = "";

		if(eventRoom.getUp().getUp().getLeft().getLeft().getEventHandler().isCompleted()) {

			if (started == false) {
				temp = event.getDescription();
				started = true;

				((Npc)eventRoom.getCharacter(0)).setDialogue(Dialogs.MORGAN_B);

				if(!eventObjects.isEmpty()) {
					for(GenericObject oggetto: eventObjects) {
						eventRoom.addObject(oggetto);
					}
				}

				if(!eventCharacters.isEmpty()) {
					for(Character p: eventCharacters) {
						eventRoom.addCharacter(p);
					}
				}
			}
		}
		return temp;
	}

	@Override
	public void endEvent(Protagonist p) {

	}


}
