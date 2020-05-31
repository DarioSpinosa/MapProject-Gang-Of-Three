/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Entita.Characters;
import java.util.Set;

import General.AliasHandler;
import General.Name;

public abstract class Character {

    protected Name name;
    protected AliasHandler alias;

    public Character(Name nome) {
        this.name = nome;
    }

    public Name getName() {
        return name;
    }

    public AliasHandler getAliasHandler(){
        return alias;
    }

    public void setAlias(Set<Name> alias) {
        this.alias = new AliasHandler(alias);
    }

    public void setAlias(Name[] alias) {
        this.alias = new AliasHandler(alias);
    }

    public boolean isArticleUsable(String articolo){
        return name.getAdmittedArticles().contains(articolo);
    }

    public boolean isPrepositionUsable(String preposizione){
        return name.getAdmittedPrepositions().contains(preposizione);
    }
    
}
