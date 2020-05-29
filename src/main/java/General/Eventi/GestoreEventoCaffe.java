package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import General.GenericObject;
import General.Eventi.Enigmi.Caffe;
import Resources.Dialogs;

public class GestoreEventoCaffe extends GenericGestoreEvento{

	public GestoreEventoCaffe(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p, ArrayList<GenericObject> objs) {

		if(((Caffe)(missione.getEnigma())).getCompletato()){
			setCompletato();
			objs.add(((Caffe)missione.getEnigma()).getCoffee());
			((Npc)stanza.getPersonaggi().get(0)).setDialogo(Dialogs.CANNAVACCIUOLO_B);
			p.getInventario().addToContainer(((Caffe)missione.getEnigma()).getCoffee());

		}
	}


}
