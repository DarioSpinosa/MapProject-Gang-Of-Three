/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General;
import Parser.WordType;

public class Name {
    private String name;
    private WordType type;
    
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
}
