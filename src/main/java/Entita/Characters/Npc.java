/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita.Characters;

import General.Name;

public class Npc extends Personaggio {

	private String[] dialoghi;
	private int dialogoCorrente = 0;

	public Npc(Name nome) {
		super(nome, 1);
	}

	public Npc(Name nome, String[] dialoghi) {
		super(nome, 1);
		this.dialoghi = dialoghi;

	}

	public String getDialogo() {
		String scelta = null;
		switch (dialogoCorrente) {
		case 0:
			scelta = dialoghi[0];
			dialogoCorrente++;
			break;
		case 1:
			scelta = dialoghi[1];
			dialogoCorrente++;
			break;
		case 2:
			if (dialoghi.length == 3) {
				scelta = dialoghi[2];
				dialogoCorrente = 1;
			} else if (dialoghi.length > 3) {
				scelta = dialoghi[2];
				dialogoCorrente++;
			} else {
				scelta = dialoghi[1];
			}
			break;
		case 3:
			scelta = dialoghi[3];
			dialogoCorrente++;
			break;
		}
		return scelta;
	}

	@Override
	public void getDamage(int danno) {
	}

}
