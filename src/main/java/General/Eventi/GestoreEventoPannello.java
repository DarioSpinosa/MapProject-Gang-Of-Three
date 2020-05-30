package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import Entita.Characters.Protagonista;
import General.GenericObject;
import General.Eventi.Enigmi.Pannello;

public class GestoreEventoPannello extends GenericGestoreEvento{

	public  GestoreEventoPannello(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p, ArrayList<GenericObject> objs) {

		if(((Pannello)(missione.getEnigma())).getCompletato()){
			stanza.getSinistra().setAccessibile(true);
		}
	}

}
