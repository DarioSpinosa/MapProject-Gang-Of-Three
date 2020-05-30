package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Protagonista;
import General.Eventi.Enigmi.Pannello;
import Resources.Places;

public class GestoreEventoPannello extends GenericGestoreEvento{

	public  GestoreEventoPannello(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p) {

		if(((Pannello)(missione.getEnigma())).getCompletato()){
			stanza.getSinistra().setAccessibile(true);
			stanza.setDescrizione(Places.PHYSICS);
		}
	}

}
