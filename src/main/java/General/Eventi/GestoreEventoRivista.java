package General.Eventi;

import java.util.ArrayList;

import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Protagonista;
import General.GenericObject;
import General.Name;
import General.ObjectType;
import Parser.WordType;
import Resources.Descriptions;
import Resources.Dialogs;

public class GestoreEventoRivista extends GenericGestoreEvento {

	public GestoreEventoRivista(Evento evento, Stanza stanza) {
		super(evento, stanza);
	}

	@Override
	public void terminaEvento(Protagonista p, ArrayList<GenericObject> objs) {
		if (p.getInventario().contains(missione.getEnigma())) {
			Name nomeChiavi = new Name("chiavi", WordType.NOME);
			nomeChiavi.setArticoli(new String[] { "le" });
			GenericObject chiavi = new GenericObject(nomeChiavi, Descriptions.KEYS, ObjectType.NORMAL);
			objs.add(chiavi);
			((Npc) stanza.getPersonaggi().get(0)).setDialogo(Dialogs.JANITOR_B);
			p.removeOggetto(missione.getEnigma());
			p.addOggetto(chiavi);
		}

	}

}
