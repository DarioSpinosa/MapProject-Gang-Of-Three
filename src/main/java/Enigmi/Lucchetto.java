/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Enigmi;

import General.GenericObject;
import General.Name;


public class Lucchetto extends GenericObject{
    /*
    * "C'e' un lucchetto. Se utilizzo il grimaldello, aprirlo sara' un gioco da ragazzi.
    * Devo solo decidere la forza da applicare sul tensore e provare a scassinarlo."
    */
    
    public Lucchetto(Name n, String s) {super(n, s);}
    
    private int picked = 0;
    
    public void lowTension() {
        setTension(1);
    }
    
    public void highTension() {
        setTension(2);
    }
    
    private void setTension(int tension) {
        picked += tension;
    }
    
    public boolean switchOn() {
        if(picked > 5) { picked = 0; System.out.println("Il tensore si e' spezzato!"); return false; }
        if(picked < 5) { System.out.println("La tensione non e' sufficiente!"); return false; }
        
        System.out.println("CLACK");
        System.out.println("Il lucchetto si e' aperto.");
        return true;
    }
}
