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

public class GenericObject {
    private Name nome;
    private String descrizione = "";
    private Set<String> alias;
    private Set<String> aggettivi;
    private boolean consumabile = false;
    
    public GenericObject(Name nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }
    
    public GenericObject(Name nome, String descrizione, Set<String> alias) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.alias = alias;
    }
    
    public GenericObject(Name nome, String descrizione, Set<String> alias, Set<String> aggettivi) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.alias = alias;
        this.aggettivi = aggettivi;
    }
    
    public String getNome() {
        return nome.getName();
    }
    
    public WordType getTipo() {
        return nome.getType();
    }
    
    public void setNome(Name nome) {
        this.nome = nome;
    }
    
    public String getDescrizione() {
        return descrizione;
    }
    
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public boolean isConsumabile() {
        return consumabile;
    }
    
    public void setConsumabile(boolean consumabile) {
        this.consumabile = consumabile;
    }
    
    public Set<String> getAlias() {
        return alias;
    }
    
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
    
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }
    
    public Set<String> getAggettivi() {
        return aggettivi;
    }
    
    public void setAggettivi(Set<String> aggettivi) {
        this.aggettivi = aggettivi;
    }
    
    public void setAggettivi(String[] aggettivi) {
        this.aggettivi = new HashSet<>(Arrays.asList(aggettivi));
    }
    
    public boolean confrontaAlias(String nome){
        boolean controllo = false;
        if(alias != null && alias.contains(nome)){
            controllo = true;
        }
        return controllo;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof GenericObject){
            if(((GenericObject) obj).getNome().equals(this.getNome())){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
}
