/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General.Eventi.Enigmi;

import General.Name;
import General.ObjectType;

public class Lucchetto extends Enigma {
	/*
	 * "C'e' un lucchetto. Se utilizzo il grimaldello, aprirlo sara' un gioco da
	 * ragazzi. Devo solo decidere la forza da applicare sul tensore e provare a
	 * scassinarlo."
	 */

	public Lucchetto(Name n, String s) {
		super(n, s, ObjectType.EVENT);
	}

	private int picked = 0;
	private boolean completato = false;

	public void lowTension() {
		setTension(2);
	}

	public void highTension() {
		setTension(3);
	}

	private void setTension(int tension) {
		picked += tension;
	}

	public boolean switchOn() {
		if (picked > 10) {
			picked = 0;
			System.out.println("Il tensore si e' spezzato!");
			return false;
		}
		if (picked < 10) {
			System.out.println("La tensione non e' sufficiente!");
			return false;
		}

		System.out.println("CLACK");
		System.out.println("Il lucchetto si e' aperto.");
		return true;
	}

	@Override
	public boolean getCompletato() {
		return completato;
	}
}
