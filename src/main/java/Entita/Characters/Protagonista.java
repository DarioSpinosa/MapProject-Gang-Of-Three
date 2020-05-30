/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita.Characters;
import General.GenericObject;
import General.GenericObjectContainer;
import General.Name;
import General.ObjectType;
import Parser.WordType;

public class Protagonista extends Personaggio{
    
    private final GenericObjectContainer inventario = new GenericObjectContainer(new Name("Inventario", WordType.NOME), "i tuoi oggetti", ObjectType.CONTAINER, 6);
    
    public Protagonista(Name nome) {
        super(nome, 3);
    }
    
    @Override
    public void getDamage(int danno) {
        if(healthPoints - danno < 0)
            healthPoints = 0;
        else
            healthPoints -= danno;
    }
    
    public void heal(int cura) {
        healthPoints = healthPoints + cura;
    }
    
    public void addOggetto(GenericObject o) {
        inventario.addToContainer(o);
    }
    
    public void removeOggetto(GenericObject o) {
        inventario.removeFromContainer(o);
    }
    
    public GenericObjectContainer getInventario(){
        return inventario;
    }
    
    public boolean isInInventario(GenericObject oggetto){
        boolean conferma = false;
        if(inventario.getContainer().contains(oggetto)){
            conferma = true;
        }
        return conferma;
    }
    
    public GenericObject getOggetto(GenericObject oggetto){
        for(GenericObject obj : inventario.getContainer()){
            if(obj.equals(oggetto)){
                return obj;
            }
        }
        return null;
    }
    
    public int getInventoryMaxSize(){
        return inventario.getMaxSize();
    }
    
    public int getActualInventorySize(){
        return inventario.getContainer().size();
    }
}
