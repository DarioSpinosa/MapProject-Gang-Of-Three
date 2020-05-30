package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import Entita.Characters.Protagonista;
import General.GenericObject;
import General.Eventi.Enigmi.Enigma;

public abstract class GenericGestoreEvento {

	protected Evento missione;
	protected Stanza stanza;
	protected boolean iniziato = false;

	public GenericGestoreEvento() {
	}

	public GenericGestoreEvento(Evento m, Stanza stanza) {
		missione = m;
		this.stanza = stanza;
	}

	public String iniziaEvento(ArrayList<GenericObject> objs) {

		String temp = "";

		if (iniziato == false) {
			temp = missione.getDescrizione();
			iniziato = true;

			if(missione.getEnigma() != null && missione.getEnigma() instanceof Enigma ) {
				GenericObject enigma = missione.getEnigma();

				stanza.addOggetto(enigma);
				objs.add(enigma);
			}

		}

		return temp;
	}

	public Evento getEvento() {
		return missione;
	}

	public abstract void terminaEvento(Protagonista p, ArrayList<GenericObject> objs);

}
