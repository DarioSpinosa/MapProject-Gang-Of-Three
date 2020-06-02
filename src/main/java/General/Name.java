/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General;
import Parser.WordType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Name implements Serializable {
    private String name;
    private WordType type;
    private Set<String> admittedArticles = new HashSet<>();
    private Set<String> admittedPrepositions = new HashSet<>();
    
    public Name(String name, WordType type) {
        this.name = name;
        this.type = type;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setType(WordType type) {
        this.type = type;
    }
    
    public WordType getType() {
        return type;
    }
    
    public Set<String> getAdmittedArticles() {
        return admittedArticles;
    }
    
    public Set<String> getAdmittedPrepositions() {
        return admittedPrepositions;
    }
    
    public void setAdmittedArticles(Set<String> articoliComp) {
        this.admittedArticles = articoliComp;
    }
    
    public void setAdmittedArticles(String[] articoliComp) {
        this.admittedArticles = new HashSet<>(Arrays.asList(articoliComp));
    }
    
    public void setAdmittedPrepositions(Set<String> preposizioniComp) {
        this.admittedPrepositions = preposizioniComp;
    }
    
    public void setAdmittedPrepositions(String[] preposizioniComp) {
        this.admittedPrepositions = new HashSet<>(Arrays.asList(preposizioniComp));
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Name){
            if(((Name) obj).getName().equals(this.getName())){
                return true;
            }
        }
        return false;
    }
}
