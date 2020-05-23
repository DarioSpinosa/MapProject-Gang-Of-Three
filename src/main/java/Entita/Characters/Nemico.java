/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita.Characters;
import General.Name;

public class Nemico extends Personaggio{
    
    public Nemico(Name nome) {
        super(nome, (int)(Math.random() * 4) + 2);
    }
    
    @Override
    public void getDamage(int danno) {
        
        if(healthPoints - danno < 0)
            healthPoints = 0;
        else
            healthPoints -= danno;
        
    }
    
}
