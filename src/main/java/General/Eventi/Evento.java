package General.Eventi;

import General.GenericObject;

public class Evento {

	private String descrizione;
	private GenericObject enigma;

	public Evento(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void addEnigma(GenericObject g) {
		enigma = g;

	}

	public GenericObject getEnigma(){
		return enigma;
	}
}
