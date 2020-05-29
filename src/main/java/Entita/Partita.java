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
import General.GenericObject;
import General.Name;
import General.ObjectType;
import General.Eventi.Evento;
import General.Eventi.GestoreEventoCaffe;
import General.Eventi.GestoreEventoPacco;
import General.Eventi.GestoreEventoPannello;
import General.Eventi.Enigmi.Caffe;
import General.Eventi.Enigmi.Pannello;
import Parser.WordType;
import Resources.Descriptions;
import Resources.Dialogs;

public class Partita {

	private Stanza stanzaCorrente;
	private Personaggio protagonista;
	private ArrayList<GenericObject> oggetti = new ArrayList<>();

	public ArrayList<GenericObject> getOggetti() {
		return oggetti;
	}

	public Partita() {
		protagonista = new Protagonista(new Name("Pippo", WordType.NOME_PROPRIO));

		Stanza strada1 = new Stanza("Via Amendola, primo settore",
				"Scendi dal bus. L'atmosfera e' strana, sembra che il tempo si sia fermato. \n"
						+ "Non vedi nessuno in giro, ne' in macchina ne' a piedi. \n"
						+ "Il bus ha preso una buca,forse e' meglio parlare con l'autista ");
		Stanza strada2 = new Stanza("Via Amendola, secondo settore", "Prosegui su via Amendola. \n"
				+ "Alla tua destra c'e' l'Executive Center, dietro di te il bus ancora fumante. ");
		Stanza strada3 = new Stanza("Via Amendola, terzo settore", "Sei a meta' di via Amendola. \n"
				+ "Alla tua sinistra c'e' un cartellone pubblicitario, a destra una pizzeria ");
		Stanza strada4 = new Stanza("Strada Nord 1", "");
		Stanza strada5 = new Stanza("Strada Nord 2", "");
		Stanza strada6 = new Stanza("Strada Nord 3", "");
		Stanza incrocio = new Stanza("Incrocio", "");
		Stanza esecutivo = new Stanza("Executive Center, Uffici",
				"Ti sei intrufolato in uno degli uffici dell'Executive Center \n"
						+ "C'e' un imponente server al centro della stanza ma sembra spento.");
		Stanza pizzeria1 = new Stanza("Pizzeria Esterno", "");
		Stanza pizzeria2 = new Stanza("Pizzeria Interno", "Sei entrato in un'ampia sala di una pizzeria.\nNon c'e' nessuno, se non il pizzaiolo");
		Stanza stradaChiusa = new Stanza("Strada Chiusa", "Ehy fra, hai portato la bamba?");
		Stanza fisica1 = new Stanza("Atrio di Fisica", "");
		Stanza fisica2 = new Stanza("Sala raggi cosmici", "");
		Stanza fisica3 = new Stanza("Ufficio", "Sala del trono di Volpe");
		Stanza chimica1 = new Stanza("Bar di Chimica", "Il buon vecchio bar della facoltà di chimica");
		Stanza informatica1 = new Stanza("Atrio DIB", "GUARDA! C'E' PASQUALE LOPS");
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

		Name nomeAutista =
				new Name("Mimmo", WordType.NOME_PROPRIO);
		nomeAutista.setPreposizioni(new String[] { "a", "con" });
		Name aliasAutista = new Name("autista", WordType.NOME);
		aliasAutista.setArticoli(new String[] { "l" });
		aliasAutista.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio autista = new Npc(nomeAutista, Dialogs.BUSDRIVER);
		autista.setAlias(new Name[] { aliasAutista });
		strada1.addPersonaggio(autista);
		Name nomeBarista =
				new Name("Cannavacciuolo", WordType.NOME_PROPRIO);
		nomeBarista.setPreposizioni(new String[] { "a", "con" });
		Name aliasBarista = new Name("antonino", WordType.NOME);
		aliasBarista.setArticoli(new String[] { "il" });
		aliasBarista.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio barista = new Npc(nomeBarista, Dialogs.CANNAVACCIUOLO_A);
		barista.setAlias(new Name[] { aliasBarista });
		chimica1.addPersonaggio(barista);
		Name nomePizzaiolo =
				new Name("Almerino", WordType.NOME_PROPRIO);
		nomePizzaiolo.setPreposizioni(new String[] { "a", "con" });
		Name aliasPizzaiolo = new Name("pizzaiolo", WordType.NOME);
		aliasBarista.setArticoli(new String[] { "il" });
		aliasBarista.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio pizzaiolo = new Npc(nomePizzaiolo, Dialogs.BAKER_A);
		pizzaiolo.setAlias(new Name[] { aliasPizzaiolo });
		pizzeria1.addPersonaggio(pizzaiolo);


		Name nomeAcqua = new Name("acqua", WordType.NOME);
		nomeAcqua.setArticoli(new String[] { "l" });
		nomeAcqua.setPreposizioni(new String[] { "quella" });
		GenericObject acqua =
				new GenericObject(nomeAcqua, Descriptions.WATER, ObjectType.EVENT);

		Name nomeCaffe = new Name("caffe", WordType.NOME);
		nomeCaffe.setArticoli(new String[] { "il" });
		nomeCaffe.setPreposizioni(new String[] { "quello" });
		GenericObject caffe =
				new GenericObject(nomeCaffe, Descriptions.COFFEE, ObjectType.EVENT);

		Name nomeleva = new Name("leva", WordType.NOME);
		GenericObject leva =
				new GenericObject(nomeleva, "", ObjectType.NORMAL);
		leva.setAggettivi(new String[] { "rossa", "gialla", "verde", "blu", "nera" });

		Name nomeCartellone = new Name("cartellone", WordType.NOME);
		nomeCartellone.setArticoli(new String[] { "il" });
		GenericObject cartellone =
				new GenericObject(nomeCartellone, Descriptions.ADVERT, ObjectType.NORMAL);
		cartellone.setPrendibile(false);

		Name nomePacco = new Name("pacco", WordType.NOME);
		nomePacco.setArticoli(new String[] { "il" });
		GenericObject pacco =
				new GenericObject(nomePacco, Descriptions.PACK, ObjectType.CONTAINER);
		pacco.setAggettivi(new String[] {"jamazon"});

		Name nomeCd = new Name("cd", WordType.NOME);
		nomePacco.setArticoli(new String[] { "il", "un" });
		GenericObject cd =
				new GenericObject(nomeCd, Descriptions.CD, ObjectType.NORMAL);
		pacco.setAggettivi(new String[] {"musicale", "napoletano"});

		oggetti.add(leva);
		oggetti.add(acqua);
		chimica1.addOggetto(acqua);
		oggetti.add(caffe);
		chimica1.addOggetto(caffe);
		oggetti.add(cartellone);
		strada3.addOggetto(cartellone);
		oggetti.add(pacco);
		strada3.addOggetto(pacco);

		Evento preparaCaffe = new Evento(
				"\nSono le 8 di mattina, dovresti preparati \nun bel caffe per iniziare questa giornata di merda"
						+ "\n\n" + protagonista.getNome().getName() + ": Mi puo' fare un caffe caldo per favore?" +
						"\nBarista Sedicina: Non ho intenzione di fare un bel nulla, preparatelo da solo coglione");
		Name NameCaffe = new Name("macchinetta", WordType.NOME);
		NameCaffe.setArticoli(new String[] { "la", "una" });
		NameCaffe.setPreposizioni(new String[] { "nella"});
		GenericObject macchinaCaffe = new Caffe(NameCaffe, "Una macchina per fare il cazzo di caffe");
		macchinaCaffe.setPrendibile(false);
		preparaCaffe.addEnigma(macchinaCaffe);
		chimica1.setGestoreEvento(new GestoreEventoCaffe(preparaCaffe, chimica1));

		Evento eventoPannello = new Evento(
				"\nLa porta successiva e' bloccata da qualche meccanismo\n");
		Name NamePannello = new Name("pannello", WordType.NOME);
		NamePannello.setArticoli(new String[] { "il",});
		NamePannello.setPreposizioni(new String[] { "del"});
		GenericObject pannello = new Pannello(NamePannello, "Un pannello composto da leve da 5 colori diversi\nRosso,Giallo,Verde,Blu e Nero");
		pannello.setPrendibile(false);
		eventoPannello.addEnigma(pannello);
		fisica1.setGestoreEvento(new GestoreEventoPannello(eventoPannello, fisica1));

		Evento consegnaPacco = new Evento("\nLa pizzeria ha una barricata di legno che ti impedisce l'accesso\n" +
				"Senti una voce: Ehi tu! Sono il pizzaiolo, portami il contenuto di quel pacco Jamazon e ti faro' entrare");
		consegnaPacco.addEnigma(pacco);
		pizzeria1.setGestoreEvento(new GestoreEventoPacco(consegnaPacco, pizzeria1));

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
