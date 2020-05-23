/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Parser;
import General.Command;
import General.Name;
import java.util.ArrayList;

import Entita.Characters.Personaggio;
import General.GenericObject;

public abstract class ParserEssentials {
    protected boolean accepted = false;
    protected WordType lastWordType;
    protected String lastArticle;
    protected String lastPreposition;
    protected ArrayList<String> articles;
    protected ArrayList<String> prepositions;
    protected ArrayList<String> adjectives = new ArrayList<>();
    protected int numberOfObjects = 0;
    
    public ParserEssentials() {}
    
    public abstract ParserOutput parse(String input, ArrayList<Command> commands, ArrayList<GenericObject> oggetti, ArrayList<Personaggio> personaggi);
    
    public boolean getAccepted() {return accepted;}
}
