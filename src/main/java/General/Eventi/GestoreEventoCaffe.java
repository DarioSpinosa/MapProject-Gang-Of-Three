package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import General.GenericObject;
import General.Name;
import General.ObjectType;
import General.Eventi.Enigmi.Caffe;
import Parser.WordType;
import Resources.Descriptions;
import Resources.Dialogs;

public class GestoreEventoCaffe extends GenericGestoreEvento{

	public GestoreEventoCaffe(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p, ArrayList<GenericObject> objs) {

		if(((Caffe)(missione.getEnigma())).getCompletato()){
			Name NomeCaffeCaldo = new Name("espresso", WordType.NOME);
			NomeCaffeCaldo.setArticoli(new String[] { "un" });
			NomeCaffeCaldo.setPreposizioni(new String[] { "quel" });
			GenericObject CaffeCaldo  = new GenericObject(NomeCaffeCaldo, Descriptions.ESPRESSO, ObjectType.EVENT);
			objs.add(CaffeCaldo);
			((Npc)stanza.getPersonaggi().get(0)).setDialogo(Dialogs.CANNAVACCIUOLO_B);
			p.getInventario().addToContainer(CaffeCaldo);

		}
	}


}
