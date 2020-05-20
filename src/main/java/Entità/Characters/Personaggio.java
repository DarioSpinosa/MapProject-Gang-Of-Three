/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entità.Characters;
import General.Name;

public abstract class Personaggio {
    
    protected String nome;
    protected Name alias;
    protected int healthPoints;
    
    public Personaggio(String nome, int healthPoints) {
        this.nome = nome;
        this.healthPoints = healthPoints;
    }
    
    public Personaggio(String nome, Name alias, int healthPoints) {
        this.nome = nome;
        this.alias = alias;
        this.healthPoints = healthPoints;
    }
    
    public String getNome() {
        return nome;
    }
    
    public Name getAlias(){
        return alias;
    }
    
    public void setAlias(Name alias){
        this.alias = alias;
    }
    
    public int getHealtPoints() {
        return healthPoints;
    }
    
    public abstract void getDamage(int danno);
}
