package General.Eventi;

import General.GenericObject;

public class Event {

	private final String description;
	private GenericObject enigma;

	public Event(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void addEnigma(GenericObject object) {
		enigma = object;

	}

	public GenericObject getEnigma(){
		return enigma;
	}
}
