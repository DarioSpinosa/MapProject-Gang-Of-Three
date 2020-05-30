package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import General.Eventi.Enigmi.Caffe;
import Resources.Dialogs;

public class GestoreEventoCaffe extends GenericGestoreEvento{

	public GestoreEventoCaffe(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p) {

		if(((Caffe)(missione.getEnigma())).getCompletato()){
			((Npc)stanza.getPersonaggi().get(0)).setDialogo(Dialogs.CANNAVACCIUOLO_B);
			p.getInventario().addToContainer(ricompensa);

		}
	}


}
