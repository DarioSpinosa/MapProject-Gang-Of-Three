package General.Eventi.Enigmi;

import General.GenericObject;
import General.Name;
import General.ObjectType;

public abstract class Enigma extends GenericObject{

	public Enigma(Name nome, String descrizione, ObjectType id) {
		super(nome, descrizione, id);
	}

	public abstract boolean getCompletato();
}
