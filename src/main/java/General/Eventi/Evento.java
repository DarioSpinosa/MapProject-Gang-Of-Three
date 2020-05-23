package General.Eventi;

import java.util.ArrayList;

import General.GenericObject;

public class Evento {

	private String descrizione;
	private ArrayList<GenericObject> oggettiEvento = new ArrayList<GenericObject>();

	public Evento(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void addOggettoEvento(GenericObject o) {
		oggettiEvento.add(o);

	}

	public  ArrayList<GenericObject> getOggettiEvento(){
		return oggettiEvento;
	}
}
