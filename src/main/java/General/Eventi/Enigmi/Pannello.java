/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General.Eventi.Enigmi;

import General.GenericObject;
import General.Name;
import General.ObjectType;

public class Pannello extends GenericObject implements Enigma{
	/*
	 * E' un pannello per il controllo della corrente elettrica. Leggi la targhetta:
	 * "Per accendere il generatore principale, selezionare il voltaggio con le 5
	 * leve sul pannello. Dopo aver selezionato il valore corretto, premere il tasto
	 * rosso."
	 */
	public Pannello(Name n, String s) {
		super(n, s, ObjectType.EVENT);
		for(int i = 0; i < 5; i++) {
			array[0] = false;
		}
	}

	private boolean firstToggle = false;
	private boolean secondToggle = false;
	private boolean thirdToggle = false;
	private boolean fourthToggle = false;
	private boolean fifthToggle = false;
	private boolean[] array = new boolean[5]; //abbassata = false;
	private boolean completato = false;
	private int voltage = 0;

	public void switchFirstToggle() {
		if (firstToggle == true) {
			array[0] = false;
			firstToggle = false;
			setVoltage(-50);
		} else {
			firstToggle = true;
			array[0] = true;
			setVoltage(50);
		}
	}

	public void switchSecondToggle() {
		if (secondToggle == true) {
			array[1] = false;
			secondToggle = false;
			setVoltage(-20);
		} else {
			array[1] = true;
			secondToggle = true;
			setVoltage(20);
		}
	}

	public void switchThirdToggle() {
		if (thirdToggle == true) {
			array[2] = false;
			thirdToggle = false;
			setVoltage(-5);
		} else {
			array[2] = true;
			thirdToggle = true;
			setVoltage(5);
		}
	}

	public void switchFourthToggle() {
		if (fourthToggle == true) {
			array[3] = false;
			fourthToggle = false;
			setVoltage(-40);
		} else {
			array[3] = true;
			fourthToggle = true;
			setVoltage(40);
		}
	}

	public void switchFifthToggle() {
		if (fifthToggle == true) {
			array[4] = false;
			fifthToggle = false;
			setVoltage(-150);
		} else {
			array[4] = true;
			fifthToggle = true;
			setVoltage(150);
		}
	}

	public boolean getArray(int i) {
		return array[i];
	}

	private void setVoltage(int voltageValue) {
		voltage += voltageValue;
	}

	public String showVoltage() {
		return("Target = 110V. Corrente: " + voltage + "V");

	}

	public String switchOn() {

		if(voltage == 110) {
			completato = true;
			return("BEEEEEEP!\n\"Una lucina verde si e' accesa!");
		}
		else
			return("Il marchingegno non si muove");
	}

	@Override
	public boolean getCompletato() {
		return completato;
	}
}
