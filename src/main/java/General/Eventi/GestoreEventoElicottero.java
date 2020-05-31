package General.Eventi;

import Entita.Stanza;
import Entita.Characters.Npc;
import Entita.Characters.Personaggio;
import Entita.Characters.Protagonista;
import General.GenericObject;
import Resources.Dialogs;

public class GestoreEventoElicottero extends GenericGestoreEvento{

	public  GestoreEventoElicottero(Evento m, Stanza stanza){
		super(m, stanza);
	}

	@Override
	public String iniziaEvento() {

		String temp = "";

		if(stanza.getSopra().getSopra().getSinistra().getSinistra().getGestoreEvento().getCompletato()) {

			if (iniziato == false) {
				temp = missione.getDescrizione();
				iniziato = true;

				((Npc)stanza.getPersonaggio(0)).setDialogo(Dialogs.MORGAN_B);

				if(oggetti.size() != 0) {
					for(GenericObject oggetto: oggetti) {
						stanza.addOggetto(oggetto);
					}
				}

				if(personaggi.size() != 0) {
					for(Personaggio p: personaggi) {
						stanza.addPersonaggio(p);
					}
				}
			}
		}
		return temp;
	}

	@Override
	public void terminaEvento(Protagonista p) {

	}


}
