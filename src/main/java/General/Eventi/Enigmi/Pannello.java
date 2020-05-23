/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General.Eventi.Enigmi;

import General.GenericObject;
import General.Name;

public class Pannello extends GenericObject implements Enigma {
	/*
	 * E' un pannello per il controllo della corrente elettrica. Leggi la targhetta:
	 * "Per accendere il generatore principale, selezionare il voltaggio con le 5
	 * leve sul pannello. Dopo aver selezionato il valore corretto, premere il tasto
	 * rosso."
	 */
	public Pannello(Name n, String s) {
		super(n, s);
	}

	private boolean firstToggle = false;
	private boolean secondToggle = false;
	private boolean thirdToggle = false;
	private boolean fourthToggle = false;
	private boolean fifthToggle = false;
	private int voltage = 0;

	public void switchFirstToggle() {
		if (firstToggle == true) {
			firstToggle = false;
			setVoltage(-50);
		} else {
			firstToggle = true;
			setVoltage(50);
		}
	}

	public void switchSecondToggle() {
		if (secondToggle == true) {
			secondToggle = false;
			setVoltage(-20);
		} else {
			secondToggle = true;
			setVoltage(20);
		}
	}

	public void switchThirdToggle() {
		if (thirdToggle == true) {
			thirdToggle = false;
			setVoltage(-5);
		} else {
			thirdToggle = true;
			setVoltage(5);
		}
	}

	public void switchFourthToggle() {
		if (fourthToggle == true) {
			fourthToggle = false;
			setVoltage(-40);
		} else {
			fourthToggle = true;
			setVoltage(40);
		}
	}

	public void switchFifthToggle() {
		if (fifthToggle == true) {
			fifthToggle = false;
			setVoltage(-150);
		} else {
			fifthToggle = true;
			setVoltage(150);
		}
	}

	private void setVoltage(int voltageValue) {
		voltage += voltageValue;
	}

	@Override
	public boolean switchOn() {
		if (voltage != 110) {
			System.out.println("Target = 110V. Corrente: " + voltage + "V");
			return false;
		}
		System.out.println("BEEEEEEP!");
		System.out.println("Una lucina verde si e' accesa!");
		return true;
	}
}
