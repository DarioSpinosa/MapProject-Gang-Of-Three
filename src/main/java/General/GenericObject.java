/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import Parser.WordType;
import java.io.Serializable;

public class GenericObject implements Serializable {
	private Name name;
	private String description = "";
	private AliasHandler alias = new AliasHandler(new Name[] {});
	private Set<String> adjectives;
	private boolean consumable = true;
	private boolean takeable = true;

	public GenericObject(Name name, String description) {
		this.name = name;
		this.description = description;
	}

	public GenericObject(Name name, String description, AliasHandler alias) {
		this.name = name;
		this.description = description;
		this.alias = alias;
	}

	public GenericObject(Name name, String description, Set<String> adjectives) {
		this.name = name;
		this.description = description;
		this.adjectives = adjectives;
	}

	public String getObjectName() {
		return name.getName();
	}

	public WordType getType() {
		return name.getType();
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isConsumable() {
		return consumable;
	}

	public boolean isTakeable() {
		return takeable;
	}

	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}

	public void setTakeable(boolean takeable) {
		this.takeable = takeable;
	}

	public AliasHandler getAliasHandler() {
		return alias;
	}

	public void setAliasHandler(Set<Name> alias) {
		this.alias = new AliasHandler(alias);
	}

	public void setAliasHandler(Name[] alias) {
		this.alias = new AliasHandler(alias);
	}

	public Set<String> getAdjectives() {
		return adjectives;
	}

	public void setAdjectives(Set<String> adjectives) {
		this.adjectives = adjectives;
	}

	public void setAdjectives(String[] aggettivi) {
		this.adjectives = new HashSet<>(Arrays.asList(aggettivi));
	}

	public boolean isArticleUsable(String articolo) {
		return name.getAdmittedArticles().contains(articolo);
	}

	public boolean isPrepositionUsable(String preposizione) {
		return name.getAdmittedPrepositions().contains(preposizione);
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name.getName());
        hash = 59 * hash + Objects.hashCode(this.adjectives);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenericObject other = (GenericObject) obj;
        if (!Objects.equals(this.name.getName(), other.name.getName())) {
            return false;
        }
        if (!Objects.equals(this.adjectives, other.adjectives)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return this.getObjectName();
	}
}
