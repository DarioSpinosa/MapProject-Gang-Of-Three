package General;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GestoreAlias {

	private Set<Name> alias = new HashSet<Name>();

	public GestoreAlias(Set<Name> alias) {
		this.alias = alias;
	}

	public GestoreAlias(Name[] alias) {
		this.alias = new HashSet<>(Arrays.asList(alias));
	}

	public Set<Name> getAlias(){
		return alias;
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
		if(!alias.isEmpty() && ricercaAlias(nome)){
			controllo = true;
		}
		return controllo;
	}
}
