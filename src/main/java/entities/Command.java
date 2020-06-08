/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package entities;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *<Entity> Responsabilità: Rappresenta un comando. Contiene le informazioni sul suo tipo, nome
 * e sugli eventuali alias ad esso associabili.
 *
 */

public class Command {

    private CommandType type;
    private String name;
    private Set<String> alias;

    public Command(){}

    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    public Command(CommandType type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    public CommandType getCommandType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(String[] array) {
        alias = new HashSet<>(Arrays.asList(array));
    }

    public boolean confrontaAlias(String name){
        boolean controllo = false;
        if(alias != null && alias.contains(name)){
            controllo = true;
        }
        return controllo;
    }
}

