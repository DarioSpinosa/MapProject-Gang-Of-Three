/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entità;

import Entità.Characters.Npc;
import Entità.Characters.Personaggio;
import Entità.Characters.Protagonista;
import General.Combinations;
import General.Command;
import General.CommandType;
import Parser.WordType;
import java.util.ArrayList;
import java.util.Set;
import General.GenericObject;
import General.GenericObjectContainer;
import General.Name;

public class Partita {
    
    private Stanza stanzaCorrente;
    private Personaggio protagonista;
    private ArrayList<Command> comandi = new ArrayList<>();
    private ArrayList<GenericObject> oggetti = new ArrayList<>();
    
    public ArrayList<Command> getComandi(){
        return comandi;
    }
    
    public ArrayList<GenericObject> getOggetti(){
        return oggetti;
    }
    
    public Partita() {
        protagonista = new Protagonista("Pippo");
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
        
        Stanza strada1 = new Stanza("Strada Sud 1", "MO E C'IE TUTT STU TRAFFC ");
        strada1.addEvento("Sono le 7.32 del mattino e sei un autobus FSE che puzza di sterco di capra, sembra una giornata come tante"
                + "\naltre ma ecco che proprio quando eri quasi arrivato in università quel gran coglione dell'autista fora una gomma,"
                + "\nnon ti resta che scendere e continuare a piedi");
        Stanza strada2 = new Stanza("Strada Sud 2", "MO E C'IE TUTT STU TRAFFC ");
        Stanza strada3 = new Stanza("Strada Sud 3", "MO E C'IE TUTT STU TRAFFC ");
        Stanza strada4 = new Stanza("Strada Est", "");
        Stanza strada5 = new Stanza("Strada Ovest", "");
        Stanza strada6 = new Stanza("Strada Nord 1", "");
        Stanza strada7 = new Stanza("Strada Nord 2", "");
        Stanza strada8 = new Stanza("Strada Nord 3", "");
        Stanza rotonda = new Stanza("Rotonda", "");
        Stanza esecutivo = new Stanza("Executive", "Boh Fabio mettila tu");
        Stanza pizzeria = new Stanza("Pizzeria", "Iamme ia famm na pizz");
        Stanza vicoloCieco = new Stanza("Vicolo cieco", "Ehy fra, hai portato la bamba?");
        Stanza parcheggio = new Stanza("Parcheggio", "NON C'E' MAI UN CAZZO DI POSTO, FANCULO USO QUELLO DEL DISABILE");
        Stanza fisica1 = new Stanza("Atrio di Fisica", "");
        Stanza fisica2 = new Stanza("Sala raggi cosmici", "");
        Stanza fisica3 = new Stanza("Ufficio", "Sala del trono di Volpe");
        Stanza chimica1 = new Stanza("Bar di Chimica", "Un caffe al glicerolo grazie");
        Stanza chimica2 = new Stanza("Sala Pozioni", "Insegnante Severus Piton");
        Stanza informatica1 = new Stanza("Atrio DIB", "GUARDA! C'E' PASQUALE LOPS");
        Stanza informatica2 = new Stanza("LABORATIO P.C.", "HACKER-MAN TIME");
        
        strada1.setSopra(strada2).setDestra(esecutivo);
        esecutivo.setSinistra(strada1);
        strada2.setDestra(pizzeria).setSopra(strada3).setSotto(strada1);
        pizzeria.setSinistra(strada2);
        strada3.setSotto(strada2).setSopra(rotonda);
        rotonda.setSotto(strada3).setSinistra(strada4).setDestra(strada5).setSopra(strada6);
        strada4.setDestra(rotonda).setSinistra(vicoloCieco);
        vicoloCieco.setDestra(strada4);
        strada5.setSinistra(rotonda).setDestra(parcheggio);
        parcheggio.setSinistra(strada5);
        strada6.setSotto(rotonda).setSopra(strada7);
        strada7.setSotto(strada6).setSinistra(fisica1).setDestra(chimica1).setSopra(strada8);
        fisica1.setSinistra(fisica2).setDestra(strada7);
        fisica2.setSopra(fisica3).setDestra(fisica1);
        fisica3.setSotto(fisica2);
        chimica1.setSinistra(strada7).setDestra(chimica2);
        chimica2.setSinistra(chimica1);
        strada8.setSotto(strada7).setDestra(informatica1);
        informatica1.setSinistra(strada8).setDestra(informatica2);
        informatica2.setSinistra(informatica1);
        
        strada1.addPersonaggio(new Npc("autistaa", new Name("autista", WordType.NOME_MASCHILE), "Sono l'autista!"));
        pizzeria.addPersonaggio(new Npc("cameriere"));
        fisica3.addPersonaggio(new Npc("giacomo volpe"));
        chimica1.addPersonaggio(new Npc("bruno"));
        informatica1.addPersonaggio(new Npc("antonino"));
        
        GenericObject torta = new GenericObject(new Name("torta", WordType.NOME_FEMMINILE), "una deliziosa torta");
        torta.setAlias(new String[]{"tortina"});
        GenericObject baule = new GenericObjectContainer(new Name("baule", WordType.NOME_FEMMINILE), "un baule");
        GenericObject spada = new GenericObject(new Name("spada", WordType.NOME_FEMMINILE), "una spada");
        oggetti.add(spada);
        strada1.addOggetto(spada);
        oggetti.add(baule);
        strada1.addOggetto(baule);
        oggetti.add(torta);
        strada1.addOggetto(torta);
        Combinations.addCombination(torta, baule, spada);
        
        stanzaCorrente = strada1;
    }
    
    public void creaProtagonista(String nome) {
        protagonista = new Protagonista(nome);
    }
    
    public String getNomeProtagonista() {
        return protagonista.getNome();
    }
    
    public int getVitaProtagonista() {
        return protagonista.getHealtPoints();
    }
    
    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }
    
    public void setStanzaCorrente(Stanza room) {
        stanzaCorrente = room;
    }
    
    public Personaggio getProtagonista(){
        return protagonista;
    }
    
    /*public void muovi(String s) {
        
        int i = 0;
        do {
            Command comando = new Command();
            comando = comandi.get(i);
            Set<String> alias = comando.getAlias();
            
            for(String a : alias) {
                if(a.equalsIgnoreCase(s)) {
                    
                    switch(comando.getCommandType()) {
                        case EST:
                            if(getStanzaCorrente().getDestra() != null) {
                                setStanzaCorrente(getStanzaCorrente().getDestra());
                            } else { System.out.println("Non puoi andare in questa direzione!");}
                            break;
                        case OVEST:
                            if(this.getStanzaCorrente().getSinistra() != null) {
                                setStanzaCorrente(getStanzaCorrente().getSinistra());
                            } else { System.out.println("Non puoi andare in questa direzione!");}
                            break;
                        case NORD:
                            if(this.getStanzaCorrente().getSopra() != null) {
                                setStanzaCorrente(getStanzaCorrente().getSopra());
                            } else { System.out.println("Non puoi andare in questa direzione!");}
                            break;
                        case SUD:
                            if(this.getStanzaCorrente().getSotto() != null) {
                                setStanzaCorrente(getStanzaCorrente().getSotto());
                            } else { System.out.println("Non puoi andare in questa direzione!");}
                            break;
                    }
                    break;
                }
            }
            i++;
            
        }while(i < comandi.size());
    }*/
}

