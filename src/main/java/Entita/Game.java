/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita;

import CaricamentoFile.ObjectsFile;
import CaricamentoFile.RoomsFile;
import java.util.ArrayList;
import Entita.Characters.Character;
import Entita.Characters.Protagonist;
import General.GenericObject;
import General.Name;
import Parser.WordType;
import Resources.Names;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

	private Room currentRoom;
	private Character protagonist;
	private ArrayList<GenericObject> gameObjects = new ArrayList<>();
        private ArrayList<Room> gameRooms = new ArrayList<>();

	public ArrayList<GenericObject> getObjects() {
		return gameObjects;
	}

	public Game() {
		protagonist = new Protagonist(new Name(Names.PROTAGONIST_NAME, WordType.NOME_PROPRIO));
            try {
                gameObjects = ObjectsFile.loadObjects();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                gameRooms = RoomsFile.loadRooms();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
		currentRoom = gameRooms.get(0);
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
