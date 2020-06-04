package General.Eventi;

import java.io.Serializable;

import Entita.Characters.Character;
import Entita.Characters.Protagonist;
import General.GenericObject;

public abstract class GenericEventHandler implements Serializable {

	protected Event event;
	protected GenericEventHandler otherRoomEvent = null;
	protected boolean started = false;
	protected boolean completed = false;

	public GenericEventHandler() {
	}

	public GenericEventHandler(Event m) {
		event = m;
	}

	public GenericEventHandler(Event m, GenericEventHandler condition) {
		event = m;
		this.otherRoomEvent = condition;
	}

	public String startEvent() {

		String temp = "";

		if(otherRoomEvent != null) {
			if(otherRoomEvent.isCompleted())
				temp = start();
		}else
			temp = start();

		return temp;
	}

	private String start() {

		String temp = "";

		if (!started) {
			temp = event.getDescription();
			started = true;

			if(!event.getEventObjects().isEmpty()) {
				for(GenericObject object: event.getEventObjects()) {
					event.getEventRoom().addObject(object);
				}
			}

			if(!event.getEventCharacters().isEmpty()) {
				for(Character character: event.getEventCharacters()) {
					event.getEventRoom().addCharacter(character);
				}
			}

		}

		return temp;
	}

	public Event getEvent() {
		return event;
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

	public abstract boolean endEvent(Protagonist protagonist, GenericObject obj);

}
