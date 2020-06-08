/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package parser;
import java.util.ArrayList;

import entities.Command;
import entities.GenericObject;
import entities.Prepositions;
import entities.characters.Character;

/**
 * <Boundary> Responsabilita': analizza l'input dell'utente e controlla che la frase sia corretta.
 *
 *
 */

public abstract class ParserEssentials {
    protected boolean accepted = false;
    protected WordType lastWordType;
    protected String lastArticle;
    protected String lastPreposition;
    protected GenericObject lastObject;
    protected ArrayList<String> articles;
    protected Prepositions prepositions;
    protected ArrayList<String> adjectives = new ArrayList<>();
    protected int numberOfObjects = 0;

    public ParserEssentials() {}
   /**
    * Metodo essenziale di Parser che si occupa del controllo della frase
    *
    * @param input 			frase scritta dall'utente
    * @param commands		lista di comandi del gioco
    * @param oggetti		lista di oggetti del gioco
    * @param personaggi		lista di personaggi del gioco.
    * @return				oggetto di tipo ParserOutput contenente le informazioni rilevanti
    */
    public abstract ParserOutput parse(String input, ArrayList<Command> commands, ArrayList<GenericObject> oggetti, ArrayList<Character> personaggi);

    public boolean isAccepted() {
        return accepted;
    }
}
