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
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
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
	}

	@Override
	public void elaboraAzione(ParserOutput action) {
		Protagonista protagonista = (Protagonista) partita.getProtagonista();
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
				stampa.stampaInventario(oggetto.getNome());
				i++;
			}
			for (int j = i; j < protagonista.getInventario().getMaxSize(); j++) {
				stampa.stampaInventario("");
			}
			stampa.stampaLinea();
			break;
		case PRENDI:
			if (action.getPrimoOggetto() != null && action.getSecondoOggetto() == null) {
				if (partita.getStanzaCorrente().getOggetti().getContainer().contains(action.getPrimoOggetto())) {
					if (protagonista.getInventario().getContainer().size() < 6) {
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
					&& action.getSecondoOggetto() instanceof GenericObjectContainer
					&& preposizioniPrendi.contains(action.getPreposizione())) {
				lasciaOPrendiOggetto(action.getPrimoOggetto(), action.getSecondoOggetto(), protagonista, false);
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case LASCIA:
			if (action.getPrimoOggetto().getNome().equals("caffe")
					&& action.getSecondoOggetto().getNome().equals("macchinetta")) {
				((Caffe) (action.getSecondoOggetto())).addCoffee();
			} else if (action.getPrimoOggetto().getNome().equals("acqua")
					&& action.getSecondoOggetto().getNome().equals("macchinetta")) {
				((Caffe) (action.getSecondoOggetto())).addWater();
			} else if (action.getPrimoOggetto() != null && action.getSecondoOggetto() == null) {
				if (protagonista.getInventario().getContainer().contains(action.getPrimoOggetto())) {
					partita.getStanzaCorrente().addOggetto(action.getPrimoOggetto());
					protagonista.removeOggetto(action.getPrimoOggetto());
					stampa.stampaLascia(action.getPrimoOggetto());
				} else {
					stampa.messaggioOggettoNonPresenteInventario();
				}
			} else if (action.getPrimoOggetto() != null && action.getSecondoOggetto() != null
					&& action.getSecondoOggetto() instanceof GenericObjectContainer
					&& preposizioniLascia.contains(action.getPreposizione())) {
				if (protagonista.isInInventario(action.getPrimoOggetto())) {
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
			if (action.getPrimoOggetto() != null && action.getSecondoOggetto() == null) {
				if (partita.getStanzaCorrente().getOggetti().getContainer().contains(action.getPrimoOggetto())
						|| protagonista.getInventario().getContainer().contains(action.getPrimoOggetto())) {
					stampa.stampaDescrizioneOggetto(action.getPrimoOggetto());
				} else {
					stampa.messaggioOggettoNonPresente();
				}
			} else if (action.getPrimoOggetto() == null && action.getSecondoOggetto() == null) {
				stampa.stampaMessaggio(partita.getStanzaCorrente().getDescrizione());
				ArrayList<GenericObject> loot = partita.getStanzaCorrente().getOggetti().getContainer();
				if (loot.size() != 0) {
					stampa.stampaMessaggio("Intorno a te vedi: ");
					for (GenericObject a : loot) {
						stampa.stampaDescrizioneOggetto(a);
					}
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case COMBINA:
			if (action.getPrimoOggetto() != null && action.getSecondoOggetto() != null
					&& (action.getPreposizione() == null || preposizioniCombina.contains(action.getPreposizione()))) {
				if (protagonista.isInInventario(action.getPrimoOggetto())
						&& protagonista.isInInventario(action.getSecondoOggetto())) {
					GenericObject oggettoCombinato = Combinations.testCombination(action.getPrimoOggetto(),
							action.getSecondoOggetto());
					if (oggettoCombinato != null) {
						protagonista.removeOggetto(action.getPrimoOggetto());
						protagonista.removeOggetto(action.getSecondoOggetto());
						protagonista.addOggetto(oggettoCombinato);
						stampa.messaggioCombinazioneRiuscita(action.getPrimoOggetto(), action.getSecondoOggetto(),
								oggettoCombinato);
					} else {
						stampa.messaggioCombinazioneNonDisponibile(action.getPrimoOggetto(),
								action.getSecondoOggetto());
					}
				} else {
					if (!protagonista.isInInventario(action.getPrimoOggetto())) {
						stampa.messaggioOggettoSpecificoNonInInventario(action.getPrimoOggetto());
					}
					if (!protagonista.isInInventario(action.getSecondoOggetto())) {
						stampa.messaggioOggettoSpecificoNonInInventario(action.getSecondoOggetto());
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
			if (action.getPrimoOggetto() != null && action.getSecondoOggetto() == null) {
				if (protagonista.isInInventario(action.getPrimoOggetto())) {
					usaOggetto(action.getPrimoOggetto());
				} else {
					stampa.messaggioOggettoNonPresenteInventario();
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case MANGIA:
			if (action.getPrimoOggetto() != null && action.getSecondoOggetto() == null) {
				if (protagonista.isInInventario(action.getPrimoOggetto())) {
					mangiaOggettoCura(action.getPrimoOggetto());
				} else {
					stampa.messaggioOggettoNonPresenteInventario();
				}
			} else {
				stampa.messaggioNonCompreso();
			}
			break;
		case BEVI:
			if (action.getPrimoOggetto() != null && action.getSecondoOggetto() == null) {
				if (protagonista.isInInventario(action.getPrimoOggetto())) {
					beviOggettoCura(action.getPrimoOggetto());
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
	public void movimentoNord() {
		if (partita.getStanzaCorrente().getSopra() != null) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getSopra());
			stampa.stampaMessaggio(partita.getStanzaCorrente().getNome());
			if (partita.getStanzaCorrente().getGestoreEvento().getEvento() != null)
				stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento(oggetti));
		} else {
			stampa.messaggioStanzaIrraggiungibile();
		}
	}

	@Override
	public void movimentoSud() {
		if (partita.getStanzaCorrente().getSotto() != null) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getSotto());
			stampa.stampaMessaggio(partita.getStanzaCorrente().getNome());
			if (partita.getStanzaCorrente().getGestoreEvento().getEvento() != null)
				stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento(oggetti));
		} else {
			stampa.messaggioStanzaIrraggiungibile();
		}
	}

	@Override
	public void movimentoEst() {
		if (partita.getStanzaCorrente().getDestra() != null) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getDestra());
			stampa.stampaMessaggio(partita.getStanzaCorrente().getNome());
			if (partita.getStanzaCorrente().getGestoreEvento().getEvento() != null)
				stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento(oggetti));
		} else {
			stampa.messaggioStanzaIrraggiungibile();
		}
	}

	@Override
	public void movimentoOvest() {
		if (partita.getStanzaCorrente().getSinistra() != null) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getSinistra());
			stampa.stampaMessaggio(partita.getStanzaCorrente().getNome());
			if (partita.getStanzaCorrente().getGestoreEvento().getEvento() != null)
				stampa.stampaMessaggio(partita.getStanzaCorrente().getGestoreEvento().iniziaEvento(oggetti));
		} else {
			stampa.messaggioStanzaIrraggiungibile();
		}
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
					stampa.stampaInventario(oggetto.getNome());
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
		case 1:
			mangiaOggettoCura(oggetto);
			break;
		case 2:
			beviOggettoCura(oggetto);
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
