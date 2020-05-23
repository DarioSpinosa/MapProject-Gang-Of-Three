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
import General.Eventi.GestoreEventoProva;
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

		Stanza strada1 = new Stanza("Strada Sud 1", "MO E C'IE TUTT STU TRAFFC ");
		Stanza strada2 = new Stanza("Strada Sud 2", "MO E C'IE TUTT STU TRAFFC ");
		Evento macchinaCaffe = new Evento(
				"Sono le 8 di mattina, dovresti preparati un bel caffe per iniziare questa giornata di merda");
		macchinaCaffe.addOggettoEvento(
				new Caffe(new Name("macchinetta", WordType.NOME), "E' una macchina per fare il cazzo di caffe"));
		strada2.setGestoreEvento(new GestoreEventoProva(macchinaCaffe, strada2));
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
		Personaggio autista = new Npc(nomeAutista, new String[] { "Sono l'autista", "Questo piato", "é una MERDA" });
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
		GenericObject torta = new GenericObject(nomeTorta, "una deliziosa torta", new GestoreAlias(new Name[] {}));

		Name nomeBaule = new Name("baule", WordType.NOME);
		nomeBaule.setArticoli(new String[] { "il", "un" });
		nomeBaule.setPreposizioni(new String[] { "nel", "in", "da", "dal" });
		GenericObject baule = new GenericObjectContainer(nomeBaule, "un baule");
		Name nomeCassa = new Name("cassa", WordType.NOME);
		nomeCassa.setArticoli(new String[] { "la", "una" });
		nomeCassa.setPreposizioni(new String[] { "nella", "dalla" });
		baule.setGestoreAlias((new Name[] { nomeCassa }));
		GenericObject spada = new GenericObject(new Name("spada", WordType.NOME), "una spada");
		Name nomeAcqua = new Name("acqua", WordType.NOME);
		nomeAcqua.setArticoli(new String[] { "l'" });
		nomeAcqua.setPreposizioni(new String[] { "quella" });
		Name NomeCaffe = new Name("caffe", WordType.NOME);
		NomeCaffe.setArticoli(new String[] { "un" });
		NomeCaffe.setPreposizioni(new String[] { "quello" });
		GenericObject acqua = new GenericObject(nomeAcqua, "un bicchiere d'acqua");
		GenericObject caffe = new GenericObject(NomeCaffe, "caffe in polvere");

		oggetti.add(spada);
		strada1.addOggetto(spada);
		oggetti.add(baule);
		strada1.addOggetto(baule);
		oggetti.add(torta);
		strada1.addOggetto(torta);
		Combinations.addCombination(torta, baule, spada);
		oggetti.add(acqua);
		oggetti.add(caffe);
		strada2.addOggetto(acqua);
		strada2.addOggetto(caffe);

		stanzaCorrente = strada1;
	}

	public void creaProtagonista(Name nome) {
		protagonista = new Protagonista(nome);
	}

	public String getNomeProtagonista() {
		return protagonista.getNome().getName();
	}

	public int getVitaProtagonista() {
		return protagonista.getHealtPoints();
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
