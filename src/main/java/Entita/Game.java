/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita;

import java.io.IOException;
import java.util.ArrayList;

import CaricamentoFile.CombinationsFile;
import CaricamentoFile.ObjectsFile;
import CaricamentoFile.RoomFile;
import Entita.Characters.Character;
import Entita.Characters.Protagonist;
import General.Combinations;
import General.GenericObject;
import General.Name;
import Parser.WordType;
import Resources.Names;

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
