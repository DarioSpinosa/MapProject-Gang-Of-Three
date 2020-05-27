/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Entita.Partita;
import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import General.Eventi.GestoreEventoCaffe;
import General.Eventi.Enigmi.Caffe;
import Parser.ParserOutput;

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
		apri.setAlias(new String[] { "alza" });
		comandi.add(apri);
		Command chiudi = new Command(CommandType.CHIUDI, "chiudi");
		comandi.add(chiudi);
		Command combina = new Command(CommandType.COMBINA, "combina");
		comandi.add(combina);
		Command parla = new Command(CommandType.PARLA, "parla");
		comandi.add(parla);
		Command mangia = new Command(CommandType.MANGIA, "mangia");
		comandi.add(mangia);
		Command bevi = new Command(CommandType.BEVI, "bevi");
		comandi.add(bevi);
		Command metti = new Command(CommandType.METTI, "metti");
		metti.setAlias(new String[] { "versa" });
		comandi.add(metti);
	}

	@Override
	public void elaboraAzione(ParserOutput action) {
		Protagonista protagonista = (Protagonista) partita.getProtagonista();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		ArrayList<GenericObject> oggettiStanza = stanzaCorrente.getOggetti().getContainer();
		GenericObject primo = action.getPrimoOggetto();
		GenericObject secondo = action.getSecondoOggetto();
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
			for (int j = i; j < protagonista.getInventario().getMaxSize(); j++) {
				stampa.stampaMessaggio("- ");
			}
			stampa.stampaLinea();
			break;
		case PRENDI:
			boolean trovato = false;
			if (protagonista.getInventario().getContainer().size() > 6) {
				stampa.messaggioInventarioPieno();
				break;
			}

			if (primo != null && secondo == null) {
				if (!primo.isPrendibile()) {
					stampa.messaggioOggettoNonPresenteStanza();
					break;
				}

				if (partita.getStanzaCorrente().getOggetti().contains(primo)) {
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

				if (trovato == false) {
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
		case LASCIA:
			if (primo != null && secondo == null) {
				if (protagonista.getInventario().getContainer().contains(primo)) {
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
			apriOChiudiOggetto(primo, secondo, protagonista, true);
			break;
		case CHIUDI:
			apriOChiudiOggetto(primo, secondo, protagonista, false);
			break;
		case GUARDA:
			boolean trovato2 = false;

			if (primo != null && secondo == null) {
				if (oggettiStanza.contains(primo) || protagonista.getInventario().getContainer().contains(primo)) {
					stampa.stampaDescrizioneOggetto(primo);
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
				if (loot.size() != 0) {
					stampa.messaggioIntornoATe();
					for (GenericObject a : loot) {
						stampa.stampaDescrizioneOggetto(a);
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
				Npc npc = (Npc) partita.getStanzaCorrente().getPersonaggio(action.getPersonaggio());
				stampa.stampaMessaggio(npc.getDialogo());
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case USA:
			if (primo.getNome().equals("macchinetta")) {
				stampa.stampaMessaggio(((Caffe) (primo)).switchOn());
				if (partita.getStanzaCorrente().getGestoreEvento() != null && ((Caffe) primo).getCompletato()) {
					protagonista.getInventario().addToContainer(((Caffe) (primo)).getCoffee());
					partita.getOggetti().add(((Caffe) (primo)).getCoffee());
					((GestoreEventoCaffe) partita.getStanzaCorrente().getGestoreEvento()).terminaEvento(oggetti);
				}
			} else if (primo != null && secondo == null) {
				if (protagonista.isInInventario(primo)) {
					usaOggetto(primo);
				} else {
					stampa.messaggioOggettoNonPresenteInventario();
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case MANGIA:
			if (primo != null && secondo == null) {
				if (protagonista.isInInventario(primo)) {
					mangiaOggettoCura(primo);
				} else {
					stampa.messaggioOggettoNonPresenteInventario();
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case BEVI:
			if (primo != null && secondo == null) {
				if (protagonista.isInInventario(primo)) {
					beviOggettoCura(primo);
				} else {
					stampa.messaggioOggettoNonPresenteInventario();
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;

		case METTI:
			if (primo.getNome().equals("caffe") && secondo.getNome().equals("macchinetta")) {
				stampa.stampaMessaggio(((Caffe) (secondo)).addCoffee());
				protagonista.getInventario().removeFromContainer(primo);
			} else if (primo.getNome().equals("acqua") && secondo.getNome().equals("macchinetta")) {
				stampa.stampaMessaggio(((Caffe) (secondo)).addWater());
				protagonista.getInventario().removeFromContainer(primo);
			} else
				stampa.messaggioOggettoNonPresenteInventario();
		}
	}


	@Override
	public void movimentoNord() {
		if (partita.getStanzaCorrente().getSopra() != null) {
			if(partita.getStanzaCorrente().getSopra().getAccessibile()) {
				partita.setStanzaCorrente(partita.getStanzaCorrente().getSopra());
				stampa.stampaMessaggio("Luogo: " + partita.getStanzaCorrente().getNome());
				if (partita.getStanzaCorrente().getGestoreEvento() != null)
					stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento(oggetti));
			}else
				stampa.messaggioStanzaChiusa();
		} else
			stampa.messaggioStanzaInesistente();

	}

	@Override
	public void movimentoSud() {
		if (partita.getStanzaCorrente().getSotto() != null) {
			if(partita.getStanzaCorrente().getSotto().getAccessibile()) {
				partita.setStanzaCorrente(partita.getStanzaCorrente().getSotto());
				stampa.stampaMessaggio("Luogo: " + partita.getStanzaCorrente().getNome());
				if (partita.getStanzaCorrente().getGestoreEvento() != null)
					stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento(oggetti));
			}else
				stampa.messaggioStanzaChiusa();
		} else
			stampa.messaggioStanzaInesistente();

	}

	@Override
	public void movimentoEst() {
		if (partita.getStanzaCorrente().getDestra() != null) {
			if(partita.getStanzaCorrente().getDestra().getAccessibile()) {
				partita.setStanzaCorrente(partita.getStanzaCorrente().getDestra());
				stampa.stampaMessaggio("Luogo: " + partita.getStanzaCorrente().getNome());
				if (partita.getStanzaCorrente().getGestoreEvento() != null)
					stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento(oggetti));
			}else
				stampa.messaggioStanzaChiusa();
		} else
			stampa.messaggioStanzaInesistente();

	}

	@Override
	public void movimentoOvest() {
		if (partita.getStanzaCorrente().getSinistra() != null) {
			if(partita.getStanzaCorrente().getSinistra().getAccessibile()) {
				partita.setStanzaCorrente(partita.getStanzaCorrente().getSinistra());
				stampa.stampaMessaggio("Luogo: " + partita.getStanzaCorrente().getNome());
				if (partita.getStanzaCorrente().getGestoreEvento() != null)
					stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento(oggetti));
			}else
				stampa.messaggioStanzaChiusa();
		} else
			stampa.messaggioStanzaInesistente();

	}

	private void lasciaOPrendiOggetto(GenericObject oggetto1, GenericObject oggetto2, Protagonista protagonista,
			boolean lasciare) {
		if (partita.getStanzaCorrente().getOggetti().getContainer().contains(oggetto2)) {
			GenericObjectContainer oggettoConStanza = (GenericObjectContainer) partita.getStanzaCorrente()
					.getOggetto(oggetto2);
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
			GenericObjectContainer oggettoConInv = (GenericObjectContainer) protagonista.getOggetto(oggetto2);
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

	private void apriOChiudiOggetto(GenericObject oggetto1, GenericObject oggetto2, Protagonista protagonista,
			boolean aprire) {
		if (oggetto1 != null && oggetto2 == null) {
			if (oggetto1 instanceof GenericObjectContainer) {
				if (partita.getStanzaCorrente().getOggetti().getContainer().contains(oggetto1)) {
					if (aprire) {
						if (!((GenericObjectContainer) partita.getStanzaCorrente().getOggetto(oggetto1)).isOpened()) {
							((GenericObjectContainer) partita.getStanzaCorrente().getOggetto(oggetto1)).open();
							stampa.stampaAperto(oggetto1);
						} else {
							stampa.messaggioOggettoGiaAperto(oggetto1);
						}
					} else {
						if (((GenericObjectContainer) partita.getStanzaCorrente().getOggetto(oggetto1)).isOpened()) {
							((GenericObjectContainer) partita.getStanzaCorrente().getOggetto(oggetto1)).close();
							stampa.stampaChiuso(oggetto1);
						} else {
							stampa.messaggioOggettoGiaChiuso(oggetto1);
						}
					}
				} else if (protagonista.isInInventario(oggetto1)) {
					if (aprire) {
						if (!((GenericObjectContainer) protagonista.getOggetto(oggetto1)).isOpened()) {
							((GenericObjectContainer) protagonista.getOggetto(oggetto1)).open();
							stampa.stampaAperto(oggetto1);
						} else {
							stampa.messaggioOggettoGiaAperto(oggetto1);
						}
					} else {
						if (((GenericObjectContainer) protagonista.getOggetto(oggetto1)).isOpened()) {
							((GenericObjectContainer) protagonista.getOggetto(oggetto1)).close();
							stampa.stampaChiuso(oggetto1);
						} else {
							stampa.messaggioOggettoGiaChiuso(oggetto1);
						}
					}
				} else {
					stampa.messaggioOggettoNonPresente();
				}
				for (GenericObject oggetto : ((GenericObjectContainer) oggetto1).getContainer()) { // TODO da rimuovere
					stampa.stampaInventario(oggetto);
				}
			} else {
				if (aprire) {
					stampa.messaggioOggettoNonApribile();
				} else {
					stampa.messaggioOggettoNonChiudibile();
				}
			}
		} else {
			stampa.messaggioNonCompreso();
		}
	}

	private void usaOggetto(GenericObject oggetto) {
		switch (oggetto.getCategory()) {
		case EATABLE:
			mangiaOggettoCura(oggetto);
			break;
		case DRINKABLE:
			beviOggettoCura(oggetto);
			break;
		default:
			stampa.messaggioNonCompreso();
			break;
		}
	}

	private void mangiaOggettoCura(GenericObject oggetto) {
		int healthPoints = partita.getProtagonista().getHealthPoints();
		switch (oggetto.getNome()) {
		case "torta":
			cura(oggetto, healthPoints, 1);
			break;
		case "pizza":
			cura(oggetto, healthPoints, 2);
			break;
		default:
			stampa.messaggioNonCompreso();
			break;
		}
	}

	private void beviOggettoCura(GenericObject oggetto) {
		int healthPoints = partita.getProtagonista().getHealthPoints();
		switch (oggetto.getNome()) {
		case "birra":
			cura(oggetto, healthPoints, 1);
			break;
		default:
			stampa.messaggioNonCompreso();
		}
	}

	private void cura(GenericObject oggetto, int healthPoints, int heal) {
		if (healthPoints == 3) {
			stampa.messaggioVitaMassima();
		} else if ((healthPoints + heal) >= 3) {
			partita.getProtagonista().setHealth(3);
			healthPoints = 3;
			stampa.stampaVita(healthPoints);
			stampa.messaggioVitaCurataMassimo(oggetto);
			if (oggetto.isConsumabile()) {
				((Protagonista) partita.getProtagonista()).removeOggetto(oggetto);
			}
		} else {
			((Protagonista) partita.getProtagonista()).heal(heal);
			healthPoints = healthPoints + heal;
			stampa.stampaVita(healthPoints);
			stampa.messaggioVitaCurataDi(oggetto, heal);
			if (oggetto.isConsumabile()) {
				((Protagonista) partita.getProtagonista()).removeOggetto(oggetto);
			}
		}
	}

}
