package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import General.GenericObject;
import General.GestoreMessaggiEssentials;


public abstract class GenericGestoreEvento {

	protected Evento missione;
	protected Stanza stanza;
	protected boolean completato = false;
	protected boolean presentazione = false;
	protected GestoreMessaggiEssentials stampante;

	public GenericGestoreEvento() {}

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

	public void iniziaEvento(ArrayList<GenericObject> objs) {

		if(presentazione == false) {
			stampante.stampaMessaggio(missione.getDescrizione());
			presentazione = true;
		}

		ArrayList<GenericObject> oggetti = missione.getOggettiEvento();

		if(oggetti.size() != 0) {

			int i = 0;

			do{
				stanza.addOggetto(oggetti.get(i));
				objs.add(oggetti.get(i));

				i++;

			}while(i < oggetti.size() );
		}


	}

	public abstract void terminaEvento();

}
