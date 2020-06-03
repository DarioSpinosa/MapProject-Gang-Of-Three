/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General.Eventi.Enigmi;

import General.GenericObject;
import General.Name;

public class Coffe extends GenericObject implements Enigma{
	/*
	 * E' una macchina per il caffe' usata in tantissimi bar. Devo solo riempirla di
	 * caffe' e acqua, poi premere il tasto di accesione"
	 */
	public Coffe(Name name, String description) {
		super(name, description);
	}

	private boolean coffee = false;
	private boolean water = false;
	private boolean powerSwitch = false;

	public String addCoffee() {
		if (coffee == false) {
			coffee = true;
		return("\nHai aggiunto il caffe'");
		} else {
			return("\nIl caffe' e' pieno");
		}
	}

	public String addWater() {
		if (water == false) {
			water = true;
			return("\nHai aggiunto l'acqua");
		} else {
			return("\nL'acqua e' piena");
		}
	}

        @Override
	public String switchOn() {

		if (powerSwitch == true)
			return("\nHai gia' fatto un caffe'. Adesso basta");

		else if (coffee == false && water == false)
			return("\nManca l'acqua e il caffe'!");

		else if (coffee == false)
			return("\nManca il caffe'!");

		else if (water == false)
			return("\nManca l'acqua");
		else {
			powerSwitch = true;
			return("\nVrrrrrrrrrr...\nVRRRRRRRRRRRRRRRRRRRRR\nIl caffe' e' pronto!\nPorti il caffe a Cannavacciuolo che ti da il premio!");
		}
	}

	@Override
	public boolean isCompleted() {
		return powerSwitch;
	}

}
