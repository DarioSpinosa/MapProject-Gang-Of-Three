package handlers.events;

import java.io.Serializable;
import java.util.ArrayList;

import entities.GenericObject;
import entities.Room;
import entities.characters.Character;
/**
 *<Entity> Rappresenta un evento o quest all'interno di una stanza, esso puo essere di due tipi:
 *La risoluzione di un enigma, oppure la consegna di un oggetto. In maniera opzionale permette di aggiungere
 *un oggetto ricompensa al termine dell'evento o di far comparire nuovi personaggi all'interno della mappa di gioco
 *
 *
 */
public class Event implements Serializable {

	private final String description;
	private GenericObject enigma;
	private Room eventRoom;
	private GenericObject reward;
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
