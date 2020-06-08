/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package entities.enigmas;

import entities.GenericObject;
import entities.Name;
import resources.Events;
/**
 * <Entity> Responsabilità: rappresenta un enigma utilizzato in un evento nel gioco,
 * i  metodi hanno il compito di verificare mediante dei valori booleani lo stato dell'enigma
 * e restituire messaggi specifici in base alle azioni compiute.
 */

public class Panel extends GenericObject implements Enigma{
	/*
	 * E' un pannello per il controllo della corrente elettrica. Leggi la targhetta:
	 * "Per accendere il generatore principale, selezionare il voltaggio con le 5
	 * leve sul pannello. Dopo aver selezionato il valore corretto, premere il tasto
	 * rosso."
	 */
	public Panel(Name description, String s) {
		super(description, s);
		for(int i = 0; i < 5; i++) {
			leversState[i] = false;
		}
	}

	private boolean firstToggle = false;
	private boolean secondToggle = false;
	private boolean thirdToggle = false;
	private boolean fourthToggle = false;
	private boolean fifthToggle = false;
	private boolean[] leversState = new boolean[5]; //abbassata = false;
	private boolean completed = false;
	private int voltage = 0;

	public void switchFirstToggle() {
		if (firstToggle == true) {
			leversState[0] = false;
			firstToggle = false;
			setVoltage(-50);
		} else {
			firstToggle = true;
			leversState[0] = true;
			setVoltage(50);
		}
	}

	public void switchSecondToggle() {
		if (secondToggle == true) {
			leversState[1] = false;
			secondToggle = false;
			setVoltage(-20);
		} else {
			leversState[1] = true;
			secondToggle = true;
			setVoltage(20);
		}
	}

	public void switchThirdToggle() {
		if (thirdToggle == true) {
			leversState[2] = false;
			thirdToggle = false;
			setVoltage(-5);
		} else {
			leversState[2] = true;
			thirdToggle = true;
			setVoltage(5);
		}
	}

	public void switchFourthToggle() {
		if (fourthToggle == true) {
			leversState[3] = false;
			fourthToggle = false;
			setVoltage(-40);
		} else {
			leversState[3] = true;
			fourthToggle = true;
			setVoltage(40);
		}
	}

	public void switchFifthToggle() {
		if (fifthToggle == true) {
			leversState[4] = false;
			fifthToggle = false;
			setVoltage(-150);
		} else {
			leversState[4] = true;
			fifthToggle = true;
			setVoltage(150);
		}
	}

	public boolean getLeversState(int i) {
		return leversState[i];
	}

	private void setVoltage(int voltageValue) {
		voltage += voltageValue;
	}

	public String showVoltage() {
		return(Events.VOLTAGE + voltage + "V");

	}

	@Override
	public String switchOn() {

		if(voltage == 110) {
			completed = true;
			return(Events.PANEL_COMPLETED);
		}
		else
			return(Events.PANEL_NOT_WORKING);
	}

	@Override
	public boolean isCompleted() {
		return completed;
	}
}
