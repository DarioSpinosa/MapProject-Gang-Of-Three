package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import Entita.Characters.Personaggio;
import Entita.Characters.Protagonista;
import General.GenericObject;

public abstract class GenericGestoreEvento {

	protected Evento missione;
	protected Stanza stanza;
	protected GenericObject ricompensa = null;
	protected boolean iniziato = false;
	protected boolean completato = false;
	protected ArrayList<GenericObject> oggetti = new ArrayList<GenericObject>();
	protected ArrayList<Personaggio> personaggi = new ArrayList<Personaggio>();

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

			if(oggetti.size() != 0) {
				for(GenericObject oggetto: oggetti) {
					stanza.addOggetto(oggetto);
				}
			}

			if(personaggi.size() != 0) {
				for(Personaggio p: personaggi) {
					stanza.addPersonaggio(p);
				}
			}

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

	public boolean getCompletato() {
		return completato;
	}

	protected void setCompletato() {
		completato = true;
	}

	public void setOggetto(GenericObject oggetto) {
		oggetti.add(oggetto);
	}

	public void setPersonaggio(Personaggio p) {
		personaggi.add(p);
	}


	public abstract void terminaEvento(Protagonista p);

}
