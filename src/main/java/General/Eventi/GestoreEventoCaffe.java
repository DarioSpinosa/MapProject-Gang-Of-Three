package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
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
		}
	}


}
