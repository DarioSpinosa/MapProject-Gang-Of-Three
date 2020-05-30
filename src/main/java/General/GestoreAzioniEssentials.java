/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;
import java.util.ArrayList;

import Entita.Partita;
import Entita.Characters.Personaggio;
import Entita.Stanza;
import Parser.ParserOutput;

/**
 *
 * @author Elio
 */
public abstract class GestoreAzioniEssentials {
    protected Partita partita;
    protected boolean gameCompleted = false;
    protected ArrayList<Command> comandi = new ArrayList<>();
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
    
    public Stanza getStanzaCorrente(){
        return partita.getStanzaCorrente();
    }
    
    public boolean getCompleted(){
        return gameCompleted;
    }

    public void setListaComandi(ArrayList<Command> comandi){
    	  Command nord = new Command(CommandType.NORD, "nord");
          nord.setAlias(new String[]{"n", "Nord"});
          comandi.add(nord);
          Command ovest = new Command(CommandType.OVEST, "ovest");
          ovest.setAlias(new String[]{"o", "Ovest"});
          comandi.add(ovest);
          Command sud = new Command(CommandType.SUD, "sud");
          sud.setAlias(new String[]{"s", "Sud"});
          comandi.add(sud);
          Command est = new Command(CommandType.EST, "est");
          est.setAlias(new String[]{"e","Est"});
          comandi.add(est);
          Command inventario = new Command(CommandType.INVENTARIO, "inventario");
          comandi.add(inventario);
          Command guarda = new Command(CommandType.GUARDA, "guarda");
          guarda.setAlias(new String[]{"osserva", "analizza", "ispeziona"});
          comandi.add(guarda);
          Command prendi = new Command(CommandType.PRENDI, "prendi");
          prendi.setAlias(new String[]{"afferra"});
          comandi.add(prendi);
          Command usa = new Command(CommandType.USA, "usa");
          usa.setAlias(new String[]{"utilizza"});
          comandi.add(usa);
          Command lascia = new Command(CommandType.LASCIA, "lascia");
          lascia.setAlias(new String[]{"poggia", "posa"});
          comandi.add(lascia);
          Command apri = new Command(CommandType.APRI, "apri");
          apri.setAlias(new String[]{"alza"});
          comandi.add(apri);
          Command chiudi = new Command(CommandType.CHIUDI, "chiudi");
          comandi.add(chiudi);
          Command combina = new Command(CommandType.COMBINA, "combina");
          comandi.add(combina);
          Command parla = new Command(CommandType.PARLA, "parla");
          comandi.add(parla);
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
