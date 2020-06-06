/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;
import java.util.ArrayList;

import entities.Command;
import entities.Game;
import entities.GenericObject;
import entities.Prepositions;
import entities.Room;
import entities.characters.Character;
import parser.ParserOutput;

/**
 *
 * @author Elio
 */
public abstract class ActionsHandlerEssentials {
    protected Game game;
    protected boolean gameCompleted = false;
    protected ArrayList<Command> commands = new ArrayList<>();
    protected ArrayList<GenericObject> currentObjects;
    protected MessagesEssentials printer;
    protected Prepositions prepositions;

    public ActionsHandlerEssentials(Game game, MessagesEssentials printer, Prepositions prepositions){
        this.game = game;
        this.printer = printer;
        this.prepositions = prepositions;
    }

    public ArrayList<Command> getCommands(){
        return commands;
    }

    public ArrayList<GenericObject> getObjects(){
        return currentObjects;
    }

    public void setObjectsList(ArrayList<GenericObject> currentObjects){
        this.currentObjects = currentObjects;
    }
    
    public Room getCurrentRoom(){
        return game.getCurrentRoom();
    }
    
    public boolean getCompleted(){
        return gameCompleted;
    }

    public ArrayList<Character> getCharacters(){
        return game.getCurrentRoom().getCharacters();
    }

    public abstract void processAction(ParserOutput action);
    public abstract void moveNord();
    public abstract void moveSud();
    public abstract void moveEst();
    public abstract void moveOvest();
}
