package General;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AliasHandler {

	private Set<Name> alias = new HashSet<>();

	public AliasHandler(Set<Name> alias) {
		this.alias = alias;
	}

	public AliasHandler(Name[] alias) {
		this.alias = new HashSet<>(Arrays.asList(alias));
	}

	public Set<Name> getAlias(){
		return alias;
	}

	public boolean containsAlias(String nameToken){
		for(Name name : alias){
			if(name.getName().equals(nameToken)){
				return true;
			}
		}
		return false;
	}

	public Name getAlias(String nameToken){
		for(Name name : alias){
			if(name.getName().equals(nameToken)){
				return name;
			}
		}
		return null;
	}

	public boolean compareAlias(String name){
		boolean controllo = false;
		if(!alias.isEmpty() && containsAlias(name)){
			controllo = true;
		}
		return controllo;
	}
}
