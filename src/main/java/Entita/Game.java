/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita;

import java.util.ArrayList;

import Entita.Characters.Character;
import Entita.Characters.Npc;
import Entita.Characters.Protagonist;
import General.AliasHandler;
import General.Combinations;
import General.GenericObject;
import General.GenericObjectContainer;
import General.Name;
import General.Eventi.CarEventHandler;
import General.Eventi.CoffeEventHandler;
import General.Eventi.DibDoorEventHandler;
import General.Eventi.Event;
import General.Eventi.HelicopterEventHandler;
import General.Eventi.MagazineEventHandler;
import General.Eventi.PackageEventHandler;
import General.Eventi.PanelEventHandler;
import General.Eventi.PhysicsEventHandler;
import General.Eventi.PropulsorEventHandler;
import General.Eventi.Enigmi.Coffe;
import General.Eventi.Enigmi.Panel;
import Parser.WordType;
import Resources.Descriptions;
import Resources.Dialogs;
import Resources.Places;

public class Game {

	private Room currentRoom;
	private Character protagonist;
	private final ArrayList<GenericObject> gameObjects = new ArrayList<>();

	public ArrayList<GenericObject> getObjects() {
		return gameObjects;
	}

	public Game() {
		protagonist = new Protagonist(new Name("Pippo", WordType.NOME_PROPRIO));
		// Mappa
		Room strada1 = new Room("Via Amendola, primo settore", Places.SOUTH1);
		Room strada2 = new Room("Via Amendola, secondo settore", Places.SOUTH2);
		Room strada3 = new Room("Via Amendola, terzo settore", Places.SOUTH3);
		Room strada4 = new Room("Strada Nord 1", Places.NORTH1);
		Room strada5 = new Room("Strada Nord 2", Places.NORTH2);
		Room strada6 = new Room("Strada Nord 3", Places.NORTH3);
		Room incrocio = new Room("Incrocio", Places.CROSS);
		Room executive = new Room("Executive Center, Uffici", Places.EXECUTIVE);
		Room pizzeria1 = new Room("Pizzeria Esterno", Places.PIZZA_EXT);
		Room pizzeria2 = new Room("Pizzeria Interno", Places.PIZZA_INT);
		Room stradaChiusa = new Room("Strada Chiusa", Places.WEST);
		Room fisica1 = new Room("Atrio di Fisica", Places.PHYSICS_NOCURR);
		Room fisica2 = new Room("Sala raggi cosmici", Places.COSMIC_RAYS);
		Room fisica3 = new Room("Ufficio", "");
		Room chimica1 = new Room("Bar di Chimica", Places.CHEMISTRY);
		Room informatica1 = new Room("Atrio DIB", Places.DIB);
		strada1.setUp(strada2);
		executive.setLeft(strada2);
		strada2.setRight(executive).setUp(strada3).setDown(strada1);
		pizzeria1.setLeft(strada3).setRight(pizzeria2);
		strada3.setDown(strada2).setUp(incrocio).setRight(pizzeria1);
		pizzeria2.setLeft(pizzeria1);
		incrocio.setDown(strada3).setLeft(stradaChiusa).setUp(strada4);
		stradaChiusa.setRight(incrocio);
		strada4.setDown(incrocio).setUp(strada5).setRight(chimica1);
		strada5.setDown(strada4).setLeft(fisica1).setUp(strada6);
		fisica1.setLeft(fisica2).setRight(strada5);
		fisica2.setUp(fisica3).setRight(fisica1);
		fisica3.setDown(fisica2);
		chimica1.setLeft(strada4);
		strada6.setDown(strada5).setRight(informatica1);
		informatica1.setLeft(strada6);
		fisica1.setAccessible(false);
		informatica1.setAccessible(false);
		fisica2.setAccessible(false);
		pizzeria2.setAccessible(false);

		// NPC

		Name nomeAutista = new Name("Mimmo", WordType.NOME_PROPRIO);
		nomeAutista.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasAutista = new Name("autista", WordType.NOME);
		aliasAutista.setAdmittedArticles(new String[] { "l"});
		aliasAutista.setAdmittedPrepositions(new String[] { "con", "a", "all", "ad" });
		Character autista = new Npc(nomeAutista, Dialogs.BUSDRIVER);
		autista.setAlias(new Name[] { aliasAutista });
		strada1.addCharacter(autista);

		Name nomeMorgan = new Name("Morgan", WordType.NOME_PROPRIO);
		nomeMorgan.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasMorgan = new Name("morgan", WordType.NOME);
		aliasMorgan.setAdmittedPrepositions(new String[] { "a", "con" });
		Character morgan = new Npc(nomeMorgan, Dialogs.MORGAN_A);
		morgan.setAlias(new Name[] { aliasMorgan });
		incrocio.addCharacter(morgan);

		Name nomeBarista = new Name("Cannavacciuolo", WordType.NOME_PROPRIO);
		nomeBarista.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasBarista = new Name("antonino", WordType.NOME);
		aliasBarista.setAdmittedPrepositions(new String[] { "con", "a", "ad"});
		Character barista = new Npc(nomeBarista, Dialogs.CANNAVACCIUOLO_A);
		barista.setAlias(new Name[] { aliasBarista });
		chimica1.addCharacter(barista);

		Name nomePizzaiolo = new Name("Almerino", WordType.NOME_PROPRIO);
		nomePizzaiolo.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasPizzaiolo = new Name("pizzaiolo", WordType.NOME);
		aliasPizzaiolo.setAdmittedArticles(new String[] { "il" });
		aliasPizzaiolo.setAdmittedPrepositions(new String[] { "con", "a", "ad" });
		Character pizzaiolo = new Npc(nomePizzaiolo, Dialogs.BAKER_A);
		pizzaiolo.setAlias(new Name[] { aliasPizzaiolo });
		pizzeria1.addCharacter(pizzaiolo);

		Name nomeBidello = new Name("Bidello", WordType.NOME_PROPRIO);
		nomeBidello.setAdmittedPrepositions(new String[] { "al", "col" , "con"});
		Name aliasBidello = new Name("bidello", WordType.NOME);
		aliasBidello.setAdmittedArticles(new String[] { "il" });
		aliasBidello.setAdmittedPrepositions(new String[] { "con", "al"});
		Character bidello = new Npc(nomeBidello, Dialogs.JANITOR_A);
		bidello.setAlias(new Name[] { aliasBidello });
		informatica1.addCharacter(bidello);

		Name nomePilota = new Name("Pilota", WordType.NOME_PROPRIO);
		nomePilota.setAdmittedPrepositions(new String[] { "al", "con", "col" });
		Name aliasPilota = new Name("pilota", WordType.NOME);
		aliasPilota.setAdmittedArticles(new String[] { "il" });
		aliasPilota.setAdmittedPrepositions(new String[] { "al", "con", "col"});
		Character pilota = new Npc(nomePilota, Dialogs.PILOT_A);
		pilota.setAlias(new Name[] { aliasPilota });
		informatica1.addCharacter(pilota);

		Name nomeBruno = new Name("Bruno", WordType.NOME_PROPRIO);
		nomeBruno.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasBruno1 = new Name("chef", WordType.NOME);
		aliasBruno1.setAdmittedArticles(new String[] { "lo" });
		aliasBruno1.setAdmittedPrepositions(new String[] { "allo", "con"});
		Name aliasBruno2 = new Name("barbieri", WordType.NOME);
		aliasBruno2.setAdmittedArticles(new String[] { "lo" });
		aliasBruno2.setAdmittedPrepositions(new String[] { "a", "con"});
		Character Bruno = new Npc(nomeBruno, Dialogs.BRUNO);
		Bruno.setAlias(new Name[] { aliasBruno1, aliasBruno2 });

		Name nomeVolpe = new Name("Volpe", WordType.NOME_PROPRIO);
		nomeBruno.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasVolpe = new Name("prof", WordType.NOME);
		aliasVolpe.setAdmittedArticles(new String[] { "il" });
		aliasVolpe.setAdmittedPrepositions(new String[] {"col", "con", "al"});
		Character volpe = new Npc(nomeVolpe, Dialogs.VOLPE_A);
		volpe.setAlias(new Name[] { aliasVolpe });
		fisica2.addCharacter(volpe);

		// Oggetti

		Name NamePannello = new Name("pannello", WordType.NOME);
		NamePannello.setAdmittedArticles(new String[] { "il", });
		NamePannello.setAdmittedPrepositions(new String[] { "del" });
		GenericObject pannello = new Panel(NamePannello, Descriptions.PANEL);
		pannello.setTakeable(false);

		Name nomeMacchinetta = new Name("macchinetta", WordType.NOME);
		nomeMacchinetta.setAdmittedArticles(new String[] { "la", "una" });
		nomeMacchinetta.setAdmittedPrepositions(new String[] { "nella" , "dentro"});
		GenericObject macchinaCaffe = new Coffe(nomeMacchinetta, Descriptions.COFFEEMAKER);
		macchinaCaffe.setTakeable(false);

		Name nomeAcqua = new Name("acqua", WordType.NOME);
		nomeAcqua.setAdmittedArticles(new String[] { "l" });
		nomeAcqua.setAdmittedPrepositions(new String[] { "quella" });
		GenericObject acqua = new GenericObject(nomeAcqua, Descriptions.WATER);

		Name nomeCaffe = new Name("caffe", WordType.NOME);
		nomeCaffe.setAdmittedArticles(new String[] { "il" });
		nomeCaffe.setAdmittedPrepositions(new String[] { "quello" });
		GenericObject caffe = new GenericObject(nomeCaffe, Descriptions.COFFEE);

		Name nomeleva = new Name("leva", WordType.NOME);
		GenericObject leva = new GenericObject(nomeleva, "");
		leva.setAdjectives(new String[] { "rossa", "gialla", "verde", "blu", "nera" });

		Name nomeCartellone = new Name("cartellone", WordType.NOME);
		nomeCartellone.setAdmittedArticles(new String[] { "il" });
		GenericObject cartellone = new GenericObject(nomeCartellone, Descriptions.ADVERT);
		cartellone.setTakeable(false);

		Name nomePoster = new Name("poster", WordType.NOME);
		nomePoster.setAdmittedArticles(new String[] { "il" });
		GenericObject poster = new GenericObject(nomePoster, Descriptions.POSTER);
		poster.setTakeable(false);

		Name nomePacco = new Name("pacco", WordType.NOME);
		nomePacco.setAdmittedArticles(new String[] { "il" });
		GenericObject pacco = new GenericObjectContainer(nomePacco, Descriptions.PACK);
		pacco.setAdjectives(new String[] { "jamazon" });

		Name nomeCd = new Name("cd", WordType.NOME);
		nomeCd.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject cd = new GenericObject(nomeCd, Descriptions.CD);
		cd.setAdjectives(new String[] { "musicale", "napoletano" });
		((GenericObjectContainer) pacco).addToContainer(cd);
		((GenericObjectContainer) pacco).setBlocked(true);

		Name nomeRivista = new Name("rivista", WordType.NOME);
		nomeRivista.setAdmittedArticles(new String[] { "la" });
		GenericObject rivista = new GenericObject(nomeRivista, Descriptions.MAGAZINE);

		Name nomeGrimaldello1 = new Name("grimaldello", WordType.NOME);
		nomeGrimaldello1.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject grimaldello1 = new GenericObject(nomeGrimaldello1, Descriptions.PICK);
		grimaldello1.setAdjectives(new String[] { "rotto" });

		Name nomeForcina = new Name("forcina", WordType.NOME);
		nomeForcina.setAdmittedArticles(new String[] { "la", "una" });
		GenericObject forcina = new GenericObject(nomeForcina, Descriptions.BOBBYPIN);

		Name nomeGrimaldello2 = new Name("passepartout", WordType.NOME);
		nomeGrimaldello2.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject grimaldello2 = new GenericObject(nomeGrimaldello2, Descriptions.LOCKPICK);

		Name nomeComponente = new Name("componente", WordType.NOME);
		nomeComponente.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject componente = new GenericObject(nomeComponente, Descriptions.COMPONENT);

		Name nomeViti = new Name("viti", WordType.NOME);
		nomeViti.setAdmittedArticles(new String[] { "le" });
		GenericObject viti = new GenericObject(nomeViti, Descriptions.SCREWS);

		Name nomePropulsore = new Name("propulsore", WordType.NOME);
		nomePropulsore.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject propulsore = new GenericObject(nomePropulsore, Descriptions.ENGINE);

		Name nomeChiaviDib = new Name("chiavi", WordType.NOME);
		nomeChiaviDib.setAdmittedArticles(new String[] { "le" });
		GenericObject chiaviDib = new GenericObject(nomeChiaviDib, Descriptions.KEYS);
		chiaviDib.setAdjectives(new String[] { "dib" });

		Name nomeChiaviFis = new Name("comunella", WordType.NOME);
		nomeChiaviFis.setAdmittedArticles(new String[] { "le" });
		GenericObject chiaviFis = new GenericObject(nomeChiaviFis, Descriptions.COMMON);
		chiaviFis.setAdjectives(new String[] { "fisica" });

		Name nomeTaglierino = new Name("taglierino", WordType.NOME);
		nomeTaglierino.setAdmittedArticles(new String[] { "il", "un" });
		nomeTaglierino.setAdmittedPrepositions(new String[] { "con", "col" });
		GenericObject taglierino = new GenericObject(nomeTaglierino, Descriptions.KNIFE);

		Name nomeElicottero = new Name("elicottero", WordType.NOME);
		nomeElicottero.setAdmittedArticles(new String[] { "l" });
		nomeElicottero.setAdmittedPrepositions(new String[] { "quell" });
		GenericObject elicottero = new GenericObject(nomeElicottero, Descriptions.HELICOPTER);
		elicottero.setTakeable(false);

		Name nomeSoldi = new Name("soldi", WordType.NOME);
		nomeSoldi.setAdmittedArticles(new String[] { "i" });
		nomeSoldi.setAdmittedPrepositions(new String[] { "dei" });
		GenericObject soldi = new GenericObject(nomeSoldi, Descriptions.MONEY);

		Name nomeServer = new Name("server", WordType.NOME);
		nomeServer.setAdmittedArticles(new String[] { "il" });
		GenericObject server = new GenericObject(nomeServer, Descriptions.SERVER);
		server.setTakeable(false);

		Name nomeAuto = new Name("automobile", WordType.NOME);
		nomeAuto.setAdmittedArticles(new String[] { "l" });
		Name aliasAuto1 = new Name("berlina", WordType.NOME);
		aliasAuto1.setAdmittedArticles(new String[] { "la" });
		Name aliasAuto2 = new Name("auto", WordType.NOME);
		aliasAuto2.setAdmittedArticles(new String[] { "l" });
		Name aliasAuto3 = new Name("macchina", WordType.NOME);
		aliasAuto3.setAdmittedArticles(new String[] { "la" });
		Name aliasAuto4 = new Name("bagagliaio", WordType.NOME);
		aliasAuto4.setAdmittedArticles(new String[] { "il" });
		AliasHandler aliasMacchina = new AliasHandler(new Name[] { aliasAuto1, aliasAuto2, aliasAuto3, aliasAuto4 });
		GenericObject automobile = new GenericObjectContainer(nomeAuto, Descriptions.CAR_CLOSED, aliasMacchina);
		((GenericObjectContainer) automobile).addToContainer(rivista);
		((GenericObjectContainer) automobile).addToContainer(componente);
		automobile.setTakeable(false);
		((GenericObjectContainer) automobile).setBlocked(true);

		gameObjects.add(leva);
		gameObjects.add(acqua);
		gameObjects.add(caffe);
		gameObjects.add(cartellone);
		gameObjects.add(pacco);
		gameObjects.add(poster);
		gameObjects.add(rivista);
		gameObjects.add(automobile);
		gameObjects.add(cd);
		gameObjects.add(grimaldello1);
		gameObjects.add(forcina);
		gameObjects.add(grimaldello2);
		gameObjects.add(viti);
		gameObjects.add(componente);
		gameObjects.add(propulsore);
		gameObjects.add(chiaviDib);
		gameObjects.add(macchinaCaffe);
		gameObjects.add(pannello);
		gameObjects.add(taglierino);
		gameObjects.add(chiaviFis);
		gameObjects.add(soldi);
		gameObjects.add(elicottero);
		gameObjects.add(server);
		executive.addObject(server);
		executive.addObject(viti);
		strada1.addObject(taglierino);
		chimica1.addObject(acqua);
		strada3.addObject(cartellone);
		strada3.addObject(pacco);
		fisica1.addObject(poster);
		stradaChiusa.addObject(automobile);
		chimica1.addObject(macchinaCaffe);
		fisica1.addObject(pannello);
		pizzeria1.addObject(forcina);
		strada6.addObject(grimaldello1);
		fisica3.addObject(soldi);
		Combinations.addCombination(grimaldello1, forcina, grimaldello2);
		Combinations.addCombination(componente, viti, propulsore);

		// EVENTI
		Event eventoCaffe = new Event(Dialogs.COFFE_EVENT, chimica1);
		eventoCaffe.setEnigma(macchinaCaffe);
		eventoCaffe.setReward(chiaviDib);
		chimica1.setEventHandler(new CoffeEventHandler(eventoCaffe));

		Event eventoPannello = new Event("La porta successiva e' bloccata da qualche meccanismo", fisica1);
		eventoPannello.setEnigma(pannello);
		fisica1.setEventHandler(new PanelEventHandler(eventoPannello));

		Event consegnaPacco = new Event("La pizzeria ha una barricata di legno che ti impedisce l'accesso\n"
				+ "Senti una voce: Ehi tu! Sono il pizzaiolo, portami il contenuto di quel pacco Jamazon e ti faro' entrare", pizzeria1);
		consegnaPacco.setEnigma(cd);
		consegnaPacco.setReward(caffe);
		pizzeria1.setEventHandler(new PackageEventHandler(consegnaPacco));

		Event consegnaRivista = new Event(
				"Il bidello ha le chiavi del dipartimento di Fisica!\r\nMa vuole qualcosa in cambio...", informatica1);
		consegnaRivista.setEnigma(rivista);
		consegnaRivista.setReward(chiaviFis);
		informatica1.setEventHandler(new MagazineEventHandler(consegnaRivista));

		Event apriMacchina = new Event("", stradaChiusa);
		apriMacchina.setEnigma(grimaldello2);
		stradaChiusa.setEventHandler(new MagazineEventHandler(apriMacchina));

		Event portaFisica = new Event(
				"La porta del dipartimento di fisica e' chiusa a chiave, devo trovare un altro modo", strada5);
		portaFisica.setEnigma(chiaviFis);
		strada5.setEventHandler(new PhysicsEventHandler(portaFisica));

		Event portaDib = new Event(
				"La porta del dipartimento di informatica e' chiusa a chiave, devo trovare un altro modo", strada6);
		portaDib.setEnigma(chiaviDib);
		strada6.setEventHandler(new DibDoorEventHandler(portaDib));

		Event auto = new Event(
				"Vedi un auto in lontananza, il bagagliaio e' quasi aperto,\r\nma e' protetto da una serratura, ti servira qualcosa per aprirla", stradaChiusa);
		auto.setEnigma(grimaldello2);
		stradaChiusa.setEventHandler(new CarEventHandler(auto));

		Event raggi = new Event(
				"Volpe: Ehy tu! Non ti avvicinare alla macchina dei raggi cosmici, e' il gioiello del dipartimento di fisica", fisica2);
		raggi.setEnigma(propulsore);
		fisica2.setEventHandler(new PropulsorEventHandler(raggi));

		Event eventoElicottero = new Event(
				"Il pilota e' arrivato con il suo elicottero, pero non vuole far salire nessuno\ntranne Pasquale Lops che e' gia a bordo", incrocio);
		eventoElicottero.setEnigma(soldi);
		eventoElicottero.addEventObject(elicottero);
		eventoElicottero.addEventCharacter(Bruno);
		incrocio.setEventHandler( new HelicopterEventHandler(eventoElicottero, fisica2));

		currentRoom = strada1;
	}

	public void createProtagonist(Name nome) {
		protagonist = new Protagonist(nome);
	}

	public String getProtagonistName() {
		return protagonist.getName().getName();
	}

	/*public int getVitaProtagonista() {
		return protagonist.getHealthPoints();
	}*/

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}

	public Character getProtagonist() {
		return protagonist;
	}

}
