package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import Resources.Dialogs;

public class GestoreEventoPacco extends GenericGestoreEvento {

	public  GestoreEventoPacco(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p) {
		if(p.getInventario().contains(missione.getEnigma())) {
			stanza.getDestra().setAccessibile(true);
			((Npc)stanza.getPersonaggi().get(0)).setDialogo(Dialogs.BAKER_B);
			stanza.getDestra().addPersonaggio(stanza.getPersonaggi().get(0));
			stanza.getPersonaggi().remove(0);
			p.removeOggetto(missione.getEnigma());

		}
	}

}
