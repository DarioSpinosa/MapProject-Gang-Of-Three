/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Entita.Partita;
import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import General.Eventi.GenericGestoreEvento;
import General.Eventi.GestoreEventoCaffe;
import General.Eventi.GestoreEventoPacco;
import General.Eventi.GestoreEventoPannello;
import General.Eventi.GestoreEventoRivista;
import General.Eventi.Enigmi.Caffe;
import General.Eventi.Enigmi.Pannello;
import Parser.ParserOutput;
import Resources.Dialogs;

/**
 *
 * @author Elio
 */
public class GestoreAzioni extends GestoreAzioniEssentials {

	private final Set<String> preposizioniPrendi = new HashSet<>();
	private final Set<String> preposizioniLascia = new HashSet<>();
	private final Set<String> preposizioniCombina = new HashSet<>();
	private final Set<String> preposizioniParla = new HashSet<>();

	public GestoreAzioni(Partita partita, GestoreMessaggiEssentials stampa) {
		super(partita, stampa);
		preposizioniPrendi.add("dal");
		preposizioniPrendi.add("da");
		preposizioniPrendi.add("dalla");
		preposizioniLascia.add("nel");
		preposizioniLascia.add("in");
		preposizioniLascia.add("nella");
		preposizioniCombina.add("con");
		preposizioniCombina.add("e");
		preposizioniParla.add("con");
		preposizioniParla.add("all");
		preposizioniParla.add("a");
		preposizioniParla.add("ad");
		Command nord = new Command(CommandType.NORD, "nord");
		nord.setAlias(new String[] { "n", "Nord" });
		comandi.add(nord);
		Command ovest = new Command(CommandType.OVEST, "ovest");
		ovest.setAlias(new String[] { "o", "Ovest" });
		comandi.add(ovest);
		Command sud = new Command(CommandType.SUD, "sud");
		sud.setAlias(new String[] { "s", "Sud" });
		comandi.add(sud);
		Command est = new Command(CommandType.EST, "est");
		est.setAlias(new String[] { "e", "Est" });
		comandi.add(est);
		Command inventario = new Command(CommandType.INVENTARIO, "inventario");
		comandi.add(inventario);
		Command guarda = new Command(CommandType.GUARDA, "guarda");
		guarda.setAlias(new String[] { "osserva", "analizza", "ispeziona" });
		comandi.add(guarda);
		Command prendi = new Command(CommandType.PRENDI, "prendi");
		prendi.setAlias(new String[] { "afferra" });
		comandi.add(prendi);
		Command usa = new Command(CommandType.USA, "usa");
		usa.setAlias(new String[] { "utilizza" });
		comandi.add(usa);
		Command lascia = new Command(CommandType.LASCIA, "lascia");
		lascia.setAlias(new String[] { "poggia", "posa" });
		comandi.add(lascia);
		Command apri = new Command(CommandType.APRI, "apri");
		apri.setAlias(new String[] {});
		comandi.add(apri);
		Command chiudi = new Command(CommandType.CHIUDI, "chiudi");
		comandi.add(chiudi);
		Command combina = new Command(CommandType.COMBINA, "combina");
		comandi.add(combina);
		Command parla = new Command(CommandType.PARLA, "parla");
		comandi.add(parla);
		Command metti = new Command(CommandType.METTI, "metti");
		metti.setAlias(new String[] { "versa" });
		comandi.add(metti);
		Command abbassa = new Command(CommandType.ABBASSA, "abbassa");
		abbassa.setAlias(new String[] {});
		comandi.add(abbassa);
		Command alza = new Command(CommandType.ALZA, "alza");
		alza.setAlias(new String[] {});
		comandi.add(alza);
		Command dai = new Command(CommandType.DAI, "dai");
		dai.setAlias(new String[] { "consegna" });
		comandi.add(dai);
	}

	@Override
	public void elaboraAzione(ParserOutput action) {
		Protagonista protagonista = (Protagonista) partita.getProtagonista();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		ArrayList<GenericObject> oggettiStanza = stanzaCorrente.getOggetti().getContainer();
		GenericObject primo = action.getPrimoOggetto();
		GenericObject secondo = action.getSecondoOggetto();
		GenericGestoreEvento gestore = partita.getStanzaCorrente().getGestoreEvento();
		switch (action.getComando().getCommandType()) {
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
			for (GenericObject oggetto : protagonista.getInventario().getContainer()) {
				stampa.stampaInventario(oggetto);
				i++;
			}
			for (int j = i; j < protagonista.getInventoryMaxSize(); j++) {
				stampa.stampaMessaggio("- ");
			}
			stampa.stampaLinea();
			break;
		case PRENDI:
			boolean trovato = false;
			if (protagonista.getActualInventorySize() > 6) {
				stampa.messaggioInventarioPieno();
				break;
			}

			if (primo != null && secondo == null) {
				if (!primo.isPrendibile()) {
					stampa.messaggioOggettoNonPrendibile(primo);
					break;
				}

				if (partita.getStanzaCorrente().isInRoom(primo)) {
					partita.getStanzaCorrente().removeOggetto(primo);
					protagonista.addOggetto(primo);
					stampa.stampaPresa(primo);
					trovato = true;
				} else {
					for (i = 0; i < oggetti.size(); i++) {
						if (oggetti.get(i) instanceof GenericObjectContainer) {
							if (((GenericObjectContainer) (oggetti.get(i))).contains(primo)
									&& ((GenericObjectContainer) (oggetti.get(i))).isOpened()) {
								((GenericObjectContainer) (oggetti.get(i))).removeFromContainer(primo);
								protagonista.addOggetto(primo);
								stampa.stampaPresa(primo);
								trovato = true;
							}
						}
					}
				}

				if (!trovato) {
					stampa.messaggioOggettoNonPresenteStanza();
				}

			} else if (primo != null && secondo != null) {
				if (!primo.isPrendibile()) {
					stampa.messaggioOggettoNonPresenteStanza();
					break;
				}
				if (secondo instanceof GenericObjectContainer
						&& preposizioniPrendi.contains(action.getPreposizione())) {
					lasciaOPrendiOggetto(primo, secondo, protagonista, false);
				} else {
					stampa.messaggioOggettoNonPresenteStanza();
				}
			} else {
				stampa.messaggioNonCompreso();
			}

			break;
		case DAI:
			if (gestore instanceof GestoreEventoPacco && primo != null) {
				if (primo.equals(gestore.getEvento().getEnigma())
						&& protagonista.isInInventario(gestore.getEvento().getEnigma())) {
					gestore.terminaEvento(protagonista);
					stampa.stampaMessaggio(Dialogs.BAKER_C);
				} else {
					stampa.stampaMessaggio(Dialogs.BAKER_D);
				}

			}

			if (gestore instanceof GestoreEventoRivista
					&& protagonista.isInInventario(gestore.getEvento().getEnigma()) && primo != null
					&& primo.equals(gestore.getEvento().getEnigma())) {
				gestore.terminaEvento(protagonista);
				stampa.stampaMessaggio(Dialogs.JANITOR_EVENT);
			}
			break;
		case LASCIA:
			if (primo != null && secondo == null) {
				if (protagonista.isInInventario(primo)) {
					partita.getStanzaCorrente().addOggetto(primo);
					protagonista.removeOggetto(primo);
					stampa.stampaLascia(primo);
				} else {
					stampa.messaggioOggettoNonPresenteInventario();
				}
			} else if (primo != null && secondo != null && secondo instanceof GenericObjectContainer
					&& preposizioniLascia.contains(action.getPreposizione())) {
				if (protagonista.isInInventario(primo)) {
					lasciaOPrendiOggetto(primo, secondo, protagonista, true);
				} else {
					stampa.messaggioOggettoNonPresenteInventario();
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case APRI:
			apriOChiudiOggetto(primo, secondo, protagonista, action.getPreposizione(), true);
			break;
		case CHIUDI:
			apriOChiudiOggetto(primo, secondo, protagonista, action.getPreposizione(), false);
			break;
		case GUARDA:
			boolean trovato2 = false;

			if (primo != null && secondo == null) {
				if (oggettiStanza.contains(primo) || protagonista.isInInventario(primo)) {
					stampa.stampaDescrizioneOggetto(primo);
                                        if(primo instanceof GenericObjectContainer && ((GenericObjectContainer)primo).isOpened()){
                                            stampa.stampaMessaggio(((GenericObjectContainer) primo).getContainer().toString());
                                        }
					trovato2 = true;
				} else {
					for (i = 0; i < oggettiStanza.size(); i++) {
						if (oggettiStanza.get(i) instanceof GenericObjectContainer) {
							if (((GenericObjectContainer) (oggettiStanza.get(i))).contains(primo)
									&& ((GenericObjectContainer) (oggettiStanza.get(i))).isOpened()) {
								stampa.stampaDescrizioneOggetto(primo);
								trovato2 = true;
							}
						}
					}
				}

				if (trovato2 == false) {
					stampa.messaggioOggettoNonPresente();
				}

			} else if (primo == null && secondo == null) {
				stampa.stampaMessaggio("\n" + partita.getStanzaCorrente().getDescrizione());
				ArrayList<GenericObject> loot = partita.getStanzaCorrente().getOggetti().getContainer();
				if (!loot.isEmpty()) {
					stampa.messaggioIntornoATe();
					for (GenericObject a : loot) {
						stampa.messaggioOggettoConDescrizione(a);
					}
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case COMBINA:
			if (primo != null && secondo != null
			&& (action.getPreposizione() == null || preposizioniCombina.contains(action.getPreposizione()))) {
				if (protagonista.isInInventario(primo) && protagonista.isInInventario(secondo)) {
					GenericObject oggettoCombinato = Combinations.testCombination(primo, secondo);
					if (oggettoCombinato != null) {
						protagonista.removeOggetto(primo);
						protagonista.removeOggetto(secondo);
						protagonista.addOggetto(oggettoCombinato);
						stampa.messaggioCombinazioneRiuscita(primo, secondo, oggettoCombinato);
					} else {
						stampa.messaggioCombinazioneNonDisponibile(primo, secondo);
					}
				} else {
					if (!protagonista.isInInventario(primo)) {
						stampa.messaggioOggettoSpecificoNonInInventario(primo);
					}
					if (!protagonista.isInInventario(secondo)) {
						stampa.messaggioOggettoSpecificoNonInInventario(secondo);
					}
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case PARLA:
			if (action.getPersonaggio() != null
			&& (action.getPreposizione() == null || preposizioniParla.contains(action.getPreposizione()))
			&& action.getPersonaggio() instanceof Npc) {
				if(action.getPersonaggio().getNome().getName().equals("Morgan")) {
					Desktop d = Desktop.getDesktop();
					try {
						d.browse(new URI("https://www.youtube.com/watch?v=otbUSPQiet8"));
					} catch (IOException | URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Npc npc = (Npc) partita.getStanzaCorrente().getPersonaggio(action.getPersonaggio());
				stampa.stampaMessaggio("\n" + npc.getNome().getName() + ": " + npc.getDialogo());

			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case USA:
			if (gestore != null && gestore instanceof GestoreEventoCaffe && primo.getNome().equals("macchinetta")) {
				stampa.stampaMessaggio(((Caffe) (primo)).switchOn());
				gestore.terminaEvento(protagonista);

			} else if (gestore != null && gestore instanceof GestoreEventoPannello
					&& primo.getNome().equals("pannello")) {
				stampa.stampaMessaggio(((Pannello) (primo)).switchOn());
				gestore.terminaEvento(protagonista);

			} else if (primo != null && primo.getNome().equals("grimaldello") && stanzaCorrente.getOggetto("automobile") != null) {
				protagonista.removeOggetto(primo);
				((GenericObjectContainer)stanzaCorrente.getOggetto("automobile")).setBloccato(false);
				((GenericObjectContainer)stanzaCorrente.getOggetto("automobile")).open();
				stampa.stampaMessaggio("La serratura si e' aperta");
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case METTI:
			if (gestore instanceof GestoreEventoCaffe && primo != null && secondo != null) {
				if (primo.getNome().equals("caffe") && secondo.getNome().equals("macchinetta") && protagonista.isInInventario(primo)) {
					stampa.stampaMessaggio(((Caffe) (secondo)).addCoffee());
					protagonista.getInventario().removeFromContainer(primo);
				} else if (primo.getNome().equals("acqua") && secondo.getNome().equals("macchinetta") && protagonista.isInInventario(primo)) {
					stampa.stampaMessaggio(((Caffe) (secondo)).addWater());
					protagonista.getInventario().removeFromContainer(primo);
				} else
					stampa.messaggioOggettoNonPresenteInventario();
			} else
				stampa.messaggioNonCompreso();
			break;
		case ABBASSA:
			Pannello pannello = ((Pannello) gestore.getEvento().getEnigma());
			if (gestore != null && gestore instanceof GestoreEventoPannello && primo != null && primo.getNome().equals("leva")
					&& action.getPrimoAggettivo() != null) {
				switch (action.getPrimoAggettivo()) {
				case "rossa":
					if(!pannello.getArray(0))
						pannello.switchFirstToggle();
					else
						stampa.messaggioLevaGiaAbbassata();
					break;
				case "gialla":
					if(!pannello.getArray(1))
						pannello.switchSecondToggle();
					else
						stampa.messaggioLevaGiaAbbassata();
					break;
				case "verde":
					if(!pannello.getArray(2))
						pannello.switchThirdToggle();
					else
						stampa.messaggioLevaGiaAbbassata();
					break;
				case "blu":
					if(!pannello.getArray(3))
						pannello.switchFourthToggle();
					else
						stampa.messaggioLevaGiaAbbassata();
					break;
				case "nera":
					if(!pannello.getArray(4))
						pannello.switchFifthToggle();
					else
						stampa.messaggioLevaGiaAbbassata();
				}
				stampa.stampaMessaggio(pannello.showVoltage());
			} else
				stampa.messaggioNonCompreso();
			break;

		case ALZA:
			Pannello pannello2 = ((Pannello) gestore.getEvento().getEnigma());
			if (gestore != null && gestore instanceof GestoreEventoPannello && primo != null && primo.getNome().equals("leva")
					&& action.getPrimoAggettivo() != null) {
				switch (action.getPrimoAggettivo()) {
				case "rossa":
					if(pannello2.getArray(0))
						pannello2.switchFirstToggle();
					else
						stampa.messaggioLevaGiaAlzata();
					break;
				case "gialla":
					if(pannello2.getArray(1))
						pannello2.switchSecondToggle();
					else
						stampa.messaggioLevaGiaAlzata();
					break;
				case "verde":
					if(pannello2.getArray(2))
						pannello2.switchThirdToggle();
					else
						stampa.messaggioLevaGiaAlzata();
					break;
				case "blu":
					if(pannello2.getArray(3))
						pannello2.switchFourthToggle();
					else
						stampa.messaggioLevaGiaAlzata();
					break;
				case "nera":
					if(pannello2.getArray(4))
						pannello2.switchFifthToggle();
					else
						stampa.messaggioLevaGiaAlzata();
				}
				stampa.stampaMessaggio(pannello2.showVoltage());
			} else
				stampa.messaggioNonCompreso();
		}

	}

	@Override
	public void movimentoNord() {
		if (partita.getStanzaCorrente().getSopra() != null) {
			if (partita.getStanzaCorrente().getSopra().getAccessibile()) {
				partita.setStanzaCorrente(partita.getStanzaCorrente().getSopra());
				stampa.stampaMessaggio("\nLuogo: " + partita.getStanzaCorrente().getNome().toUpperCase());
				if (partita.getStanzaCorrente().getGestoreEvento() != null
						&& !partita.getStanzaCorrente().getGestoreEvento().getIniziato())
					stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento());
			} else
				stampa.messaggioStanzaChiusa();
		} else
			stampa.messaggioStanzaInesistente();

	}

	@Override
	public void movimentoSud() {
		if (partita.getStanzaCorrente().getSotto() != null) {
			if (partita.getStanzaCorrente().getSotto().getAccessibile()) {
				partita.setStanzaCorrente(partita.getStanzaCorrente().getSotto());
				stampa.stampaMessaggio("\nLuogo: " + partita.getStanzaCorrente().getNome().toUpperCase());
				if (partita.getStanzaCorrente().getGestoreEvento() != null
						&& !partita.getStanzaCorrente().getGestoreEvento().getIniziato())
					stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento());
			} else
				stampa.messaggioStanzaChiusa();
		} else
			stampa.messaggioStanzaInesistente();

	}

	@Override
	public void movimentoEst() {
		if (partita.getStanzaCorrente().getDestra() != null) {
			if (partita.getStanzaCorrente().getDestra().getAccessibile()) {
				partita.setStanzaCorrente(partita.getStanzaCorrente().getDestra());
				stampa.stampaMessaggio("\nLuogo: " + partita.getStanzaCorrente().getNome().toUpperCase());
				if (partita.getStanzaCorrente().getGestoreEvento() != null
						&& !partita.getStanzaCorrente().getGestoreEvento().getIniziato())
					stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento());
			} else {
				stampa.messaggioStanzaChiusa();

			}
		} else
			stampa.messaggioStanzaInesistente();

	}

	@Override
	public void movimentoOvest() {
		if (partita.getStanzaCorrente().getSinistra() != null) {
			if (partita.getStanzaCorrente().getSinistra().getAccessibile()) {
				partita.setStanzaCorrente(partita.getStanzaCorrente().getSinistra());
				stampa.stampaMessaggio("\nLuogo: " + partita.getStanzaCorrente().getNome().toUpperCase());
				if (partita.getStanzaCorrente().getGestoreEvento() != null
						&& !partita.getStanzaCorrente().getGestoreEvento().getIniziato())
					stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento());
			} else
				stampa.messaggioStanzaChiusa();
		} else
			stampa.messaggioStanzaInesistente();

	}

	private void lasciaOPrendiOggetto(GenericObject oggetto1, GenericObject oggetto2, Protagonista protagonista,
			boolean lasciare) {
		if (partita.getStanzaCorrente().isInRoom(oggetto2)) {
			GenericObjectContainer oggettoConStanza = (GenericObjectContainer)oggetto2;
			if (oggettoConStanza.isOpened()) {
				if (lasciare) {
					protagonista.removeOggetto(oggetto1);
					oggettoConStanza.addToContainer(oggetto1);
					stampa.stampaLasciatoIn(oggetto1, oggetto2);
				} else {
					if (oggettoConStanza.contains(oggetto1)) {
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
		} else if (protagonista.isInInventario(oggetto2)) {
			GenericObjectContainer oggettoConInv = (GenericObjectContainer)oggetto2;
			if (oggettoConInv.isOpened()) {
				if (lasciare) {
					protagonista.removeOggetto(oggetto1);
					oggettoConInv.addToContainer(oggetto1);
					stampa.stampaLasciatoIn(oggetto1, oggetto2);
				} else {
					if (oggettoConInv.contains(oggetto1)) {
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

	private void apriOChiudiOggetto(GenericObject oggetto1, GenericObject oggetto2, Protagonista protagonista, String preposizione, boolean aprire) {
            GenericObjectContainer oggettoContenitore = (GenericObjectContainer)oggetto1;
		if (oggettoContenitore != null && oggetto2 == null) {
			if (oggettoContenitore instanceof GenericObjectContainer) {
				if (partita.getStanzaCorrente().isInRoom(oggettoContenitore)) {
					if (aprire) {
						if(!oggettoContenitore.getBloccato()) {
							if (!oggettoContenitore.isOpened()) {
								oggettoContenitore.open();
								stampa.stampaAperto(oggetto1);
							} else {
								stampa.messaggioOggettoGiaAperto(oggetto1);
							}
						}else
							stampa.stampaMessaggio("Oggetto chiuso, ti serve qualcosa per aprirlo");

					} else {
						if (oggettoContenitore.isOpened()) {
							oggettoContenitore.close();
							stampa.stampaChiuso(oggetto1);
						} else
							stampa.messaggioOggettoGiaChiuso(oggetto1);

					}
				} else if (protagonista.isInInventario(oggettoContenitore)) {
					if (aprire) {
						if(!oggettoContenitore.getBloccato()) {
							if (!oggettoContenitore.isOpened()) {
								oggettoContenitore.open();
								stampa.stampaAperto(oggetto1);
							} else
								stampa.messaggioOggettoGiaAperto(oggetto1);

						}else
							stampa.stampaMessaggio("Oggetto chiuso, ti serve qualcosa per aprirlo");
					} else {
						if (oggettoContenitore.isOpened()) {
							oggettoContenitore.close();
							stampa.stampaChiuso(oggetto1);
						} else {
							stampa.messaggioOggettoGiaChiuso(oggetto1);
						}
					}
				} else {
					stampa.messaggioOggettoNonPresente();
				}
			} else {
				if (aprire) {
					stampa.messaggioOggettoNonApribile();
				} else {
					stampa.messaggioOggettoNonChiudibile();
				}
			}
		}else if(oggettoContenitore != null && oggetto2 != null && (preposizione == null || preposizioniCombina.contains(preposizione))) {
			if (oggettoContenitore instanceof GenericObjectContainer) {
				if ((partita.getStanzaCorrente().isInRoom(oggettoContenitore) || protagonista.isInInventario(oggettoContenitore))
						&& oggettoContenitore.getNome().equals("pacco")) {
					if(oggetto2.getNome().equals("taglierino")) {
						protagonista.removeOggetto(oggetto2);
						oggettoContenitore.setBloccato(false);
						stampa.stampaMessaggio("Hai aperto il pacco, dentro c'è un cd");
						oggettoContenitore.open();
					}
					else
						stampa.stampaMessaggio("Non riesco ad aprirlo con questo oggetto");
				}
			} else {
				stampa.messaggioNonCompreso();
			}
		} else {
                    stampa.messaggioNonCompreso();
                }
	}

	private void usaOggetto(GenericObject oggetto) {
		switch (oggetto.getCategory()) {
		case EATABLE:
			break;
		case DRINKABLE:
			break;
		default:
			stampa.messaggioNonCompreso();
			break;
		}
	}
}
