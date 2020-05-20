/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;
import Entità.Partita;
import java.util.ArrayList;
import Parser.ParserOutput;
import General.GenericObject;
import Entità.Characters.Personaggio;

/**
 *
 * @author Elio
 */
public abstract class GestoreAzioniEssentials {
    protected Partita partita;
    protected ArrayList<Command> comandi;
    protected ArrayList<GenericObject> oggetti;
    protected GestoreMessaggiEssentials stampa;
    
    public GestoreAzioniEssentials(Partita partita, GestoreMessaggiEssentials stampa){
        this.partita = partita;
        this.stampa = stampa;
    }
    
    public ArrayList<Command> getComandi(){
        return comandi;
    }
    
    public ArrayList<GenericObject> getOggetti(){
        return oggetti;
    }
    
    public void setListaOggetti(ArrayList<GenericObject> oggetti){
        this.oggetti = oggetti;
    }
    
    public void setListaComandi(ArrayList<Command> comandi){
        this.comandi = comandi;
    }
    
    public ArrayList<Personaggio> getPersonaggi(){
        return partita.getStanzaCorrente().getPersonaggi();
    }
    
    public abstract void elaboraAzione(ParserOutput action);
    public abstract void movimentoNord();
    public abstract void movimentoSud();
    public abstract void movimentoEst();
    public abstract void movimentoOvest();
}
