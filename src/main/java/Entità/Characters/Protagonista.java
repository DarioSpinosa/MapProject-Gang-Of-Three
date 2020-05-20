/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entità.Characters;
import General.GenericObject;
import General.GenericObjectContainer;
import General.Name;
import Parser.WordType;

public class Protagonista extends Personaggio{
    
    private final GenericObjectContainer inventario = new GenericObjectContainer(new Name("Inventario", WordType.NOME_MASCHILE), "i tuoi oggetti", 6);
    
    public Protagonista(String nome) {
        super(nome, 3);
    }
    
    @Override
    public void getDamage(int danno) {
        if(healthPoints - danno < 0)
            healthPoints = 0;
        else
            healthPoints -= danno;
    }
    
    public void getHeal(int cura) {
        if(healthPoints == 3)
            System.out.println("Sei gia al massimo della salute");
        else if((healthPoints + cura) >= 3)
            healthPoints = 3;
        else
            healthPoints += cura;
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
}
