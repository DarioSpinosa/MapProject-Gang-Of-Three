package entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * <Entity> Responsabilità: Rappresenta una collezione di alias associabili ad altre entity, come
 * personaggi, comandi e oggetti. Permette di verificare se un alias appartiene alla collezione.
 *
 */
public class Alias implements Serializable {

	private Set<Name> alias = new HashSet<>();

	public Alias(Set<Name> alias) {
		this.alias = alias;
	}

	public Alias(Name[] alias) {
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
