/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General;
import java.util.ArrayList;

public class GenericObjectContainer extends GenericObject{

    private ArrayList<GenericObject> container = new ArrayList<>();
    private int maxSize;
    private boolean opened = false;

    public GenericObjectContainer(Name nome, String descrizione) {
    	super(nome, descrizione);
    }

    public GenericObjectContainer(Name nome, String descrizione, GestoreAlias alias) {
        super(nome,descrizione, alias);
    }

    public GenericObjectContainer(Name nome, String descrizione, int maxSize){
        super(nome,descrizione);
        this.maxSize = maxSize;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public ArrayList<GenericObject> getContainer() {
        return container;
    }

    public void addToContainer(GenericObject object) {
        container.add(object);
    }

    public GenericObject get(int i){
        return container.get(i);
    }

    public void removeFromContainer(GenericObject object) {
        container.remove(object);
    }

    public boolean contains(GenericObject oggetto){
        if(container.contains(oggetto)){
            return true;
        }
        return false;
    }

    public boolean isOpened(){
        return opened;
    }

    public void open(){
        opened = true;
    }

    public void close(){
        opened = false;
    }
}
