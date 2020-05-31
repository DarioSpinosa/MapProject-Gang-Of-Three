package General.Eventi;

import java.util.ArrayList;

import Entita.Room;
import Entita.Characters.Character;
import Entita.Characters.Protagonist;
import General.GenericObject;

public abstract class GenericEventHandler {

	protected Event event;
	protected Room eventRoom;
	protected GenericObject reward = null;
	protected boolean started = false;
	protected boolean completed = false;
	protected ArrayList<GenericObject> eventObjects = new ArrayList<>();
	protected ArrayList<Character> eventCharacters = new ArrayList<>();

	public GenericEventHandler() {
	}

	public GenericEventHandler(Event m, Room stanza) {
		event = m;
		this.eventRoom = stanza;
	}

	public String startEvent() {

		String temp = "";

		if (!started) {
			temp = event.getDescription();
			started = true;

			if(!eventObjects.isEmpty()) {
				for(GenericObject object: eventObjects) {
					eventRoom.addObject(object);
				}
			}

			if(!eventCharacters.isEmpty()) {
				for(Character character: eventCharacters) {
					eventRoom.addCharacter(character);
				}
			}

		}

		return temp;
	}

	public Event getEvent() {
		return event;
	}

	public void setReward(GenericObject object) {
		reward = object;
	}

	public boolean isStarted() {
		return started;
	}

	public boolean isCompleted() {
		return completed;
	}

	protected void setCompleted() {
		completed = true;
	}

	public void addEventObject(GenericObject object) {
		eventObjects.add(object);
	}

	public void addEventCharacter(Character character) {
		eventCharacters.add(character);
	}
        
	public abstract void endEvent(Protagonist protagonist);

}
