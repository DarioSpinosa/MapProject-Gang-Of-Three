/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;
import Main.AdventureGUI;
/**
 *
 * @author Elio
 */
public abstract class GestoreMessaggiEssentials {
    protected AdventureGUI interfaccia;
    
    public GestoreMessaggiEssentials(AdventureGUI interfaccia){
        this.interfaccia = interfaccia;
    }
    
    public void stampaLinea(){
        interfaccia.stampaMessaggio("-----------------------------------------------");
    }
    
    public void stampaVita(int healPoints){
        interfaccia.setHealthLabel(healPoints);
    }
    
    public void stampaInventario(GenericObject oggetto){
    	if(oggetto.getAggettivi() == null) {
    		interfaccia.stampaMessaggio("- " + oggetto);
    	} else {
    		interfaccia.stampaMessaggio("- " + oggetto + " " + oggetto.getAggettivi().toArray()[0]);
    	}
    }
    
    public abstract void messaggioIntornoATe();
    public abstract void stampaMessaggio(String messaggio);
    public abstract void messaggioStanzaIrraggiungibile();
    public abstract void messaggioComandoNonRiconosciuto();
    public abstract void stampaStanza(String nome, String descrizione);
    public abstract void messaggioOggettoNonPresenteInventario();
    public abstract void messaggioInventarioPieno();
    public abstract void messaggioOggettoNonPresenteStanza();
    public abstract void messaggioOggettoNonApribile();
    public abstract void messaggioOggettoNonChiudibile();
    public abstract void messaggioOggettoNonPresente();
    public abstract void stampaDescrizioneOggetto(GenericObject oggetto);
    public abstract void stampaPresa(GenericObject oggetto);
    public abstract void stampaLascia(GenericObject oggetto);
    public abstract void stampaAperto(GenericObject oggetto);
    public abstract void stampaChiuso(GenericObject oggetto);
    public abstract void messaggioNonCompreso();
    public abstract void stampaPresaDa(GenericObject oggetto1, GenericObject oggetto2);
    public abstract void messaggioOggettoNonReperibile();
    public abstract void messaggioOggettoChiuso(GenericObject oggetto);
    public abstract void stampaLasciatoIn(GenericObject oggetto1, GenericObject oggetto2);
    public abstract void messaggioCombinazioneNonDisponibile(GenericObject oggetto1, GenericObject oggetto2);
    public abstract void messaggioCombinazioneRiuscita(GenericObject oggetto1, GenericObject oggetto2, GenericObject oggetto3);
    public abstract void messaggioOggettoSpecificoNonInInventario(GenericObject oggetto);
    public abstract void messaggioOggettiNonPresenti();
    public abstract void messaggioOggettoGiaAperto(GenericObject oggetto);
    public abstract void messaggioOggettoGiaChiuso(GenericObject oggetto);
    public abstract void messaggioVitaMassima();
    public abstract void messaggioVitaCurataDi(GenericObject oggetto, int heal);
    public abstract void messaggioVitaCurataMassimo(GenericObject oggetto);
}
