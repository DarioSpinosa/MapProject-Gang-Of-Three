/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package entities;

import java.io.IOException;
import java.util.ArrayList;

import entities.characters.Character;
import entities.characters.Protagonist;
import loadingFile.CombinationsFile;
import loadingFile.ObjectsFile;
import loadingFile.RoomFile;
import parser.WordType;
import resources.Names;
/**
 *
 * <Entity> Responsabilità: rappresenta una partita. Contiene informazioni relative al luogo corrente,
 * al protagonista, agli oggetti nel gioco e alle loro possibili combinazioni.
 *
 */
public class Game {

	private Room currentRoom;
	private Character protagonist;
    private Combinations combinations = new Combinations();
	private ArrayList<GenericObject> gameObjects = new ArrayList<>();

	public ArrayList<GenericObject> getObjects() {
		return gameObjects;
	}

	public Game() throws IOException, ClassNotFoundException {
            protagonist = new Protagonist(new Name(Names.PROTAGONIST_NAME, WordType.NOME_PROPRIO));
            gameObjects = ObjectsFile.loadObjects();
            currentRoom = RoomFile.loadRoom();
            combinations.setCombinations(CombinationsFile.loadCombinations());
	}

        public Combinations getCombinations(){
            return combinations;
        }

	public String getProtagonistName() {
		return protagonist.getName().getName();
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}

	public Character getProtagonist() {
		return protagonist;
	}

}
