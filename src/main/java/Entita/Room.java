/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entita;

import java.io.Serializable;
import java.util.ArrayList;

import Entita.Characters.Character;
import General.GenericObject;
import General.GenericObjectContainer;
import General.Name;
import General.Eventi.GenericEventHandler;
import Parser.WordType;

/**
 *
 * @author DarioSpinosa
 */

public class Room implements Serializable{

	// attributi
	private final String name;
	private String description;
	private Room up = null;
	private Room down = null;
	private Room right = null;
	private Room left = null;
	private boolean accessible = true;
	private ArrayList<GenericEventHandler> roomEvent = new ArrayList<>();
	private final GenericObjectContainer roomObjects = new GenericObjectContainer(new Name("Oggetti", WordType.NOME), "");
	private final ArrayList<Character> characters = new ArrayList<>();

	// costruttore
	public Room(String nome, String descrizione) {
		this.name = nome;
		this.description = descrizione;
	}

	// METODI SETTER
	public void setDescription(String descrizione) {
		this.description = descrizione;
	}

	public Room setUp(Room sopra) {
		this.up = sopra;
		return this;
	}

	public Room setDown(Room sotto) {
		this.down = sotto;
		return this;
	}

	public Room setRight(Room destra) {
		this.right = destra;
		return this;
	}

	public Room setLeft(Room sinistra) {
		this.left = sinistra;
		return this;
	}

	public void setAccessible(boolean b) {
		accessible = b;
	}

	public void setEventHandler(GenericEventHandler e) {
		roomEvent.add(e);
	}

	// METODI GETTER
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Room getUp() {
		return up;
	}

	public Room getDown() {
		return down;
	}

	public Room getRight() {
		return right;
	}

	public Room getLeft() {
		return left;
	}

	public boolean getAccessible() {
		return accessible;
	}

	public Character getCharacter(int i) {
		return characters.get(i);
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public Character getCharacter(Character personaggio) {
		return characters.get(characters.indexOf(personaggio));
	}

	public GenericObject getRoomObject(String s) {
		for(GenericObject oggetto: roomObjects.getContainer()) {
			if(oggetto.getObjectName().equals(s))
				return oggetto;
		}

		return null;
	}

	public GenericObjectContainer getRoomObjects() {
		return roomObjects;
	}

	public GenericEventHandler getEventHandler() {

		int i = 0;
		GenericEventHandler handler = null;

		if(roomEvent.size() != 0) {
			do {
				if(!roomEvent.get(i).isCompleted())
					handler = roomEvent.get(i);
			i++;
			}while(handler != null && i < roomEvent.size());
		}

		return handler;
	}

	public GenericObject getRoomObject(GenericObject oggetto) {
		for (GenericObject obj : roomObjects.getContainer()) {
			if (obj.equals(oggetto)) {
				return obj;
			}
		}
		return null;
	}

	public void addCharacter(Character p) {
		characters.add(p);
	}

	public void removeCharacter(Character p) {
		characters.remove(p);
	}

	public void addObject(GenericObject o) {
		roomObjects.addToContainer(o);
	}

	public void removeObject(GenericObject o) {
		roomObjects.removeFromContainer(o);
	}


	public boolean isInRoom(GenericObject oggetto){
		for(GenericObject obj : roomObjects.getContainer()){
			if(oggetto.equals(obj)){
				return true;
			}
		}
		return false;
	}
}
