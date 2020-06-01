/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita.Characters;

import General.Name;
import java.io.Serializable;

public class Npc extends Character implements Serializable {

	private String[] dialogues;
	private int currentDialogue = 0;

	public Npc(Name nome) {
		super(nome);
	}

	public Npc(Name nome, String[] dialoghi) {
		super(nome);
		this.dialogues = dialoghi;

	}

	public String getDialogue() {
		String scelta = null;
		switch (currentDialogue) {
		case 0:
			scelta = dialogues[0];
			currentDialogue++;
			break;
		case 1:
			if (dialogues.length == 1) {
				scelta = dialogues[0];
				currentDialogue = 0;
			}
			if (dialogues.length == 2) {
				scelta = dialogues[1];
				currentDialogue = 0;
			}
			if (dialogues.length == 3) {
				scelta = dialogues[1];
				currentDialogue++;
			}
			break;
		case 2:
			scelta = dialogues[2];
			currentDialogue = 0;
			break;
		}
		return scelta;
	}

	public void setDialogue(String[] dialoghi) {
		this.dialogues = dialoghi;
	}

	public String getCurrentDialogue() {
		return dialogues[currentDialogue];
	}

}
