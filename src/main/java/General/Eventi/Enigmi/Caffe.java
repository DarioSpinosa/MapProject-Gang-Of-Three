/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General.Eventi.Enigmi;

import General.GenericObject;
import General.Name;
import General.ObjectType;
import Parser.WordType;
import Resources.Descriptions;

public class Caffe extends Enigma{
	/*
	 * E' una macchina per il caffe' usata in tantissimi bar. Devo solo riempirla di
	 * caffe' e acqua, poi premere il tasto di accesione"
	 */
	public Caffe(Name n, String s) {
		super(n, s, ObjectType.EVENT);
	}

	private boolean coffee = false;
	private boolean water = false;
	private boolean powerSwitch = false;

	public String addCoffee() {
		if (coffee == false) {
			coffee = true;
		return("Hai aggiunto il caffe'.");
		} else {
			return("Il caffe' e' pieno");
		}
	}

	public String addWater() {
		if (water == false) {
			water = true;
			return("Hai aggiunto l'acqua.");
		} else {
			return("L'acqua e' piena");
		}
	}

	public String switchOn() {

		if (powerSwitch == true)
			return("Hai gia' fatto un caffe'. Adesso basta.");

		else if (coffee == false && water == false)
			return("Manca l'acqua e il caffe'!");

		else if (coffee == false)
			return("Manca il caffe'!");

		else if (water == false)
			return("Manca l'acqua");
		else {
			powerSwitch = true;
			return("\nVrrrrrrrrrr...\nVRRRRRRRRRRRRRRRRRRRRR\nIl caffe' e' pronto!\nHai ottenuto un ottimo caffe caldo");
		}
	}

	@Override
	public boolean getCompletato() {
		return powerSwitch;
	}

	public GenericObject getCoffee() {
		Name NomeCaffeCaldo = new Name("espresso", WordType.NOME);
		NomeCaffeCaldo.setArticoli(new String[] { "un" });
		NomeCaffeCaldo.setPreposizioni(new String[] { "quel" });
		GenericObject TazzaDiCaffe  = new GenericObject(NomeCaffeCaldo, Descriptions.ESPRESSO, ObjectType.EVENT);
		return TazzaDiCaffe;
	}
}
