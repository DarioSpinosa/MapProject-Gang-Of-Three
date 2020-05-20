/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;
import Entità.Partita;
import Parser.ParserOutput;
import Entità.Characters.Protagonista;
import Entità.Characters.Npc;

/**
 *
 * @author Elio
 */
public class GestoreAzioni extends GestoreAzioniEssentials{
    
    public GestoreAzioni(Partita partita, GestoreMessaggiEssentials stampa){
        super(partita, stampa);
    }
    
    @Override
    public void elaboraAzione(ParserOutput action){
        Protagonista protagonista = (Protagonista)partita.getProtagonista();
        switch(action.getComando().getCommandType()){
            case NORD:
                movimentoNord();
                break;
            case SUD:
                movimentoSud();
                break;
            case EST:
                movimentoEst();
                break;
            case OVEST:
                movimentoOvest();
                break;
            case INVENTARIO:
                int i = 0;
                stampa.stampaLinea();
                for(GenericObject oggetto : protagonista.getInventario().getContainer()){
                    stampa.stampaInventario(oggetto.getNome());
                    i++;
                }
                for(int j = i; j < protagonista.getInventario().getMaxSize(); j++){
                    stampa.stampaInventario("");
                }
                stampa.stampaLinea();
                break;
            case PRENDI:
                if(action.getPrimoOggetto() != null && action.getSecondoOggetto() == null){
                    if(partita.getStanzaCorrente().getOggetti().getContainer().contains(action.getPrimoOggetto())){
                        if(protagonista.getInventario().getContainer().size() < 6){
                            partita.getStanzaCorrente().removeOggetto(action.getPrimoOggetto());
                            protagonista.addOggetto(action.getPrimoOggetto());
                            stampa.stampaPresa(action.getPrimoOggetto());
                        } else {
                            stampa.messaggioInventarioPieno();
                        }
                    } else {
                        stampa.messaggioOggettoNonPresenteStanza();
                    }
                } else if (action.getPrimoOggetto() != null && action.getSecondoOggetto() != null
                        && action.getSecondoOggetto() instanceof GenericObjectContainer && action.getPreposizione().equals("da")){
                            lasciaOPrendiOggetto(action.getPrimoOggetto(), action.getSecondoOggetto(), protagonista, false);
                } else {
                    stampa.messaggioNonCompreso();
                }
                break;
            case LASCIA:
                if(action.getPrimoOggetto() != null && action.getSecondoOggetto() == null){
                    if(protagonista.getInventario().getContainer().contains(action.getPrimoOggetto())){
                        partita.getStanzaCorrente().addOggetto(action.getPrimoOggetto());
                        protagonista.removeOggetto(action.getPrimoOggetto());
                        stampa.stampaLascia(action.getPrimoOggetto());
                    } else {
                        stampa.messaggioOggettoNonPresenteInventario();
                    }
                }  else if (action.getPrimoOggetto() != null && action.getSecondoOggetto() != null
                            && action.getSecondoOggetto() instanceof GenericObjectContainer && action.getPreposizione().equals("in")){
                            if(protagonista.isInInventario(action.getPrimoOggetto())){
                                lasciaOPrendiOggetto(action.getPrimoOggetto(), action.getSecondoOggetto(), protagonista, true);
                            } else {
                                stampa.messaggioOggettoNonPresenteInventario();
                            }
                    } else {
                    stampa.messaggioNonCompreso();
                }
                break;
            case APRI:
                apriOChiudiOggetto(action.getPrimoOggetto(), action.getSecondoOggetto(), protagonista, true);
                break;
            case CHIUDI:
                apriOChiudiOggetto(action.getPrimoOggetto(), action.getSecondoOggetto(), protagonista, false);
                break;
            case GUARDA:
                if(action.getPrimoOggetto() != null && action.getSecondoOggetto() == null){
                    if(partita.getStanzaCorrente().getOggetti().getContainer().contains(action.getPrimoOggetto())
                        || protagonista.getInventario().getContainer().contains(action.getPrimoOggetto())){
                        stampa.stampaDescrizioneOggetto(action.getPrimoOggetto());
                    } else {
                        stampa.messaggioOggettoNonPresente();
                    }
                } else {
                    stampa.messaggioNonCompreso();
                }
                break;
            case COMBINA:
                if(action.getPrimoOggetto() != null && action.getSecondoOggetto() != null && (action.getPreposizione() == null || action.getPreposizione().equals("con"))){
                    if(protagonista.isInInventario(action.getPrimoOggetto()) && protagonista.isInInventario(action.getSecondoOggetto())){
                        GenericObject oggettoCombinato = Combinations.testCombination(action.getPrimoOggetto(), action.getSecondoOggetto());
                        if(oggettoCombinato != null){
                            protagonista.removeOggetto(action.getPrimoOggetto());
                            protagonista.removeOggetto(action.getSecondoOggetto());
                            protagonista.addOggetto(oggettoCombinato);
                            stampa.messaggioCombinazioneRiuscita(action.getPrimoOggetto(), action.getSecondoOggetto(), oggettoCombinato);
                        } else {
                            stampa.messaggioCombinazioneNonDisponibile(action.getPrimoOggetto(), action.getSecondoOggetto());
                        }
                    } else {
                        if(!protagonista.isInInventario(action.getPrimoOggetto())){
                            stampa.messaggioOggettoSpecificoNonInInventario(action.getPrimoOggetto());
                        }
                        if(!protagonista.isInInventario(action.getSecondoOggetto())){
                            stampa.messaggioOggettoSpecificoNonInInventario(action.getSecondoOggetto());
                        }
                    }
                } else {
                    stampa.messaggioNonCompreso();
                }
                break;
            case PARLA:
                if(action.getPersonaggio() != null && (action.getPreposizione() == null || action.getPreposizione().equals("con")) && action.getPersonaggio() instanceof Npc){
                    Npc npc = (Npc)partita.getStanzaCorrente().getPersonaggio(action.getPersonaggio());
                    if(!npc.getPresentato()){
                        stampa.stampaMessaggio(npc.getPresentazione());
                        npc.confirmPresentato();
                    }
                } else {
                    stampa.messaggioNonCompreso();
                }
                break;
            case USA:
                if(action.getPrimoOggetto() != null && action.getSecondoOggetto() == null){
                    if(protagonista.isInInventario(action.getPrimoOggetto())){
                        // applica effetto oggetto
                        if(action.getPrimoOggetto().isConsumabile()){
                            protagonista.removeOggetto(action.getPrimoOggetto());
                        }
                    } else {
                        stampa.messaggioOggettoNonPresenteInventario();
                    }
                } else {
                    stampa.messaggioNonCompreso();
                }
                break;
        }
    }
    
    @Override
    public void movimentoNord(){
        if(partita.getStanzaCorrente().getSopra() != null){
                    partita.setStanzaCorrente(partita.getStanzaCorrente().getSopra());
                    stampa.stampaStanza(partita.getStanzaCorrente().getNome(), partita.getStanzaCorrente().getDescrizione());
                } else {
                    stampa.messaggioStanzaIrraggiungibile();
                }
    }
    
    @Override
    public void movimentoSud(){
        if(partita.getStanzaCorrente().getSotto() != null){
                    partita.setStanzaCorrente(partita.getStanzaCorrente().getSotto());
                    stampa.stampaStanza(partita.getStanzaCorrente().getNome(), partita.getStanzaCorrente().getDescrizione());
                } else {
                    stampa.messaggioStanzaIrraggiungibile();
                }
    }
    
    @Override
    public void movimentoEst(){
        if(partita.getStanzaCorrente().getDestra() != null){
                    partita.setStanzaCorrente(partita.getStanzaCorrente().getDestra());
                    stampa.stampaStanza(partita.getStanzaCorrente().getNome(), partita.getStanzaCorrente().getDescrizione());
                } else {
                    stampa.messaggioStanzaIrraggiungibile();
                }
    }
    
    @Override
    public void movimentoOvest(){
        if(partita.getStanzaCorrente().getSinistra() != null){
                    partita.setStanzaCorrente(partita.getStanzaCorrente().getSinistra());
                    stampa.stampaStanza(partita.getStanzaCorrente().getNome(), partita.getStanzaCorrente().getDescrizione());
                } else {
                    stampa.messaggioStanzaIrraggiungibile();
                }
    }
    
    private void lasciaOPrendiOggetto(GenericObject oggetto1, GenericObject oggetto2, Protagonista protagonista, boolean lasciare){
            if( partita.getStanzaCorrente().getOggetti().getContainer().contains(oggetto2) ){
                GenericObjectContainer oggettoConStanza = (GenericObjectContainer)partita.getStanzaCorrente().getOggetto(oggetto2);
                if(oggettoConStanza.isOpened()){
                    if(lasciare){
                        protagonista.removeOggetto(oggetto1);
                        oggettoConStanza.addToContainer(oggetto1);
                        stampa.stampaLasciatoIn(oggetto1, oggetto2);
                    } else {
                        if(oggettoConStanza.contains(oggetto1)){
                            oggettoConStanza.removeFromContainer(oggetto1);
                            protagonista.addOggetto(oggetto1);
                            stampa.stampaPresaDa(oggetto1, oggetto2);
                        } else {
                            stampa.messaggioOggettoNonReperibile();
                        }
                    }
                } else {
                    stampa.messaggioOggettoChiuso(oggetto2);
                }
            } else if (protagonista.isInInventario(oggetto2)){
                GenericObjectContainer oggettoConInv = (GenericObjectContainer)protagonista.getOggetto(oggetto2);
                if(oggettoConInv.isOpened()){
                    if(lasciare){
                        protagonista.removeOggetto(oggetto1);
                        oggettoConInv.addToContainer(oggetto1);
                        stampa.stampaLasciatoIn(oggetto1,oggetto2);
                    } else {
                        if(oggettoConInv.contains(oggetto1)){
                            oggettoConInv.removeFromContainer(oggetto1);
                            protagonista.addOggetto(oggetto1);
                            stampa.stampaPresaDa(oggetto1, oggetto2);
                        } else {
                            stampa.messaggioOggettoNonReperibile();
                        }
                    }
                } else {
                    stampa.messaggioOggettoChiuso(oggetto2);
                }
            } else {
                stampa.messaggioOggettoNonPresente();
            }
    }
    
    private void apriOChiudiOggetto(GenericObject oggetto1, GenericObject oggetto2, Protagonista protagonista, boolean aprire){
        if(oggetto1 != null && oggetto2 == null){
                    if(oggetto1 instanceof GenericObjectContainer){
                            if(partita.getStanzaCorrente().getOggetti().getContainer().contains(oggetto1)){
                                if(aprire){
                                    ((GenericObjectContainer)partita.getStanzaCorrente().getOggetto(oggetto1)).open();
                                } else {
                                    ((GenericObjectContainer)partita.getStanzaCorrente().getOggetto(oggetto1)).close();
                                }
                            } else if(protagonista.isInInventario(oggetto1)){
                                if(aprire){
                                    ((GenericObjectContainer)protagonista.getOggetto(oggetto1)).open();
                                } else {
                                    ((GenericObjectContainer)protagonista.getOggetto(oggetto1)).close();
                                }
                            } else {
                                stampa.messaggioOggettoNonPresente();
                            }
                            for(GenericObject oggetto : ((GenericObjectContainer)oggetto1).getContainer()){ //TODO da rimuovere
                                stampa.stampaInventario(oggetto.getNome());
                            }
                            if(aprire){
                                stampa.stampaAperto(oggetto1);
                            } else {
                                stampa.stampaChiuso(oggetto1);
                            }
                    }  else {
                        if(aprire){
                            stampa.messaggioOggettoNonApribile();
                        } else {
                            stampa.messaggioOggettoNonChiudibile();
                        }
                    }
                } else {
                    stampa.messaggioNonCompreso();
                }
    }
}
