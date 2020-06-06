/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package entities.characters;
import java.util.Set;

import entities.Alias;
import entities.Name;

import java.io.Serializable;

public abstract class Character implements Serializable{

    protected Name name;
    protected Alias alias;

    public Character(Name nome) {
        this.name = nome;
    }

    public Name getName() {
        return name;
    }

    public Alias getAliasHandler(){
        return alias;
    }

    public void setAlias(Set<Name> alias) {
        this.alias = new Alias(alias);
    }

    public void setAlias(Name[] alias) {
        this.alias = new Alias(alias);
    }

    public boolean isArticleUsable(String articolo){
        return name.getAdmittedArticles().contains(articolo);
    }

    public boolean isPrepositionUsable(String preposizione){
        return name.getAdmittedPrepositions().contains(preposizione);
    }
    
    @Override
    public String toString(){
        return name.getName();
    }
    
}
