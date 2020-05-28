/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita.Characters;
import java.util.Set;

import General.GestoreAlias;
import General.Name;

public abstract class Personaggio {

    protected Name nome;
    protected GestoreAlias alias;
    protected int healthPoints; // TODO: rimuovere healthPoints, non servono più

    public Personaggio(Name nome, int healthPoints) {
        this.nome = nome;
        this.healthPoints = healthPoints;
    }

    public Name getNome() {
        return nome;
    }

    public GestoreAlias getGestoreAlias(){
        return alias;
    }

    public void setAlias(Set<Name> alias) {
        this.alias = new GestoreAlias(alias);
    }

    public void setAlias(Name[] alias) {
        this.alias = new GestoreAlias(alias);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public boolean articoloUsabile(String articolo){
        return nome.getArticoli().contains(articolo);
    }

    public boolean preposizioneUsabile(String preposizione){
        return nome.getPreposizioni().contains(preposizione);
    }
    
    public void setHealth(int healthPoints){
        this.healthPoints = healthPoints;
    }

    public abstract void getDamage(int danno);
}
