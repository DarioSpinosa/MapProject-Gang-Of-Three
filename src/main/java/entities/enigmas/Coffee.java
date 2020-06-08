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
 *
 */
public class Coffee extends GenericObject implements Enigma{
	/*
	 * E' una macchina per il caffe' usata in tantissimi bar. Devo solo riempirla di
	 * caffe' e acqua, poi premere il tasto di accensione"
	 */
	public Coffee(Name name, String description) {
		super(name, description);
	}

	private boolean coffee = false;
	private boolean water = false;
	private boolean powerSwitch = false;

	public String addCoffee() {
		if (coffee == false) {
			coffee = true;
		return(Events.ADDED_COFFEE);
		} else {
			return(Events.FILLED_COFFEE);
		}
	}

	public String addWater() {
		if (water == false) {
			water = true;
			return(Events.ADDED_WATER);
		} else {
			return(Events.FILLED_WATER);
		}
	}

        @Override
	public String switchOn() {

		if (powerSwitch == true)
			return(Events.ALREADY_DONE_COFEE);

		else if (coffee == false && water == false)
			return(Events.EMPTY_WATER_COFFEE);

		else if (coffee == false)
			return(Events.EMPTY_COFFEE);

		else if (water == false)
			return(Events.EMPTY_WATER);
		else {
			powerSwitch = true;
			return(Events.DONE_COFFEE);
		}
	}

	@Override
	public boolean isCompleted() {
		return powerSwitch;
	}

}
