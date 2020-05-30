package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Protagonista;
import General.GenericObject;

public abstract class GenericGestoreEvento {

	protected Evento missione;
	protected Stanza stanza;
	protected GenericObject ricompensa = null;
	protected boolean iniziato = false;

	public GenericGestoreEvento() {
	}

	public GenericGestoreEvento(Evento m, Stanza stanza) {
		missione = m;
		this.stanza = stanza;
	}

	public String iniziaEvento() {

		String temp = "";

		if (iniziato == false) {
			temp = missione.getDescrizione();
			iniziato = true;

		}

		return temp;
	}

	public Evento getEvento() {
		return missione;
	}

	public void setRicompensa(GenericObject o ) {
		ricompensa = o;
	}

	public boolean getIniziato() {
		return iniziato;
	}

	public abstract void terminaEvento(Protagonista p);

}
