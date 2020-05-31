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
			if (dialoghi.length == 1) {
				scelta = dialoghi[0];
				dialogoCorrente = 0;
			}
			if (dialoghi.length == 2) {
				scelta = dialoghi[1];
				dialogoCorrente = 0;
			}
			if (dialoghi.length == 3) {
				scelta = dialoghi[1];
				dialogoCorrente++;
			}
			break;
		case 2:
			scelta = dialoghi[2];
			dialogoCorrente = 0;
			break;
		}
		return scelta;
	}

	public void setDialogo(String[] dialoghi) {
		this.dialoghi = dialoghi;
	}

	public String getDialogoCorrente() {
		return dialoghi[dialogoCorrente];
	}


	@Override
	public void getDamage(int danno) {
	}

}
