package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import Resources.Dialogs;

public class GestoreEventoPropulsore extends GenericGestoreEvento{

	public GestoreEventoPropulsore(Evento m, Stanza stanza) {
		missione = m;
		this.stanza = stanza;
	}

	@Override
	public void terminaEvento(Protagonista p) {
		if(p.getInventario().contains(missione.getEnigma())) {
			setCompletato();
			((Npc)stanza.getPersonaggi().get(0)).setDialogo(Dialogs.VOLPE_B);
			stanza.getDestra().getDestra().getSotto().getSotto().addPersonaggio(stanza.getPersonaggi().get(0));
			stanza.getPersonaggi().remove(0);
			((Npc)stanza.getDestra().getDestra().getSopra().getDestra().getPersonaggi().get(1)).setDialogo(Dialogs.PILOT_B);;
			stanza.getDestra().getDestra().getSotto().getSotto().addPersonaggio(stanza.getDestra().getDestra().getSopra().getDestra().getPersonaggi().get(1));
			stanza.getDestra().getDestra().getSopra().getDestra().getPersonaggi().remove(1);
			p.removeOggetto(missione.getEnigma());
			p.getInventario().addToContainer(ricompensa);

		}

	}

}
