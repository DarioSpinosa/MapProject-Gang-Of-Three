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
public final class GestoreMessaggi extends GestoreMessaggiEssentials {

	public GestoreMessaggi(AdventureGUI interfaccia) {
		super(interfaccia);
	}

	@Override
	public void messaggioInizioGioco(String nome, String descrizione) {
		interfaccia.stampaMessaggio(
				"Sei da solo sul bus piu' scassato del mondo.\nLa temperatura e' insopportabile e l'odore e' indescrivibile.\nRiesci a sentire ogni irregolarita' del manto stradale.\n"
						+ "CRASH! Il bus ha urtato qualcosa! Meglio scendere al piu' presto!\n" + "\nLUOGO: " + nome
						+ "\n\n" + descrizione);
	}

	@Override
	public void stampaMessaggio(String messaggio) {
		interfaccia.stampaMessaggio(messaggio);
	}

	@Override
	public void messaggioStanzaInesistente() {
		int rand = (int) (Math.random() * 3);
		switch (rand) {
		case 0:
			interfaccia.stampaMessaggio("A meno che tu non sia un fantasma, non puoi attraversare quella parete!");
			break;
		case 1:
			interfaccia.stampaMessaggio("Se continui di questo passo ti verra'  un trauma cranico!");
			break;
		case 2:
			interfaccia.stampaMessaggio("Se colpisci il muro ancora una volta la gente pensara'  che tu sia pazzo!");

		}
	}

	@Override
	public void messaggioLevaGiaAbbassata() {
		interfaccia.stampaMessaggio("La leva e' gia abbassata");
	}

	@Override
	public void messaggioLevaGiaAlzata() {
		interfaccia.stampaMessaggio("La leva e' gia alzata");
	}


	@Override
	public void messaggioStanzaChiusa() {
		int rand = (int) (Math.random() * 2);
		switch (rand) {
		case 0:
			interfaccia.stampaMessaggio("La porta non si apre, non posso proseguire!");
			break;
		case 1:
			interfaccia.stampaMessaggio("Questa porta e' chiusa, forse dovrei fare altro prima di andare avanti!");
		}
	}

	@Override
	public void messaggioComandoNonRiconosciuto() {
		interfaccia.stampaMessaggio("Comando non riconosciuto");
	}

	@Override
	public void stampaStanza(String nome, String descrizione) {
		interfaccia.stampaMessaggio(nome);
		interfaccia.stampaMessaggio(descrizione);
	}

	@Override
	public void messaggioOggettoNonPresenteInventario() {
		interfaccia.stampaMessaggio("Non hai questo oggetto nel tuo inventario");
	}

	@Override
	public void messaggioInventarioPieno() {
		interfaccia.stampaMessaggio("Il tuo invetario e' pieno");
	}

	@Override
	public void messaggioOggettoNonPresenteStanza() {
		interfaccia.stampaMessaggio("Non vedo questo oggetto da nessuna parte, hai bisogno di un oculista?");
	}

	@Override
	public void messaggioOggettoNonApribile() {
		interfaccia.stampaMessaggio("Spiegami come vorresti aprire questo oggetto");
	}

	@Override
	public void messaggioOggettoNonChiudibile() {
		interfaccia.stampaMessaggio("Oggetto non chiudibile");
	}

	@Override
	public void messaggioOggettoNonPresente() {
		interfaccia.stampaMessaggio("Oggetto non presente ne' nella stanza ne' nell'inventario");
	}

	@Override
	public void stampaDescrizioneOggetto(GenericObject oggetto) {
		interfaccia.stampaMessaggio("-" + oggetto.getDescrizione());
	}

	@Override
	public void stampaPresa(GenericObject oggetto) {
		interfaccia.stampaMessaggio("Hai preso: " + oggetto);
	}

	@Override
	public void stampaLascia(GenericObject oggetto) {
		interfaccia.stampaMessaggio("Hai lasciato: " + oggetto);
	}

	@Override
	public void stampaAperto(GenericObject oggetto) {
		interfaccia.stampaMessaggio("Hai aperto: " + oggetto);
	}

	@Override
	public void stampaChiuso(GenericObject oggetto) {
		interfaccia.stampaMessaggio("Hai chiuso: " + oggetto);
	}

	@Override
	public void messaggioNonCompreso() {
		interfaccia.stampaMessaggio("Non ho capito le tue intenzioni, spiegati meglio!");
	}

	@Override
	public void stampaPresaDa(GenericObject oggetto1, GenericObject oggetto2) {
		interfaccia.stampaMessaggio("Hai preso:  " + oggetto1 + " da " + oggetto2);
	}

	@Override
	public void messaggioOggettoNonReperibile() {
		interfaccia.stampaMessaggio("L'oggetto non contiene cio' che cerchi");
	}

	@Override
	public void messaggioOggettoChiuso(GenericObject oggetto) {
		interfaccia.stampaMessaggio("L'oggetto " + oggetto + " e' chiuso");
	}

	@Override
	public void stampaLasciatoIn(GenericObject oggetto1, GenericObject oggetto2) {
		interfaccia.stampaMessaggio("Hai lasciato l'oggetto " + oggetto1 + " in " + oggetto2);
	}

	@Override
	public void messaggioCombinazioneNonDisponibile(GenericObject oggetto1, GenericObject oggetto2) {
		interfaccia.stampaMessaggio("Non e' possibile combinare " + oggetto1 + " con " + oggetto2);
	}

	@Override
	public void messaggioCombinazioneRiuscita(GenericObject oggetto1, GenericObject oggetto2, GenericObject oggetto3) {
		interfaccia.stampaMessaggio("Hai combinato " + oggetto1 + " con " + oggetto2 + " e ottenuto: " + oggetto3);
	}

	@Override
	public void messaggioOggettoSpecificoNonInInventario(GenericObject oggetto) {
		interfaccia.stampaMessaggio("L'oggetto " + oggetto + " non e' presente nel tuo inventario");
	}

	@Override
	public void messaggioOggettiNonPresenti() {
		interfaccia.stampaMessaggio("Oggetti non presenti nel tuo inventario");
	}

	@Override
	public void messaggioOggettoGiaAperto(GenericObject oggetto) {
		interfaccia.stampaMessaggio("L'oggetto " + oggetto + " e' gia'  aperto");
	}

	@Override
	public void messaggioOggettoGiaChiuso(GenericObject oggetto) {
		interfaccia.stampaMessaggio("L'oggetto " + oggetto + " e' gia'  chiuso");
	}

	@Override
	public void messaggioVitaMassima() {
		interfaccia.stampaMessaggio("Sei gia'  al massimo della vita, conserva le tue cure, nabbo!");
	}

	@Override
	public void messaggioVitaCurataDi(GenericObject oggetto, int heal) {
		interfaccia.stampaMessaggio("Hai usato l'oggetto " + oggetto + " e ti sei curato di " + heal + "!");
	}

	@Override
	public void messaggioVitaCurataMassimo(GenericObject oggetto) {
		interfaccia.stampaMessaggio("Hai usato l'oggetto " + oggetto + " e sei tornato al massimo della vita!");
	}

	@Override
	public void messaggioIntornoATe() {
		interfaccia.stampaMessaggio("Intorno a te vedi: ");
	}

	@Override
	public void messaggioOggettoNonPrendibile(GenericObject oggetto) {
		interfaccia.stampaMessaggio("Non puoi prendere " + oggetto + "!");
	}

	@Override
	public void messaggioOggettoConDescrizione(GenericObject oggetto) {
		interfaccia.stampaMessaggio(oggetto.getNome() + ": " + oggetto.getDescrizione());
	}
}
