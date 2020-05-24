/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita;

import java.util.ArrayList;

import Entita.Characters.Personaggio;
import General.GenericObject;
import General.GenericObjectContainer;
import General.Name;
import General.Eventi.GenericGestoreEvento;
import Parser.WordType;

/**
 *
 * @author DarioSpinosa
 */

public class Stanza {

	// attributi
	private final String nome;
	private final String descrizione;
	private Stanza sopra = null;
	private Stanza sotto = null;
	private Stanza destra = null;
	private Stanza sinistra = null;
	private GenericGestoreEvento evento;
	private GenericObjectContainer oggetti = new GenericObjectContainer(new Name("Oggetti", WordType.NOME), "", 0);
	private ArrayList<Personaggio> characters = new ArrayList<>();

	// costruttore
	public Stanza(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
	}

	// METODI SETTER
	public Stanza setSopra(Stanza sopra) {
		this.sopra = sopra;
		return this;
	}

	public Stanza setSotto(Stanza sotto) {
		this.sotto = sotto;
		return this;
	}

	public Stanza setDestra(Stanza destra) {
		this.destra = destra;
		return this;
	}

	public Stanza setSinistra(Stanza sinistra) {
		this.sinistra = sinistra;
		return this;
	}

	// METODI GETTER
	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Stanza getSopra() {
		return sopra;
	}

	public Stanza getSotto() {
		return sotto;
	}

	public Stanza getDestra() {
		return destra;
	}

	public Stanza getSinistra() {
		return sinistra;
	}

	public GenericObjectContainer getOggetti() {
		return oggetti;
	}

	public void addPersonaggio(Personaggio p) {
		characters.add(p);
	}

	public void removePersonaggio(Personaggio p) {
		characters.remove(p);
	}

	public void addOggetto(GenericObject o) {
		oggetti.addToContainer(o);
	}

	public void removeOggetto(GenericObject o) {
		oggetti.removeFromContainer(o);
	}

	public void setGestoreEvento(GenericGestoreEvento e) {
		evento = e;
	}

	public Personaggio getPersonaggio(int i) {
		return characters.get(i);
	}

	public Personaggio getPersonaggio(Personaggio personaggio) {
		return characters.get(characters.indexOf(personaggio));
	}

	public ArrayList<Personaggio> getPersonaggi() {
		return characters;
	}

	public GenericObject getOggetti(int i) {
		return oggetti.get(i);
	}

	public int trovaIndice(GenericObject oggetto) {
		return oggetti.getContainer().indexOf(oggetto);
	}

    public GenericGestoreEvento getGestoreEvento() {

    	GenericGestoreEvento returned = null;
    	if(evento != null && evento.getCompletato() == false)
    		returned = evento;

    	  return returned;
    }

	public GenericObject getOggetto(GenericObject oggetto) {
		for (GenericObject obj : oggetti.getContainer()) {
			if (obj.equals(oggetto)) {
				return obj;
			}
		}
		return null;
	}
}
