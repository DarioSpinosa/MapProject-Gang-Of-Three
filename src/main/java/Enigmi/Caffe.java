/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Enigmi;

import General.GenericObject;
import General.Name;

public class Caffe extends GenericObject {
    /*
    * E' una macchina per il caffe' usata in tantissimi bar. Devo solo riempirla di caffe' e acqua,
    * poi premere il tasto di accesione"
    */
    public Caffe(Name n, String s) {super(n, s);}
    
    private boolean coffee = false;
    private boolean water = false;
    private boolean powerSwitch = false;
    
    public void addCoffee() {
        if(coffee==false) {
            coffee = true; System.out.println("Hai aggiunto il caffe'.");
        }
        else { System.out.println("Il caffe' e' pieno"); }
    }
    
    public void addWater() {
        if(water==false) {
            water = true; System.out.println("Hai aggiunto l'acqua.");
        }
        else { System.out.println("L'acqua e' piena"); }
    }
    
    public boolean switchOn() {
        if(powerSwitch == true) {System.out.println("Hai gia' fatto un caffe'. Adesso basta."); return false;}
        if(coffee == false && water == false) {System.out.println("Manca l'acqua e il caffe'!"); return false;}
        if(coffee == false) { System.out.println("Manca il caffe'!"); return false;}
        if(water == false) { System.out.println("Manca l'acqua"); return false;}
        
        powerSwitch = true;
        System.out.println("Vrrrrrrrrrr...");
        System.out.println("VRRRRRRRRRRRRRRRRRRRRR");
        System.out.println("Il caffe' e' pronto!");
        return true;
    }
    
}
