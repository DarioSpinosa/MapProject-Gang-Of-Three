/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita;

import java.util.ArrayList;

import Entita.Characters.Npc;
import Entita.Characters.Personaggio;
import Entita.Characters.Protagonista;
import General.Combinations;
import General.GenericObject;
import General.GenericObjectContainer;
import General.GestoreAlias;
import General.Name;
import General.ObjectType;
import General.Eventi.Evento;
import General.Eventi.GestoreEventoCaffe;
import General.Eventi.GestoreEventoPacco;
import General.Eventi.GestoreEventoPannello;
import General.Eventi.GestoreEventoRivista;
import General.Eventi.Enigmi.Caffe;
import General.Eventi.Enigmi.Pannello;
import Parser.WordType;
import Resources.Descriptions;
import Resources.Dialogs;
import Resources.Places;

public class Partita {

	private Stanza stanzaCorrente;
	private Personaggio protagonista;
	private ArrayList<GenericObject> oggetti = new ArrayList<>();

	public ArrayList<GenericObject> getOggetti() {
		return oggetti;
	}

	public Partita() {
		protagonista = new Protagonista(new Name("Pippo", WordType.NOME_PROPRIO));
		//Mappa
		Stanza strada1 = new Stanza("Via Amendola, primo settore", Places.SOUTH1);
		Stanza strada2 = new Stanza("Via Amendola, secondo settore", Places.SOUTH2);
		Stanza strada3 = new Stanza("Via Amendola, terzo settore", Places.SOUTH3);
		Stanza strada4 = new Stanza("Strada Nord 1", Places.NORTH1);
		Stanza strada5 = new Stanza("Strada Nord 2", Places.NORTH2);
		Stanza strada6 = new Stanza("Strada Nord 3", Places.NORTH3);
		Stanza incrocio = new Stanza("Incrocio", Places.CROSS);
		Stanza esecutivo = new Stanza("Executive Center, Uffici", Places.EXECUTIVE);
		Stanza pizzeria1 = new Stanza("Pizzeria Esterno", Places.PIZZA_EXT);
		Stanza pizzeria2 = new Stanza("Pizzeria Interno", Places.PIZZA_INT);
		Stanza stradaChiusa = new Stanza("Strada Chiusa", Places.WEST);
		Stanza fisica1 = new Stanza("Atrio di Fisica", Places.PHYSICS_NOCURR);
		Stanza fisica2 = new Stanza("Sala raggi cosmici", "");
		Stanza fisica3 = new Stanza("Ufficio", "Sala del trono di Volpe");
		Stanza chimica1 = new Stanza("Bar di Chimica", Places.CHEMISTRY);
		Stanza informatica1 = new Stanza("Atrio DIB", Places.DIB);
		fisica2.setAccessibile(false);
		pizzeria2.setAccessibile(false);
		strada1.setSopra(strada2);
		esecutivo.setSinistra(strada2);
		strada2.setDestra(esecutivo).setSopra(strada3).setSotto(strada1);
		pizzeria1.setSinistra(strada3).setDestra(pizzeria2);
		strada3.setSotto(strada2).setSopra(incrocio).setDestra(pizzeria1);
		pizzeria2.setSinistra(pizzeria1);
		incrocio.setSotto(strada3).setSinistra(stradaChiusa).setSopra(strada4);
		stradaChiusa.setDestra(incrocio);
		strada4.setSotto(incrocio).setSopra(strada5).setDestra(chimica1);
		strada5.setSotto(strada4).setSinistra(fisica1).setSopra(strada6);
		fisica1.setSinistra(fisica2).setDestra(strada5);
		fisica2.setSopra(fisica3).setDestra(fisica1);
		fisica3.setSotto(fisica2);
		chimica1.setSinistra(strada4);
		strada6.setSotto(strada5).setDestra(informatica1);
		informatica1.setSinistra(strada6);
		//NPC
		Name nomeAutista = new Name("Mimmo", WordType.NOME_PROPRIO);
		nomeAutista.setPreposizioni(new String[] { "a", "con" });
		Name aliasAutista = new Name("autista", WordType.NOME);
		aliasAutista.setArticoli(new String[] { "l" });
		aliasAutista.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio autista = new Npc(nomeAutista, Dialogs.BUSDRIVER);
		autista.setAlias(new Name[] { aliasAutista });
		strada1.addPersonaggio(autista);

		Name nomeBarista = new Name("Cannavacciuolo", WordType.NOME_PROPRIO);
		nomeBarista.setPreposizioni(new String[] { "a", "con" });
		Name aliasBarista = new Name("antonino", WordType.NOME);
		aliasBarista.setArticoli(new String[] { "il" });
		aliasBarista.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio barista = new Npc(nomeBarista, Dialogs.CANNAVACCIUOLO_A);
		barista.setAlias(new Name[] { aliasBarista });
		chimica1.addPersonaggio(barista);

		Name nomePizzaiolo = new Name("Almerino", WordType.NOME_PROPRIO);
		nomePizzaiolo.setPreposizioni(new String[] { "a", "con" });
		Name aliasPizzaiolo = new Name("pizzaiolo", WordType.NOME);
		aliasPizzaiolo.setArticoli(new String[] { "il" });
		aliasPizzaiolo.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio pizzaiolo = new Npc(nomePizzaiolo, Dialogs.BAKER_A);
		pizzaiolo.setAlias(new Name[] { aliasPizzaiolo });
		pizzeria1.addPersonaggio(pizzaiolo);

		Name nomeBidello = new Name("Bidello", WordType.NOME_PROPRIO);
		nomeBidello.setPreposizioni(new String[] { "a", "con" });
		Name aliasBidello = new Name("bidello", WordType.NOME);
		aliasBidello.setArticoli(new String[] { "il" });
		aliasBidello.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio bidello = new Npc(nomeBidello, Dialogs.JANITOR_A);
		bidello.setAlias(new Name[] { aliasBidello });
		informatica1.addPersonaggio(bidello);
		//Oggetti
		Name NamePannello = new Name("pannello", WordType.NOME);
		NamePannello.setArticoli(new String[] { "il", });
		NamePannello.setPreposizioni(new String[] { "del" });
		GenericObject pannello = new Pannello(NamePannello,
				"Un pannello composto da leve da 5 colori diversi\nRosso,Giallo,Verde,Blu e Nero");
		pannello.setPrendibile(false);

		Name nomeMacchinetta = new Name("macchinetta", WordType.NOME);
		nomeMacchinetta.setArticoli(new String[] { "la", "una" });
		nomeMacchinetta.setPreposizioni(new String[] { "nella" });
		GenericObject macchinaCaffe = new Caffe(nomeMacchinetta, Descriptions.COFFEEMAKER);
		macchinaCaffe.setPrendibile(false);

		Name nomeAcqua = new Name("acqua", WordType.NOME);
		nomeAcqua.setArticoli(new String[] { "l" });
		nomeAcqua.setPreposizioni(new String[] { "quella" });
		GenericObject acqua = new GenericObject(nomeAcqua, Descriptions.WATER, ObjectType.EVENT);

		Name nomeCaffe = new Name("caffe", WordType.NOME);
		nomeCaffe.setArticoli(new String[] { "il" });
		nomeCaffe.setPreposizioni(new String[] { "quello" });
		GenericObject caffe = new GenericObject(nomeCaffe, Descriptions.COFFEE, ObjectType.EVENT);

		Name nomeCaffeCaldo = new Name("espresso", WordType.NOME);
		nomeCaffeCaldo.setArticoli(new String[] { "un" });
		nomeCaffeCaldo.setPreposizioni(new String[] { "quel" });
		GenericObject caffeCaldo  = new GenericObject(nomeCaffeCaldo, Descriptions.ESPRESSO, ObjectType.EVENT);

		Name nomeleva = new Name("leva", WordType.NOME);
		GenericObject leva = new GenericObject(nomeleva, "", ObjectType.NORMAL);
		leva.setAggettivi(new String[] { "rossa", "gialla", "verde", "blu", "nera" });

		Name nomeCartellone = new Name("cartellone", WordType.NOME);
		nomeCartellone.setArticoli(new String[] { "il" });
		GenericObject cartellone = new GenericObject(nomeCartellone, Descriptions.ADVERT, ObjectType.NORMAL);
		cartellone.setPrendibile(false);

		Name nomePoster = new Name("poster", WordType.NOME);
		nomePoster.setArticoli(new String[] { "il" });
		GenericObject poster = new GenericObject(nomePoster, Descriptions.POSTER, ObjectType.NORMAL);
		poster.setPrendibile(false);

		Name nomePacco = new Name("pacco", WordType.NOME);
		nomePacco.setArticoli(new String[] { "il" });
		GenericObject pacco = new GenericObject(nomePacco, Descriptions.PACK, ObjectType.NORMAL);
		pacco.setAggettivi(new String[] { "jamazon" });

		Name nomeCd = new Name("cd", WordType.NOME);
		nomeCd.setArticoli(new String[] { "il", "un" });
		GenericObject cd = new GenericObject(nomeCd, Descriptions.CD, ObjectType.NORMAL);
		cd.setAggettivi(new String[] { "musicale", "napoletano" });

		Name nomeRivista = new Name("rivista", WordType.NOME);
		nomeRivista.setArticoli(new String[] { "la" });
		GenericObject rivista = new GenericObject(nomeRivista, Descriptions.MAGAZINE, ObjectType.NORMAL);

		Name nomeGrimaldello1 = new Name("grimaldello_rotto", WordType.NOME);
		nomeGrimaldello1.setArticoli(new String[] { "il", "un" });
		GenericObject grimaldello1 = new GenericObject(nomeGrimaldello1, Descriptions.PICK, ObjectType.NORMAL);

		Name nomeForcina = new Name("forcina", WordType.NOME);
		nomeForcina.setArticoli(new String[] { "la", "una" });
		GenericObject forcina = new GenericObject(nomeForcina, Descriptions.BOBBYPIN, ObjectType.NORMAL);

		Name nomeGrimaldello2 = new Name("grimaldello", WordType.NOME);
		nomeGrimaldello2.setArticoli(new String[] { "il", "un" });
		GenericObject grimaldello2 = new GenericObject(nomeGrimaldello2, Descriptions.LOCKPICK, ObjectType.NORMAL);

		Name nomeComponente = new Name("pezzo_elettronico", WordType.NOME);
		nomeComponente.setArticoli(new String[] { "il", "un" });
		GenericObject componente = new GenericObject(nomeComponente, Descriptions.COMPONENT, ObjectType.NORMAL);

		Name nomeViti = new Name("viti", WordType.NOME);
		nomeViti.setArticoli(new String[] { "le" });
		GenericObject viti = new GenericObject(nomeViti, Descriptions.SCREWS, ObjectType.NORMAL);

		Name nomePropulsore = new Name("propulsore_ionico", WordType.NOME);
		nomePropulsore.setArticoli(new String[] { "il", "un" });
		GenericObject propulsore = new GenericObject(nomePropulsore, Descriptions.ENGINE, ObjectType.NORMAL);

		Name nomeChiavi = new Name("chiavi", WordType.NOME);
		nomeChiavi.setArticoli(new String[] { "le" });
		GenericObject chiavi = new GenericObject(nomeChiavi, Descriptions.KEYS, ObjectType.NORMAL);

		Name nomeAuto = new Name("automobile", WordType.NOME);
		nomeAuto.setArticoli(new String[] { "l" });
		Name aliasAuto1 = new Name("berlina", WordType.NOME);
		aliasAuto1.setArticoli(new String[] { "la" });
		Name aliasAuto2 = new Name("auto", WordType.NOME);
		aliasAuto2.setArticoli(new String[] { "l" });
		Name aliasAuto3 = new Name("macchina", WordType.NOME);
		aliasAuto3.setArticoli(new String[] { "la" });
		Name aliasAuto4 = new Name("bagagliaio", WordType.NOME);
		aliasAuto4.setArticoli(new String[] { "il" });
		GestoreAlias aliasMacchina = new GestoreAlias(new Name[] { aliasAuto1, aliasAuto2, aliasAuto3, aliasAuto4 });
		GenericObjectContainer automobile = new GenericObjectContainer(nomeAuto, Descriptions.CAR_CLOSED,
				ObjectType.CONTAINER, aliasMacchina);
		automobile.addToContainer(rivista);
		automobile.setPrendibile(false);

		oggetti.add(leva);
		oggetti.add(acqua);
		chimica1.addOggetto(acqua);
		oggetti.add(caffe);
		chimica1.addOggetto(caffe);
		oggetti.add(caffeCaldo);
		oggetti.add(cartellone);
		strada3.addOggetto(cartellone);
		oggetti.add(pacco);
		strada3.addOggetto(pacco);
		oggetti.add(poster);
		fisica1.addOggetto(poster);
		oggetti.add(rivista);
		oggetti.add(automobile);
		stradaChiusa.addOggetto(automobile);
		oggetti.add(cd);
		oggetti.add(grimaldello1);
		oggetti.add(forcina);
		oggetti.add(grimaldello2);
		oggetti.add(viti);
		oggetti.add(componente);
		oggetti.add(propulsore);
		oggetti.add(chiavi);
		oggetti.add(macchinaCaffe);
		chimica1.addOggetto(macchinaCaffe);
		oggetti.add(pannello);
		fisica1.addOggetto(pannello);
		Combinations.addCombination(grimaldello1, forcina, grimaldello2);
		Combinations.addCombination(componente, viti, propulsore);

		//Eventi
		Evento eventoCaffe = new Evento(
				"\nSono le 8 di mattina, dovresti preparati \nun bel caffe per iniziare questa giornata di merda"
						+ "\n\n" + protagonista.getNome().getName() + ": Mi puo' fare un caffe caldo per favore?"
						+ "\nBarista Sedicina: Non ho intenzione di fare un bel nulla, preparatelo da solo coglione");
		eventoCaffe.addEnigma(macchinaCaffe);
		chimica1.setGestoreEvento(new GestoreEventoCaffe(eventoCaffe, chimica1));
		chimica1.getGestoreEvento().setRicompensa(caffeCaldo);

		Evento eventoPannello = new Evento("\nLa porta successiva e' bloccata da qualche meccanismo");
		eventoPannello.addEnigma(pannello);
		fisica1.setGestoreEvento(new GestoreEventoPannello(eventoPannello, fisica1));

		Evento consegnaPacco = new Evento("\nLa pizzeria ha una barricata di legno che ti impedisce l'accesso\n"
				+ "Senti una voce: Ehi tu! Sono il pizzaiolo, portami il contenuto di quel pacco Jamazon e ti faro' entrare");
		consegnaPacco.addEnigma(pacco);
		pizzeria1.setGestoreEvento(new GestoreEventoPacco(consegnaPacco, pizzeria1));

		Evento consegnaRivista = new Evento(
				"Il bidello ha le chiavi del dipartimento di Fisica!\r\nMa vuole qualcosa in cambio...");
		consegnaRivista.addEnigma(rivista);
		informatica1.setGestoreEvento(new GestoreEventoRivista(consegnaRivista, informatica1));
		informatica1.getGestoreEvento().setRicompensa(chiavi);

		stanzaCorrente = strada1;
	}

	public void creaProtagonista(Name nome) {
		protagonista = new Protagonista(nome);
	}

	public String getNomeProtagonista() {
		return protagonista.getNome().getName();
	}

	public int getVitaProtagonista() {
		return protagonista.getHealthPoints();
	}

	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza room) {
		stanzaCorrente = room;
	}

	public Personaggio getProtagonista() {
		return protagonista;
	}

	public void IniziaEvento() {

	}

}
