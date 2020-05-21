/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entità.Characters;
import General.Name;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Personaggio {
    
    protected Name nome;
    protected Set<Name> alias;
    protected int healthPoints;
    
    public Personaggio(Name nome, int healthPoints) {
        this.nome = nome;
        this.healthPoints = healthPoints;
    }
    
    public Name getNome() {
        return nome;
    }
    
    public Set<Name> getAlias(){
        return alias;
    }
    
    public void setAlias(Set<Name> alias) {
        this.alias = alias;
    }
    
    public void setAlias(Name[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }
    
    public int getHealtPoints() {
        return healthPoints;
    }
    
    public boolean articoloUsabile(String articolo){
        return nome.getArticoli().contains(articolo);
    }
    
    public boolean preposizioneUsabile(String preposizione){
        return nome.getPreposizioni().contains(preposizione);
    }
    
    public boolean ricercaAlias(String nome){
        for(Name name : alias){
            if(name.getName().equals(nome)){
                return true;
            }
        }
        return false;
    }
    
    public Name restituisciAlias(String nome){
        for(Name name : alias){
            if(name.getName().equals(nome)){
                return name;
            }
        }
        return null;
    }
    
    public boolean confrontaAlias(String nome){
        boolean controllo = false;
        if(alias != null && ricercaAlias(nome)){
            controllo = true;
        }
        return controllo;
    }
    
    public abstract void getDamage(int danno);
}
