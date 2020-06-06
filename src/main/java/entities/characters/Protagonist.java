/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package entities.characters;

import entities.GenericObject;
import entities.GenericObjectContainer;
import entities.Name;
import parser.WordType;

public class Protagonist extends Character {

	private final GenericObjectContainer inventory;

	public Protagonist(Name nome) {
		super(nome);
		this.inventory = new GenericObjectContainer(new Name("Inventario", WordType.NOME), "i tuoi oggetti", 3);
	}

	public void addObject(GenericObject o) {
		inventory.addToContainer(o);
	}

	public void removeObject(GenericObject o) {
		inventory.removeFromContainer(o);
	}

	public GenericObjectContainer getInventory() {
		return inventory;
	}

	public boolean isInInventory(GenericObject oggetto) {
		boolean conferma = false;
		if (inventory.getContainer().contains(oggetto)) {
			conferma = true;
		}
		return conferma;
	}

	public GenericObject getObject(GenericObject oggetto) {
		for (GenericObject obj : inventory.getContainer()) {
			if (obj.equals(oggetto)) {
				return obj;
			}
		}
		return null;
	}

	public int getInventoryMaxSize() {
		return inventory.getMaxSize();
	}

	public int getActualInventorySize() {
		return inventory.getContainer().size();
	}
}
