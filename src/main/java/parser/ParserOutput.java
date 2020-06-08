/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package parser;
import entities.Command;
import entities.GenericObject;
import entities.characters.Character;

/**
 *<Entity> Responsabilità: contiene le informazioni fondamentali ottenute in input dall'utente
 *nel parser.
 *
 */
public class ParserOutput {

    private Command command;
    private String preposition;
    private GenericObject firstObject;
    private GenericObject secondObject;
    private Character character;
    private String firstAdjective;
    private String secondAdjective;

    public ParserOutput() {}

    public ParserOutput(Command command) {
        this.command = command;
    }

    public ParserOutput(Command command, GenericObject firstObject) {
        this.command = command;
        this.firstObject = firstObject;
    }

    public ParserOutput(Command command, GenericObject firstObject, GenericObject secondObject) {
        this.command = command;
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public Command getCommand() {
        return command;
    }

    public GenericObject getFirstObject() {
        return firstObject;
    }

    public GenericObject getSecondObject() {
        return secondObject;
    }

    public String getPreposition(){
        return preposition;
    }

    public Character getCharacter(){
        return character;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setFirstObject(GenericObject firstObject) {
        this.firstObject = firstObject;
    }

    public void setSecondObject(GenericObject secondObject) {
        this.secondObject = secondObject;
    }

    public void setPreposition(String preposition){
        this.preposition = preposition;
    }

    public void setCharacter(Character character){
        this.character = character;
    }

    public void setObject(GenericObject object) {
        if(firstObject == null) {
            firstObject = object;
        } else if (secondObject == null) {
            secondObject = object;
        }
    }

    public String getFirstAdjective(){
        return firstAdjective;
    }

    public String getSecondAdjective(){
        return secondAdjective;
    }

    public void setAdjective(String adjective){
        if(firstAdjective == null){
            firstAdjective = adjective;
        } else if (secondAdjective == null){
            secondAdjective = adjective;
        }
    }
}
