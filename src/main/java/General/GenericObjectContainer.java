/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General;

import java.io.Serializable;
import java.util.ArrayList;

public class GenericObjectContainer extends GenericObject implements Serializable {

	private ArrayList<GenericObject> container = new ArrayList<>();
	private int maxSize;
	private boolean opened = false;
	private boolean blocked = false;

	public GenericObjectContainer(Name name, String description) {
		super(name, description);
	}

	public GenericObjectContainer(Name name, String description, AliasHandler alias) {
		super(name, description, alias);
	}

	public GenericObjectContainer(Name name, String description, int maxSize) {
		super(name, description);
		this.maxSize = maxSize;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blockedValue) {
		blocked = blockedValue;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public ArrayList<GenericObject> getContainer() {
		return container;
	}

	public void addToContainer(GenericObject object) {
		container.add(object);
	}

	public GenericObject getFromContainer(int i) {
		return container.get(i);
	}

	public void removeFromContainer(GenericObject object) {
		container.remove(object);
	}

	public boolean contains(GenericObject object) {
		if (container.contains(object)) {
			return true;
		}
		return false;
	}

	public boolean isOpened() {
		return opened;
	}

	public void open() {
		opened = true;
	}

	public void close() {
		opened = false;
	}

	public GenericObject getOggetto(GenericObject object) {
		for (GenericObject obj : container) {
			if (obj.equals(object)) {
				return obj;
			}
		}
		return null;
	}

	public void removeOggetto(GenericObject object) {
		container.remove(object);
	}
}
