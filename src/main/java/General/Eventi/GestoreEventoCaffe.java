package General.Eventi;

import Entita.Stanza;
import General.Eventi.Enigmi.Caffe;

public class GestoreEventoCaffe extends GenericGestoreEvento{

	public GestoreEventoCaffe(){
		super();
	}

	public GestoreEventoCaffe(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento() {

		if(((Caffe)(missione.getOggettiEvento().get(0))).getCompletato()){
			setCompletato();
		}
	}


}
