package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import General.GenericObject;

public abstract class GenericGestoreEvento {

	protected Evento missione;
	protected Stanza stanza;
	protected boolean completato = false;
	protected boolean presentazione = false;

	public GenericGestoreEvento() {
	}

	public GenericGestoreEvento(Evento m, Stanza stanza) {
		missione = m;
		this.stanza = stanza;
	}

	public void setCompletato() {
		completato = true;
	}

	public boolean getCompletato() {
		return completato;
	}

	public String iniziaEvento(ArrayList<GenericObject> objs) {

		String temp = "";

		if (presentazione == false) {
			temp = missione.getDescrizione();
			presentazione = true;

			GenericObject enigma = missione.getEnigma();

			stanza.addOggetto(enigma);
			objs.add(enigma);

			if(stanza.getSopra() != null)
				stanza.getSopra().setAccessibile(false);
			if(stanza.getSotto() != null)
				stanza.getSotto() .setAccessibile(false);
			if(stanza.getSinistra() != null)
				stanza.getSinistra().setAccessibile(false);
			if(stanza.getDestra() != null)
				stanza.getDestra().setAccessibile(false);
		}

		return temp;
	}

	public Evento getEvento() {
		return missione;
	}

	public abstract void terminaEvento(ArrayList<GenericObject> objs);

}
