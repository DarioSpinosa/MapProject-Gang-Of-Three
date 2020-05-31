package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Protagonista;
import General.GenericObjectContainer;

public class GestoreEventoAuto extends GenericGestoreEvento{

	public GestoreEventoAuto(Evento m, Stanza stanza) {
		missione = m;
		this.stanza = stanza;
	}

	@Override
	public void terminaEvento(Protagonista p) {
		if(p.isInInventario(missione.getEnigma())) {
			setCompletato();
			p.removeOggetto(missione.getEnigma());
			((GenericObjectContainer)stanza.getOggetto("automobile")).setBloccato(false);
			((GenericObjectContainer)stanza.getOggetto("automobile")).open();
		}
	}

}
