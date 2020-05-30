package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import Resources.Dialogs;

public class GestoreEventoRivista extends GenericGestoreEvento {

	public GestoreEventoRivista(Evento evento, Stanza stanza) {
		super(evento, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p) {
		if (p.getInventario().contains(missione.getEnigma())) {
			((Npc) stanza.getPersonaggi().get(0)).setDialogo(Dialogs.JANITOR_B);
			p.removeOggetto(missione.getEnigma());
			p.addOggetto(ricompensa);
		}

	}

}
