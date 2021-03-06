/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadingFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Alias;
import entities.Combinations;
import entities.GenericObject;
import entities.GenericObjectContainer;
import entities.Name;
import entities.Room;
import entities.characters.Npc;
import entities.enigmas.Coffee;
import entities.enigmas.Panel;
import handlers.events.CoffeeEventHandler;
import handlers.events.DibDoorEventHandler;
import handlers.events.Event;
import handlers.events.GenericEventHandler;
import handlers.events.HelicopterEventHandler;
import handlers.events.MagazineEventHandler;
import handlers.events.PackageEventHandler;
import handlers.events.PanelEventHandler;
import handlers.events.PhysicsDoorEventHandler;
import handlers.events.PropulsorEventHandler;
import parser.WordType;
import resources.Descriptions;
import resources.Dialogs;
import resources.Events;
import resources.Names;
import resources.Places;

/**
 *
 * Responsabilità: Inizializzazione di tutte le entità facente parti del gioco e creazione dei vari file
 */
public class FilesCreation {

	public static void saveFiles() {
		ArrayList<GenericObject> gameObjects = new ArrayList<>();
		Room strada1 = new Room(Places.AMENDOLA1_NAME, Places.AMENDOLA1_DESCRIPTION);
		Room strada2 = new Room(Places.AMENDOLA2_NAME, Places.AMENDOLA2_DESCRIPTION);
		Room strada3 = new Room(Places.AMENDOLA3_NAME, Places.AMENDOLA3_DESCRIPTION);
		Room strada4 = new Room(Places.STREET1_NAME, Places.STREET1_DESCRIPTION);
		Room strada5 = new Room(Places.STREET2_NAME, Places.STREET2_DESCRIPTION);
		Room strada6 = new Room(Places.STREET3_NAME, Places.STREET3_DESCRIPTION);
		Room incrocio = new Room(Places.CROSS_NAME, Places.CROSS_DESCRIPTION);
		Room executive = new Room(Places.EXECUTIVE_NAME, Places.EXECUTIVE_DESCRIPTION);
		Room pizzeria1 = new Room(Places.PIZZA_EXT_NAME, Places.PIZZA_EXT_DESCRIPTION);
		Room pizzeria2 = new Room(Places.PIZZA_INT_NAME, Places.PIZZA_INT_DESCRIPTION);
		Room stradaChiusa = new Room(Places.BLOCKED_STREET_NAME, Places.BLOCKED_STREET_DESCRIPTION);
		Room fisica1 = new Room(Places.PHYSICS_LOBBY_NAME, Places.PHYSICS_NOCURR);
		Room fisica2 = new Room(Places.COSMIC_RAYS_ROOM_NAME, Places.COSMIC_RAYS_ROOM_DESCRIPTION);
		Room fisica3 = new Room(Places.OFFICE_NAME, Places.VOLPE_OFFICE);
		Room chimica = new Room(Places.CHEMISTRY_BAR_NAME, Places.CHEMISTRY_BAR_DESCRIPTION);
		Room informatica = new Room(Places.DIB_LOBBY_NAME, Places.DIB_LOBBY_DESCRIPTION);
		strada1.setUp(strada2);
		executive.setLeft(strada2);
		strada2.setRight(executive).setUp(strada3).setDown(strada1);
		pizzeria1.setLeft(strada3).setRight(pizzeria2);
		strada3.setDown(strada2).setUp(incrocio).setRight(pizzeria1);
		pizzeria2.setLeft(pizzeria1);
		incrocio.setDown(strada3).setLeft(stradaChiusa).setUp(strada4);
		stradaChiusa.setRight(incrocio);
		strada4.setDown(incrocio).setUp(strada5).setRight(chimica);
		strada5.setDown(strada4).setLeft(fisica1).setUp(strada6);
		fisica1.setLeft(fisica2).setRight(strada5);
		fisica2.setUp(fisica3).setRight(fisica1);
		fisica3.setDown(fisica2);
		chimica.setLeft(strada4);
		strada6.setDown(strada5).setRight(informatica);
		informatica.setLeft(strada6);
		fisica1.setAccessible(false);
		informatica.setAccessible(false);
		fisica2.setAccessible(false);
		pizzeria2.setAccessible(false);

		// NPC

		Name nomeAutista = new Name(Names.DRIVER_NAME, WordType.NOME_PROPRIO);
		nomeAutista.setAdmittedPrepositions(new String[] { "a", "con", "e" });
		Name aliasAutista = new Name("autista", WordType.NOME);
		aliasAutista.setAdmittedArticles(new String[] { "l" });
		aliasAutista.setAdmittedPrepositions(new String[] { "a", "con", "all" });
		entities.characters.Character autista = new Npc(nomeAutista, Dialogs.BUSDRIVER);
		autista.setAlias(new Name[] { aliasAutista });
		strada1.addCharacter(autista);

		Name nomeMorgan = new Name(Names.MORGAN_NAME, WordType.NOME_PROPRIO);
		nomeMorgan.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasMorgan = new Name("morgan", WordType.NOME);
		aliasMorgan.setAdmittedPrepositions(new String[] { "a", "con" });
		entities.characters.Character morgan = new Npc(nomeMorgan, Dialogs.MORGAN_A);
		morgan.setAlias(new Name[] { aliasMorgan });
		incrocio.addCharacter(morgan);

		Name nomeBarista = new Name(Names.CANNAVACCIUOLO_NAME, WordType.NOME_PROPRIO);
		nomeBarista.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasBarista = new Name("antonino", WordType.NOME);
		aliasBarista.setAdmittedPrepositions(new String[] { "ad", "con" });
		entities.characters.Character barista = new Npc(nomeBarista, Dialogs.CANNAVACCIUOLO_A);
		barista.setAlias(new Name[] { aliasBarista });
		chimica.addCharacter(barista);

		Name nomePizzaiolo = new Name("Almerino", WordType.NOME_PROPRIO);
		nomePizzaiolo.setAdmittedPrepositions(new String[] { "ad", "con" });
		Name aliasPizzaiolo = new Name("pizzaiolo", WordType.NOME);
		aliasPizzaiolo.setAdmittedArticles(new String[] { "il" });
		aliasPizzaiolo.setAdmittedPrepositions(new String[] { "con", "al", "col" });
		entities.characters.Character pizzaiolo = new Npc(nomePizzaiolo, Dialogs.BAKER_A);
		pizzaiolo.setAlias(new Name[] { aliasPizzaiolo });
		pizzeria1.addCharacter(pizzaiolo);

		Name nomeBidello = new Name("Bidello", WordType.NOME_PROPRIO);
		nomeBidello.setAdmittedArticles(new String[] { "il" });
		nomeBidello.setAdmittedPrepositions(new String[] { "al", "col", "con" });
		Name aliasBidello = new Name("bidello", WordType.NOME);
		aliasBidello.setAdmittedArticles(new String[] { "il" });
		aliasBidello.setAdmittedPrepositions(new String[] { "con", "al", "col" });
		entities.characters.Character bidello = new Npc(nomeBidello, Dialogs.JANITOR_A);
		bidello.setAlias(new Name[] { aliasBidello });
		informatica.addCharacter(bidello);

		Name nomePilota = new Name("Pilota", WordType.NOME_PROPRIO);
		nomePilota.setAdmittedArticles(new String[] { "il" });
		nomePilota.setAdmittedPrepositions(new String[] { "al", "con", "col" });
		Name aliasPilota = new Name("pilota", WordType.NOME);
		aliasPilota.setAdmittedArticles(new String[] { "il" });
		aliasPilota.setAdmittedPrepositions(new String[] { "al", "con", "col" });
		entities.characters.Character pilota = new Npc(nomePilota, Dialogs.PILOT_A);
		pilota.setAlias(new Name[] { aliasPilota });
		informatica.addCharacter(pilota);

		Name nomeBruno = new Name("Bruno", WordType.NOME_PROPRIO);
		nomeBruno.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasBruno1 = new Name("chef", WordType.NOME);
		aliasBruno1.setAdmittedArticles(new String[] { "lo" });
		aliasBruno1.setAdmittedPrepositions(new String[] { "allo", "con" });
		Name aliasBruno2 = new Name("barbieri", WordType.NOME);
		aliasBruno2.setAdmittedPrepositions(new String[] { "a", "con" });
		entities.characters.Character Bruno = new Npc(nomeBruno, Dialogs.BRUNO);
		Bruno.setAlias(new Name[] { aliasBruno1, aliasBruno2 });

		Name nomeVolpe = new Name("Volpe", WordType.NOME_PROPRIO);
		nomeVolpe.setAdmittedPrepositions(new String[] { "a", "con" });
		Name aliasVolpe = new Name("prof", WordType.NOME);
		aliasVolpe.setAdmittedArticles(new String[] { "il" });
		aliasVolpe.setAdmittedPrepositions(new String[] { "col", "con", "al" });
		entities.characters.Character volpe = new Npc(nomeVolpe, Dialogs.VOLPE_A);
		volpe.setAlias(new Name[] { aliasVolpe });
		fisica2.addCharacter(volpe);

		// Oggetti

		Name NamePannello = new Name(Names.PANEL, WordType.NOME);
		NamePannello.setAdmittedArticles(new String[] { "il", });
		NamePannello.setAdmittedPrepositions(new String[] { "del" });
		GenericObject pannello = new Panel(NamePannello, Descriptions.PANEL);
		pannello.setTakeable(false);

		Name nomeMacchinetta = new Name(Names.COFFE_MACHINE, WordType.NOME);
		nomeMacchinetta.setAdmittedArticles(new String[] { "la", "una" });
		nomeMacchinetta.setAdmittedPrepositions(new String[] { "nella", "dentro", "in" });
		GenericObject macchinaCaffe = new Coffee(nomeMacchinetta, Descriptions.COFFEEMAKER);
		macchinaCaffe.setTakeable(false);

		Name nomeAcqua = new Name(Names.WATER, WordType.NOME);
		nomeAcqua.setAdmittedArticles(new String[] { "l" });
		nomeAcqua.setAdmittedPrepositions(new String[] { "con", "e" });
		GenericObject acqua = new GenericObject(nomeAcqua, Descriptions.WATER);

		Name nomePorta = new Name(Names.DOOR, WordType.NOME);
		nomePorta.setAdmittedArticles(new String[] { "la" });
		GenericObject porta = new GenericObject(nomePorta, "");

		Name nomeCaffe = new Name(Names.COFFE, WordType.NOME);
		nomeCaffe.setAdmittedPrepositions(new String[] { "con", "e" });
		nomeCaffe.setAdmittedArticles(new String[] { "il" });
		GenericObject caffe = new GenericObject(nomeCaffe, Descriptions.COFFEE);

		Name nomeleva = new Name(Names.LEVER, WordType.NOME);
		nomeleva.setAdmittedArticles(new String[] { "la" });
		nomeleva.setAdmittedPrepositions(new String[] { "quella" });
		GenericObject leva = new GenericObject(nomeleva, "");
		leva.setAdjectives(new String[] { "rossa", "gialla", "verde", "blu", "nera" });

		Name nomeCartellone = new Name(Names.SIGN, WordType.NOME);
		nomeCartellone.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject cartellone = new GenericObject(nomeCartellone, Descriptions.ADVERT);
		cartellone.setTakeable(false);

		Name nomePoster = new Name(Names.POSTER, WordType.NOME);
		nomePoster.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject poster = new GenericObject(nomePoster, Descriptions.POSTER);
		poster.setTakeable(false);

		Name nomePacco = new Name(Names.PACKAGE, WordType.NOME);
		nomePacco.setAdmittedArticles(new String[] { "il" });
		nomePacco.setAdmittedPrepositions(new String[] { "nel", "in", "dentro", "dal", "da", "con", "e" });
		GenericObject pacco = new GenericObjectContainer(nomePacco, Descriptions.PACK, 1);
		pacco.setAdjectives(new String[] { "jamazon" });

		Name nomeCd = new Name(Names.CD, WordType.NOME);
		nomeCd.setAdmittedArticles(new String[] { "il", "un" });
		nomeCd.setAdmittedPrepositions(new String[] { "con", "e" });
		GenericObject cd = new GenericObject(nomeCd, Descriptions.CD);
		cd.setAdjectives(new String[] { "neomelodico", "napoletano" });
		((GenericObjectContainer) pacco).addToContainer(cd);
		((GenericObjectContainer) pacco).setBlocked(true);

		Name nomeRivista = new Name(Names.MAGAZINE, WordType.NOME);
		nomeRivista.setAdmittedArticles(new String[] { "la", "una" });
		nomeRivista.setAdmittedPrepositions(new String[] { "con", "e" });
		GenericObject rivista = new GenericObject(nomeRivista, Descriptions.MAGAZINE);

		Name nomeGrimaldello1 = new Name(Names.PICK, WordType.NOME);
		nomeGrimaldello1.setAdmittedPrepositions(new String[] { "con", "e" });
		nomeGrimaldello1.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject grimaldello1 = new GenericObject(nomeGrimaldello1, Descriptions.PICK);
		grimaldello1.setAdjectives(new String[] { "rotto" });

		Name nomeForcina = new Name(Names.BOBBYPIN, WordType.NOME);
		nomeForcina.setAdmittedPrepositions(new String[] { "con", "e" });
		nomeForcina.setAdmittedArticles(new String[] { "la", "una" });
		GenericObject forcina = new GenericObject(nomeForcina, Descriptions.BOBBYPIN);

		Name nomeGrimaldello2 = new Name(Names.LOCKPICK, WordType.NOME);
		nomeGrimaldello2.setAdmittedPrepositions(new String[] { "con", "e" });
		nomeGrimaldello2.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject grimaldello2 = new GenericObject(nomeGrimaldello2, Descriptions.LOCKPICK);

		Name nomeComponente = new Name(Names.COMPONENT, WordType.NOME);
		nomeComponente.setAdmittedPrepositions(new String[] { "con", "e" });
		nomeComponente.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject componente = new GenericObject(nomeComponente, Descriptions.COMPONENT);

		Name nomeViti = new Name(Names.SCREWS, WordType.NOME);
		nomeViti.setAdmittedPrepositions(new String[] { "con", "e" });
		nomeViti.setAdmittedArticles(new String[] { "le" });
		GenericObject viti = new GenericObject(nomeViti, Descriptions.SCREWS);

		Name nomePropulsore = new Name(Names.ENGINE, WordType.NOME);
		nomePropulsore.setAdmittedPrepositions(new String[] { "con", "e" });
		nomePropulsore.setAdmittedArticles(new String[] { "il", "un" });
		GenericObject propulsore = new GenericObject(nomePropulsore, Descriptions.ENGINE);

		Name nomeChiaviDib = new Name(Names.KEYS, WordType.NOME);
		nomeChiaviDib.setAdmittedPrepositions(new String[] { "con", "e" });
		nomeChiaviDib.setAdmittedArticles(new String[] { "le" });
		GenericObject chiaviDib = new GenericObject(nomeChiaviDib, Descriptions.KEYS);
		chiaviDib.setAdjectives(new String[] { "dib" });

		Name nomeChiaviFis = new Name(Names.COMMON, WordType.NOME);
		nomeChiaviFis.setAdmittedArticles(new String[] { "la" });
		GenericObject chiaviFis = new GenericObject(nomeChiaviFis, Descriptions.COMMON);
		chiaviFis.setAdjectives(new String[] { "fisica" });

		Name nomeTaglierino = new Name(Names.CUTTER, WordType.NOME);
		nomeTaglierino.setAdmittedPrepositions(new String[] { "con", "e" });
		nomeTaglierino.setAdmittedArticles(new String[] { "il", "un" });
		nomeTaglierino.setAdmittedPrepositions(new String[] { "con", "e", "col" });
		GenericObject taglierino = new GenericObject(nomeTaglierino, Descriptions.CUTTER);

		Name nomeElicottero = new Name(Names.HELICOPTER, WordType.NOME);
		nomeElicottero.setAdmittedArticles(new String[] { "l" });
		nomeElicottero.setAdmittedPrepositions(new String[] { "quell" });
		GenericObject elicottero = new GenericObject(nomeElicottero, Descriptions.HELICOPTER);
		elicottero.setTakeable(false);

		Name nomeSoldi = new Name(Names.MONEY, WordType.NOME);
		nomeSoldi.setAdmittedArticles(new String[] { "i" });
		nomeSoldi.setAdmittedPrepositions(new String[] { "dei" });
		GenericObject soldi = new GenericObject(nomeSoldi, Descriptions.MONEY);

		Name nomeServer = new Name(Names.SERVER, WordType.NOME);
		nomeServer.setAdmittedArticles(new String[] { "il" });
		GenericObject server = new GenericObject(nomeServer, Descriptions.SERVER);
		server.setTakeable(false);

		Name nomeAuto = new Name(Names.CAR, WordType.NOME);
		nomeAuto.setAdmittedArticles(new String[] { "l" });
		Name aliasAuto1 = new Name("berlina", WordType.NOME);
		aliasAuto1.setAdmittedArticles(new String[] { "la" });
		Name aliasAuto2 = new Name("auto", WordType.NOME);
		aliasAuto2.setAdmittedArticles(new String[] { "l" });
		Name aliasAuto3 = new Name("macchina", WordType.NOME);
		aliasAuto3.setAdmittedArticles(new String[] { "la" });
		Name aliasAuto4 = new Name("bagagliaio", WordType.NOME);
		aliasAuto4.setAdmittedArticles(new String[] { "il" });
		Alias aliasMacchina = new Alias(new Name[] { aliasAuto1, aliasAuto2, aliasAuto3, aliasAuto4 });
		GenericObject automobile = new GenericObjectContainer(nomeAuto, Descriptions.CAR_CLOSED, aliasMacchina, 2);
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
		gameObjects.add(porta);
		executive.addObject(server);
		executive.addObject(viti);
		strada1.addObject(taglierino);
		chimica.addObject(acqua);
		strada3.addObject(cartellone);
		strada3.addObject(pacco);
		fisica1.addObject(poster);
		stradaChiusa.addObject(automobile);
		chimica.addObject(macchinaCaffe);
		fisica1.addObject(pannello);
		pizzeria2.addObject(forcina);
		strada6.addObject(grimaldello1);
		fisica3.addObject(soldi);
		Combinations combinations = new Combinations();
		combinations.addCombination(grimaldello1, forcina, grimaldello2);
		combinations.addCombination(componente, viti, propulsore);

		// EVENTI
		Event eventoCaffe = new Event(Events.COFFEE_EVENT, chimica);
		eventoCaffe.setEnigma(macchinaCaffe);
		eventoCaffe.setReward(chiaviDib);
		chimica.setEventHandler(new CoffeeEventHandler(eventoCaffe));

		Event eventoPannello = new Event(Events.PANEL_DOOR, fisica1);
		eventoPannello.setEnigma(pannello);
		fisica1.setEventHandler(new PanelEventHandler(eventoPannello));

		Event consegnaPacco = new Event(Events.PIZZERIA, pizzeria1);
		consegnaPacco.setEnigma(cd);
		consegnaPacco.setReward(caffe);
		pizzeria1.setEventHandler(new PackageEventHandler(consegnaPacco));

		Event consegnaRivista = new Event(Events.MAGAZINE, informatica);
		consegnaRivista.setEnigma(rivista);
		consegnaRivista.setReward(chiaviFis);
		informatica.setEventHandler(new MagazineEventHandler(consegnaRivista));

		Event portaFisica = new Event(Events.PHYSICS_DOOR, strada5);
		portaFisica.setEnigma(chiaviFis);
		strada5.setEventHandler(new PhysicsDoorEventHandler(portaFisica));

		Event portaDib = new Event(Events.DIB_DOOR, strada6);
		portaDib.setEnigma(chiaviDib);
		strada6.setEventHandler(new DibDoorEventHandler(portaDib));

		Event raggi = new Event(Events.COSMIC_RAYS, fisica2);
		raggi.setEnigma(propulsore);
		GenericEventHandler fisicaEvent = new PropulsorEventHandler(raggi);
		fisica2.setEventHandler(fisicaEvent);

		Event eventoElicottero = new Event(Events.PILOT, incrocio);
		eventoElicottero.setEnigma(soldi);
		eventoElicottero.addEventObject(elicottero);
		eventoElicottero.addEventCharacter(Bruno);
		incrocio.setEventHandler(new HelicopterEventHandler(eventoElicottero, fisicaEvent));

		try {
			ObjectsFile.saveObjects(gameObjects);
		} catch (IOException ex) {
			Logger.getLogger(FilesCreation.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			RoomFile.saveRoom(strada1);
		} catch (IOException ex) {
			Logger.getLogger(FilesCreation.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			CombinationsFile.saveCombinations(combinations.getCombinationsList());
		} catch (IOException ex) {
			Logger.getLogger(FilesCreation.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}