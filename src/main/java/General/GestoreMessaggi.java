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
public final class GestoreMessaggi extends GestoreMessaggiEssentials{
    
    public GestoreMessaggi(AdventureGUI interfaccia){
        super(interfaccia);
    }
    
    @Override
    public void stampaMessaggio(String messaggio){
        interfaccia.stampaMessaggio(messaggio);
    }
    
    @Override
    public void messaggioStanzaIrraggiungibile(){
        interfaccia.stampaMessaggio("Non puoi andare qui!");
    }
    
    @Override
    public void messaggioComandoNonRiconosciuto(){
        interfaccia.stampaMessaggio("Comando non riconosciuto");
    }
    
    @Override
    public void stampaStanza(String nome, String descrizione){
        interfaccia.stampaMessaggio(nome);
        interfaccia.stampaMessaggio(descrizione);
    }
    
    @Override
    public void messaggioOggettoNonPresenteInventario(){
        interfaccia.stampaMessaggio("Oggetto non presente nell'inventario");
    }
    
    @Override
    public void stampaInventario(String oggetto){
        interfaccia.stampaMessaggio("- " + oggetto);
    }
    
    @Override
    public void messaggioInventarioPieno(){
        interfaccia.stampaMessaggio("Inventario pieno");
    }
    
    @Override
    public void messaggioOggettoNonPresenteStanza(){
        interfaccia.stampaMessaggio("Oggetto non presente nella stanza");
    }
    
    @Override
    public void messaggioOggettoNonApribile(){
        interfaccia.stampaMessaggio("Oggetto non apribile");
    }
    
    @Override
    public void messaggioOggettoNonChiudibile(){
        interfaccia.stampaMessaggio("Oggetto non chiudibile");
    }
    
    @Override
    public void messaggioOggettoNonPresente(){
        interfaccia.stampaMessaggio("Oggetto non presente né nella stanza né nell'inventario");
    }
    
    @Override
    public void stampaDescrizioneOggetto(GenericObject oggetto){
        interfaccia.stampaMessaggio(oggetto.getDescrizione());
    }
    
    @Override
    public void stampaPresa(GenericObject oggetto){
        interfaccia.stampaMessaggio("Hai preso: " + oggetto);
    }
    
    @Override
    public void stampaLascia(GenericObject oggetto){
        interfaccia.stampaMessaggio("Hai lasciato: " + oggetto);
    }
    
    @Override
    public void stampaAperto(GenericObject oggetto){
        interfaccia.stampaMessaggio("Hai aperto: " + oggetto);
    }
    
    @Override
    public void stampaChiuso(GenericObject oggetto){
        interfaccia.stampaMessaggio("Hai chiuso: " + oggetto);
    }
    
    @Override
    public void messaggioNonCompreso(){
        interfaccia.stampaMessaggio("Non ho capito le tue intenzioni, spiegati meglio!");
    }
    
    @Override
    public void stampaPresaDa(GenericObject oggetto1, GenericObject oggetto2){
        interfaccia.stampaMessaggio("Hai preso:  " + oggetto1 + " da " + oggetto2);
    }
    
    @Override
    public void messaggioOggettoNonReperibile(){
        interfaccia.stampaMessaggio("L'oggetto non contiene ciò che cerchi");
    }
    
    @Override
    public void messaggioOggettoChiuso(GenericObject oggetto){
        interfaccia.stampaMessaggio("L'oggetto " + oggetto + " è chiuso");
    }
    
    @Override
    public void stampaLasciatoIn(GenericObject oggetto1, GenericObject oggetto2){
        interfaccia.stampaMessaggio("Hai lasciato l'oggetto " + oggetto1 + " in " +  oggetto2);
    }
    
    @Override
    public void messaggioCombinazioneNonDisponibile(GenericObject oggetto1, GenericObject oggetto2){
        interfaccia.stampaMessaggio("Non è possibile combinare " + oggetto1 + " con " + oggetto2);
    }
    
    @Override
    public void messaggioCombinazioneRiuscita(GenericObject oggetto1, GenericObject oggetto2, GenericObject oggetto3){
        interfaccia.stampaMessaggio("Hai combinato " + oggetto1 + " con " + oggetto2 + " e ottenuto: " + oggetto3);
    }
    
    @Override
    public void messaggioOggettoSpecificoNonInInventario(GenericObject oggetto){
        interfaccia.stampaMessaggio("L'oggetto " + oggetto + " non è presente nel tuo inventario");
    }
    
    @Override
    public void messaggioOggettiNonPresenti(){
        interfaccia.stampaMessaggio("Oggetti non presenti nel tuo inventario");
    }
}
