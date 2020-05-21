/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General;
import Parser.WordType;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Name {
    private String name;
    private WordType type;
    private Set<String> articoliComp;
    private Set<String> preposizioniComp;
    
    public Name(String name, WordType type) {
        this.name = name;
        this.type = type;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {return name;}
    
    public void setType(WordType type) {
        this.type = type;
    }
    
    public WordType getType() {return type;}
    
    public Set<String> getArticoli() {
        return articoliComp;
    }
    
    public Set<String> getPreposizioni() {
        return preposizioniComp;
    }
    
    public void setArticoli(Set<String> articoliComp) {
        this.articoliComp = articoliComp;
    }
    
    public void setArticoli(String[] articoliComp) {
        this.articoliComp = new HashSet<>(Arrays.asList(articoliComp));
    }
    
    public void setPreposizioni(Set<String> preposizioniComp) {
        this.preposizioniComp = preposizioniComp;
    }
    
    public void setPreposizioni(String[] preposizioniComp) {
        this.preposizioniComp = new HashSet<>(Arrays.asList(preposizioniComp));
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
