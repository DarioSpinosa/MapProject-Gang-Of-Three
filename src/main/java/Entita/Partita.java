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
import General.GenericObjectContainer;
import General.GestoreAlias;
import General.Name;
import General.ObjectType;
import General.Eventi.Evento;
import General.Eventi.GestoreEventoCaffe;
import General.Eventi.GestoreEventoPannello;
import General.Eventi.Enigmi.Caffe;
import General.Eventi.Enigmi.Pannello;
import Parser.WordType;

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
		Stanza pizzeria = new Stanza("Pizzeria", "Iamme ia famm na pizz");
		Stanza stradaChiusa = new Stanza("Strada Chiusa", "Ehy fra, hai portato la bamba?");
		Stanza fisica1 = new Stanza("Atrio di Fisica", "");
		Stanza fisica2 = new Stanza("Sala raggi cosmici", "");
		Stanza fisica3 = new Stanza("Ufficio", "Sala del trono di Volpe");
		Stanza chimica1 = new Stanza("Bar di Chimica", "Il buon vecchio bar della facoltà di chimica");
		Stanza informatica1 = new Stanza("Atrio DIB", "GUARDA! C'E' PASQUALE LOPS");
		fisica2.setAccessibile(false);

		strada1.setSopra(strada2).setDestra(esecutivo);
		esecutivo.setSinistra(strada1);
		strada2.setDestra(pizzeria).setSopra(strada3).setSotto(strada1);
		pizzeria.setSinistra(strada2);
		strada3.setSotto(strada2).setSopra(incrocio);
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

		Name nomeAutista = new Name("mimmo", WordType.NOME_PROPRIO);
		nomeAutista.setPreposizioni(new String[] { "a", "con" });
		Name aliasAutista = new Name("autista", WordType.NOME);
		aliasAutista.setArticoli(new String[] { "l" });
		aliasAutista.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio autista = new Npc(nomeAutista, new String[] {
				"Autista: Maledizione! Abbiamo preso in pieno una voragine!\nMi dispiace ma dovrai proseguire a piedi",
				"Autista: C'e' poco traffico... strano." });
		autista.setAlias(new Name[] { aliasAutista });
		strada1.addPersonaggio(autista);
		Name nomeBarista = new Name("Sedicina", WordType.NOME_PROPRIO);
		nomeBarista.setPreposizioni(new String[] { "a", "con" });
		Name aliasBarista = new Name("barista", WordType.NOME);
		aliasBarista.setArticoli(new String[] { "l" });
		aliasBarista.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio barista = new Npc(nomeAutista,
				new String[] { "Sono il barista Sedicina", "Tu di qua non esci finche non hai fatto un caffe" });
		barista.setAlias(new Name[] { aliasBarista });
		chimica1.addPersonaggio(barista);
		/*
		 * pizzeria.addPersonaggio(new Npc("cameriere")); fisica3.addPersonaggio(new
		 * Npc("giacomo volpe")); chimica1.addPersonaggio(new Npc("bruno"));
		 * informatica1.addPersonaggio(new Npc("antonino"));
		 */

		Name nomeCD = new Name("CD", WordType.NOME);
		nomeCD.setArticoli(new String[] { "il", "un" });
		nomeCD.setPreposizioni(new String[] { "quel" });
		GenericObject CD = new GenericObject(nomeCD, "un CD di musica neomelodica", ObjectType.NORMAL,
				new GestoreAlias(new Name[] {}));

		Name nomePacco = new Name("pacco", WordType.NOME);
		nomePacco.setArticoli(new String[] { "il", "un" });
		nomePacco.setPreposizioni(new String[] { "nel", "in", "da", "dal" });
		GenericObject pacco = new GenericObjectContainer(nomePacco, "un pacco jamazon", ObjectType.CONTAINER);
		pacco.setAggettivi(new String[] { "jamazon" });
		pacco.setPrendibile(true);
		((GenericObjectContainer) (pacco)).addToContainer(CD);

		Name nomeTorta = new Name("torta", WordType.NOME);
		nomeTorta.setArticoli(new String[] { "la", "una" });
		nomeTorta.setPreposizioni(new String[] { "quella" });
		GenericObject torta = new GenericObject(nomeTorta, "una deliziosa torta", ObjectType.EATABLE,
				new GestoreAlias(new Name[] {}));
		Name nomePizza = new Name("pizza", WordType.NOME);
		nomePizza.setArticoli(new String[] { "la", "una" });
		nomePizza.setPreposizioni(new String[] { "quella" });
		GenericObject pizza = new GenericObject(nomePizza, "una squisita pizza", ObjectType.EATABLE,
				new GestoreAlias(new Name[] {}));
		Name nomeBirra = new Name("birra", WordType.NOME);
		nomeBirra.setArticoli(new String[] { "la", "una" });
		nomeBirra.setPreposizioni(new String[] { "quella" });
		GenericObject birra = new GenericObject(nomeBirra, "una pregiata birra peroni", ObjectType.DRINKABLE,
				new GestoreAlias(new Name[] {}));
		Name nomeBaule = new Name("baule", WordType.NOME);
		nomeBaule.setArticoli(new String[] { "il", "un" });
		nomeBaule.setPreposizioni(new String[] { "nel", "in", "da", "dal" });
		GenericObject baule = new GenericObjectContainer(nomeBaule, "un baule", ObjectType.CONTAINER);
		baule.setAggettivi(new String[] { "antico" });
		baule.setPrendibile(false);
		((GenericObjectContainer) (baule)).addToContainer(torta);
		Name nomeCassa = new Name("cassa", WordType.NOME);
		nomeCassa.setArticoli(new String[] { "la", "una" });
		nomeCassa.setPreposizioni(new String[] { "nella", "dalla" });
		baule.setGestoreAlias((new Name[] { nomeCassa }));
		GenericObject spada = new GenericObject(new Name("spada", WordType.NOME), "una spada", ObjectType.NORMAL);
		Name nomeAcqua = new Name("acqua", WordType.NOME);
		nomeAcqua.setArticoli(new String[] { "l" });
		nomeAcqua.setPreposizioni(new String[] { "quella" });
		Name NomeCaffe = new Name("caffe", WordType.NOME);
		NomeCaffe.setArticoli(new String[] { "il" });
		NomeCaffe.setPreposizioni(new String[] { "quello" });
		GenericObject acqua = new GenericObject(nomeAcqua, "Un bicchiere d'acqua", ObjectType.EVENT);
		GenericObject caffe = new GenericObject(NomeCaffe, "E' del caffe in polvere, magari se lo sniffi muori",
				ObjectType.EVENT);
		Name leva = new Name("leva", WordType.NOME);
		GenericObject levaPannello = new GenericObject(leva, "una leva", ObjectType.NORMAL);
		levaPannello.setAggettivi(new String[] { "rossa", "gialla", "verde", "blu", "nera" });

		oggetti.add(acqua);
		chimica1.addOggetto(acqua);
		oggetti.add(caffe);
		chimica1.addOggetto(caffe);
		oggetti.add(levaPannello);

		Evento preparaCaffe = new Evento(
				"\nSono le 8 di mattina, dovresti preparati \nun bel caffe per iniziare questa giornata di merda"
						+ "\n\n" + protagonista.getNome().getName() + ": Mi puo' fare un caffe caldo per favore?"
						+ "\nSedicina: Non ho intenzione di fare un bel nulla, preparatelo da solo coglione");
		Name NameCaffe = new Name("macchinetta", WordType.NOME);
		NameCaffe.setArticoli(new String[] { "la", "una" });
		NameCaffe.setPreposizioni(new String[] { "nella" });
		GenericObject macchinaCaffe = new Caffe(NameCaffe, "Una macchina per fare il cazzo di caffe");
		macchinaCaffe.setPrendibile(false);
		preparaCaffe.addEnigma(macchinaCaffe);
		chimica1.setGestoreEvento(new GestoreEventoCaffe(preparaCaffe, chimica1));
		Evento eventoPannello = new Evento("\nLa porta successiva e' bloccata da qualche meccanismo\n");
		Name NamePannello = new Name("pannello", WordType.NOME);
		NamePannello.setArticoli(new String[] { "il", });
		NamePannello.setPreposizioni(new String[] { "del" });
		GenericObject pannello = new Pannello(NamePannello,
				"Un pannello composto da leve da 5 colori diversi\nRosso,Giallo,Verde,Blu e Nero");
		pannello.setPrendibile(false);
		eventoPannello.addEnigma(pannello);
		fisica1.setGestoreEvento(new GestoreEventoPannello(eventoPannello, fisica1));

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
