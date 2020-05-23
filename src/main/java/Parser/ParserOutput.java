/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Parser;
import General.GenericObject;
import Entita.Characters.Personaggio;
import General.Command;

public class ParserOutput {
    
    private Command comando;
    private String preposizione;
    private GenericObject primoOggetto;
    private GenericObject secondoOggetto;
    private Personaggio personaggio;
    
    public ParserOutput() {}
    
    public ParserOutput(Command comando) {
        this.comando = comando;
    }
    
    public ParserOutput(Command comando, GenericObject primoOggetto) {
        this.comando = comando;
        this.primoOggetto = primoOggetto;
    }
    
    public ParserOutput(Command comando, GenericObject primoOggetto, GenericObject secondoOggetto) {
        this.comando = comando;
        this.primoOggetto = primoOggetto;
        this.secondoOggetto = secondoOggetto;
    }
    
    public Command getComando() {
        return comando;
    }
    
    public GenericObject getPrimoOggetto() {
        return primoOggetto;
    }
    
    public GenericObject getSecondoOggetto() {
        return secondoOggetto;
    }
    
    public String getPreposizione(){
        return preposizione;
    }
    
    public Personaggio getPersonaggio(){
        return personaggio;
    }
    
    public void setCommand(Command comando) {
        this.comando = comando;
    }
    
    public void setPrimoOggetto(GenericObject primoOggetto) {
        this.primoOggetto = primoOggetto;
    }
    
    public void setSecondoOggetto(GenericObject secondoOggetto) {
        this.secondoOggetto = secondoOggetto;
    }
    
    public void setPreposizione(String preposizione){
        this.preposizione = preposizione;
    }
    
    public void setPersonaggio(Personaggio personaggio){
        this.personaggio = personaggio;
    }
    
    public void setOggetto(GenericObject oggetto) {
        if(primoOggetto == null) {
            primoOggetto = oggetto;
        } else if (secondoOggetto == null) {
            secondoOggetto = oggetto;
        }
    }
}
