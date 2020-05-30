package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Protagonista;

public class GestoreEventoPortaFis extends GenericGestoreEvento{

	public GestoreEventoPortaFis(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p) {
		if(p.getInventario().contains(missione.getEnigma()))
			p.getInventario().removeFromContainer((missione.getEnigma()));
			stanza.getSinistra().setAccessibile(true);
	}
}
