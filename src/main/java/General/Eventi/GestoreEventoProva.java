package General.Eventi;

import Entita.Stanza;
import General.Eventi.Enigmi.Caffe;

public class GestoreEventoProva extends GenericGestoreEvento{

	public GestoreEventoProva(){
		super();
	}

	public GestoreEventoProva(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento() {

		if(((Caffe)(missione.getOggettiEvento().get(0))).switchOn()){
			setCompletato();
		}
	}


}
