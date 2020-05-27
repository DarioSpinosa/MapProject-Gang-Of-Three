package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import Entita.Characters.Npc;
import General.GenericObject;
import General.Eventi.Enigmi.Caffe;

public class GestoreEventoCaffe extends GenericGestoreEvento{

	public GestoreEventoCaffe(){
		super();
	}

	public GestoreEventoCaffe(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(ArrayList<GenericObject> objs) {

		if(((Caffe)(missione.getEnigma())).getCompletato()){
			setCompletato();
			objs.add(((Caffe)missione.getEnigma()).getCoffee());
			if(stanza.getSopra() != null)
				stanza.getSopra().setAccessibile(true);
			if(stanza.getSotto() != null)
				stanza.getSotto() .setAccessibile(true);
			if(stanza.getSinistra() != null)
				stanza.getSinistra().setAccessibile(true);
			if(stanza.getDestra() != null)
				stanza.getDestra().setAccessibile(true);
			((Npc)stanza.getPersonaggi().get(0)).setDialogo("Ora che hai fatto il caffe sparisci", 1);
		}
	}


}
