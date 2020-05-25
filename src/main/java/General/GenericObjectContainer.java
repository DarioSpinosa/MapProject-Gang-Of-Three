/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General;

import java.util.ArrayList;

public class GenericObjectContainer extends GenericObject {

	private ArrayList<GenericObject> container = new ArrayList<>();
	private int maxSize;
	private boolean opened = false;

	public GenericObjectContainer(Name nome, String descrizione, ObjectType id) {
		super(nome, descrizione, id);
	}

	public GenericObjectContainer(Name nome, String descrizione, ObjectType id, GestoreAlias alias) {
		super(nome, descrizione, id, alias);
	}

	public GenericObjectContainer(Name nome, String descrizione, ObjectType id, int maxSize) {
		super(nome, descrizione, id);
		this.maxSize = maxSize;
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

	public GenericObject get(int i) {
		return container.get(i);
	}

	public void removeFromContainer(GenericObject object) {
		container.remove(object);
	}

	public boolean contains(GenericObject oggetto) {
		if (container.contains(oggetto)) {
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
        
    public GenericObject getOggetto(GenericObject oggetto) {
    	for(GenericObject obj : container) {
    		if(obj.equals(oggetto)) {
    			return obj;
    		}
    	}
    	return null;
    }
        
        public void removeOggetto(GenericObject oggetto){
            container.remove(oggetto);
        }
}
