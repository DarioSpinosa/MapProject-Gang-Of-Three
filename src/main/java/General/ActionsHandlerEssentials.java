/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;
import java.util.ArrayList;

import Entita.Game;
import Entita.Characters.Character;
import Entita.Room;
import Parser.ParserOutput;

/**
 *
 * @author Elio
 */
public abstract class ActionsHandlerEssentials {
    protected Game game;
    protected boolean gameCompleted = false;
    protected ArrayList<Command> commands = new ArrayList<>();
    protected ArrayList<GenericObject> currentObjects;
    protected MessagesHandlerEssentials printer;

    public ActionsHandlerEssentials(Game game, MessagesHandlerEssentials printer){
        this.game = game;
        this.printer = printer;
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
