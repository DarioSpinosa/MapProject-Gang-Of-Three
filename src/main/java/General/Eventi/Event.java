package General.Eventi;

import java.util.ArrayList;

import Entita.Room;
import Entita.Characters.Character;
import General.GenericObject;
import java.io.Serializable;

public class Event implements Serializable {

	private final String description;
	private GenericObject enigma;
	private Room eventRoom;
	private GenericObject reward = null;
	private ArrayList<GenericObject> eventObjects = new ArrayList<>();
	private ArrayList<Character> eventCharacters = new ArrayList<>();

	public Event(String description, Room room) {
		this.description = description;
		this.eventRoom = room;
	}

	public void setEnigma(GenericObject object) {
		enigma = object;

	}

	public void setReward(GenericObject object) {
		reward = object;
	}

	public GenericObject getEnigma(){
		return enigma;
	}

	public String getDescription() {
		return description;
	}

	public Room getEventRoom() {
		return eventRoom;
	}

	public GenericObject getReward() {
		return reward;
	}

	public void addEventObject(GenericObject object) {
		eventObjects.add(object);
	}

	public void addEventCharacter(Character character) {
		eventCharacters.add(character);
	}

	public  ArrayList<GenericObject> getEventObjects(){
		return eventObjects;
	}

	public  ArrayList<Character> getEventCharacters() {
		return eventCharacters;
	}
}
