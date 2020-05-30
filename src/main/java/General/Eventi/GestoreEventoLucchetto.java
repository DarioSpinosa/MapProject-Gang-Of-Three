package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Protagonista;
import General.GenericObjectContainer;
import General.Eventi.Enigmi.Lucchetto;

public class GestoreEventoLucchetto extends GenericGestoreEvento{

	public GestoreEventoLucchetto(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p) {
		if(((Lucchetto)(missione.getEnigma())).getCompletato()){
			GenericObjectContainer car = ((GenericObjectContainer)stanza.getOggetto("automobile"));
			car.setBloccato(false);
		}
	}
}
