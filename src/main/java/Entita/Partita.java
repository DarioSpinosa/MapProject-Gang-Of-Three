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
import General.Eventi.Evento;
import General.Eventi.GestoreEventoCaffe;
import General.Eventi.Enigmi.Caffe;
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


		Stanza strada1 = new Stanza("Strada Sud 1",
				"Scendi dal bus. L'atmosfera e' strana, sembra che il tempo si sia fermato. \n"
						+ "Non vedi nessuno in giro, ne' in macchina ne' a piedi. \n"
						+ "Il bus ha preso una buca, forse e' meglio andare a vedere cosa e' successo ");
		Stanza strada2 = new Stanza("Strada Sud 2", "MO E C'IE TUTT STU TRAFFC ");
		Evento macchinaCaffe = new Evento(
				"\nSono le 8 di mattina, dovresti preparati \nun bel caffe per iniziare questa giornata di merda"
				+ "\nGuarda, sul tavolo vi è una macchina per il caffe");
		Name NameCaffe = new Name("macchinetta", WordType.NOME);
		NameCaffe.setArticoli(new String[] { "la", "una" });
		NameCaffe.setPreposizioni(new String[] { "nella"});
		macchinaCaffe.addEnigma(new Caffe(NameCaffe, "E' una macchina per fare il cazzo di caffe"));
		strada2.setGestoreEvento(new GestoreEventoCaffe(macchinaCaffe, strada2));
		Stanza strada3 = new Stanza("Strada Sud 3", "MO E C'IE TUTT STU TRAFFC ");
		Stanza strada4 = new Stanza("Strada Est", "");
		Stanza strada5 = new Stanza("Strada Ovest", "");
		Stanza strada6 = new Stanza("Strada Nord 1", "");
		Stanza strada7 = new Stanza("Strada Nord 2", "");
		Stanza strada8 = new Stanza("Strada Nord 3", "");
		Stanza rotonda = new Stanza("Rotonda", "");
		Stanza esecutivo = new Stanza("Executive", "Boh Fabio mettila tu");
		Stanza pizzeria = new Stanza("Pizzeria", "Iamme ia famm na pizz");
		Stanza vicoloCieco = new Stanza("Vicolo cieco", "Ehy fra, hai portato la bamba?");
		Stanza parcheggio = new Stanza("Parcheggio", "NON C'E' MAI UN CAZZO DI POSTO, FANCULO USO QUELLO DEL DISABILE");
		Stanza fisica1 = new Stanza("Atrio di Fisica", "");
		Stanza fisica2 = new Stanza("Sala raggi cosmici", "");
		Stanza fisica3 = new Stanza("Ufficio", "Sala del trono di Volpe");
		Stanza chimica1 = new Stanza("Bar di Chimica", "Un caffe al glicerolo grazie");
		Stanza chimica2 = new Stanza("Sala Pozioni", "Insegnante Severus Piton");
		Stanza informatica1 = new Stanza("Atrio DIB", "GUARDA! C'E' PASQUALE LOPS");
		Stanza informatica2 = new Stanza("LABORATIO P.C.", "HACKER-MAN TIME");

		strada1.setSopra(strada2).setDestra(esecutivo);
		esecutivo.setSinistra(strada1);
		strada2.setDestra(pizzeria).setSopra(strada3).setSotto(strada1);
		pizzeria.setSinistra(strada2);
		strada3.setSotto(strada2).setSopra(rotonda);
		rotonda.setSotto(strada3).setSinistra(strada4).setDestra(strada5).setSopra(strada6);
		strada4.setDestra(rotonda).setSinistra(vicoloCieco);
		vicoloCieco.setDestra(strada4);
		strada5.setSinistra(rotonda).setDestra(parcheggio);
		parcheggio.setSinistra(strada5);
		strada6.setSotto(rotonda).setSopra(strada7);
		strada7.setSotto(strada6).setSinistra(fisica1).setDestra(chimica1).setSopra(strada8);
		fisica1.setSinistra(fisica2).setDestra(strada7);
		fisica2.setSopra(fisica3).setDestra(fisica1);
		fisica3.setSotto(fisica2);
		chimica1.setSinistra(strada7).setDestra(chimica2);
		chimica2.setSinistra(chimica1);
		strada8.setSotto(strada7).setDestra(informatica1);
		informatica1.setSinistra(strada8).setDestra(informatica2);
		informatica2.setSinistra(informatica1);

		Name nomeAutista = new Name("mimmo", WordType.NOME_PROPRIO);
		nomeAutista.setPreposizioni(new String[] { "a", "con" });
		Name aliasAutista = new Name("autista", WordType.NOME);
		aliasAutista.setArticoli(new String[] { "l" });
		aliasAutista.setPreposizioni(new String[] { "con", "a", "all", "ad" });
		Personaggio autista = new Npc(nomeAutista, new String[] { "Sono l'autista", "Questo piato", "é una MERDA", "Dario Spinosa" });
		autista.setAlias(new Name[] { aliasAutista });
		strada1.addPersonaggio(autista);
		/*
		 * pizzeria.addPersonaggio(new Npc("cameriere")); fisica3.addPersonaggio(new
		 * Npc("giacomo volpe")); chimica1.addPersonaggio(new Npc("bruno"));
		 * informatica1.addPersonaggio(new Npc("antonino"));
		 */


		Name nomeTorta = new Name("torta", WordType.NOME);
		nomeTorta.setArticoli(new String[] { "la", "una" });
		nomeTorta.setPreposizioni(new String[] { "quella" });
		GenericObject torta = new GenericObject(nomeTorta, "una deliziosa torta", 1, new GestoreAlias(new Name[] {}));
		Name nomePizza = new Name("pizza", WordType.NOME);
		nomePizza.setArticoli(new String[] { "la", "una" });
		nomePizza.setPreposizioni(new String[] { "quella" });
		GenericObject pizza = new GenericObject(nomePizza, "una squisita pizza", 1 , new GestoreAlias(new Name[]{}));
		Name nomeBirra = new Name("birra", WordType.NOME);
		nomeBirra.setArticoli(new String[]{"la","una"});
		nomeBirra.setPreposizioni(new String[] {"quella"});
		GenericObject birra = new GenericObject(nomeBirra, "una pregiata birra peroni", 2, new GestoreAlias(new Name[]{}));
		Name nomeBaule = new Name("baule", WordType.NOME);
		nomeBaule.setArticoli(new String[] { "il", "un" });
		nomeBaule.setPreposizioni(new String[] { "nel", "in", "da", "dal" });
		GenericObject baule = new GenericObjectContainer(nomeBaule, "un baule", 0);
		((GenericObjectContainer)(baule)).addToContainer(torta);
		Name nomeCassa = new Name("cassa", WordType.NOME);
		nomeCassa.setArticoli(new String[] { "la", "una" });
		nomeCassa.setPreposizioni(new String[] { "nella", "dalla" });
		baule.setGestoreAlias((new Name[] { nomeCassa }));
		GenericObject spada = new GenericObject(new Name("spada", WordType.NOME), "una spada", 2);
		Name nomeAcqua = new Name("acqua", WordType.NOME);
		nomeAcqua.setArticoli(new String[] { "l'" });
		nomeAcqua.setPreposizioni(new String[] { "quella" });
		Name NomeCaffe = new Name("caffe", WordType.NOME);
		NomeCaffe.setArticoli(new String[] { "il" });
		NomeCaffe.setPreposizioni(new String[] { "quello" });
		GenericObject acqua = new GenericObject(nomeAcqua, "Un bicchiere d'acqua", 1);
		GenericObject caffe = new GenericObject(NomeCaffe, "E' del caffe in polvere, magari se lo sniffi muori", 1);

		Name nomeBottoneRosso = new Name("bottone", WordType.NOME);
		GenericObject bottoneRosso = new GenericObject(nomeBottoneRosso, "un bottone rosso!", 3);
		bottoneRosso.setAggettivi(new String[]{"rosso"});
		Name nomeBottoneBlu = new Name("bottone", WordType.NOME);
		GenericObject bottoneBlu = new GenericObject(nomeBottoneBlu, "un bottone blu!", 3);
		bottoneBlu.setAggettivi(new String[]{"blu"});
		Name nomeBottoneVerde = new Name("bottone", WordType.NOME);
		GenericObject bottoneVerde = new GenericObject(nomeBottoneVerde, "un bottone verde!", 3);
		bottoneVerde.setAggettivi(new String[]{"verde"});

		oggetti.add(bottoneVerde);
		strada1.addOggetto(bottoneVerde);
		oggetti.add(bottoneRosso);
		strada1.addOggetto(bottoneRosso);
		oggetti.add(bottoneBlu);
		strada1.addOggetto(bottoneBlu);
		oggetti.add(birra);
		strada1.addOggetto(birra);
		oggetti.add(pizza);
		strada1.addOggetto(pizza);
		oggetti.add(spada);
		strada1.addOggetto(spada);
		oggetti.add(baule);
		strada1.addOggetto(baule);
		oggetti.add(torta);
		Combinations.addCombination(torta, baule, spada);
		oggetti.add(acqua);
		oggetti.add(caffe);
		strada2.addOggetto(acqua);
		strada2.addOggetto(caffe);

		stanzaCorrente = strada1;
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
