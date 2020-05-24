/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package General;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Parser.WordType;
import java.util.Objects;

public class GenericObject {
	private Name nome;
	private String descrizione = "";
	private GestoreAlias alias = new GestoreAlias(new Name[] {});
	private Set<String> aggettivi;
	private boolean consumabile = true;
	private final int category;

	public GenericObject(Name nome, String descrizione, int id) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.category = id;
	}

	public GenericObject(Name nome, String descrizione, int id, GestoreAlias alias) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.category = id;
		this.alias = alias;
	}

	public GenericObject(Name nome, String descrizione, int id, Set<String> aggettivi) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.category = id;
		this.aggettivi = aggettivi;
	}

	public String getNome() {
		return nome.getName();
	}

	public WordType getTipo() {
		return nome.getType();
	}

	public int getCategory() {
		return this.category;
	}

	public void setNome(Name nome) {
		this.nome = nome;
	}

	public Name getName() {
		return nome;
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

	public GestoreAlias getGestoreAlias() {
		return alias;
	}

	public void setGestoreAlias(Set<Name> alias) {
		this.alias = new GestoreAlias(alias);
	}

	public void setGestoreAlias(Name[] alias) {
		this.alias = new GestoreAlias(alias);
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

	public boolean articoloUsabile(String articolo) {
		return nome.getArticoli().contains(articolo);
	}

	public boolean preposizioneUsabile(String preposizione) {
		return nome.getPreposizioni().contains(preposizione);
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nome.getName());
        hash = 59 * hash + Objects.hashCode(this.aggettivi);
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
        if (!Objects.equals(this.nome.getName(), other.nome.getName())) {
            return false;
        }
        if (!Objects.equals(this.aggettivi, other.aggettivi)) {
            return false;
        }
        return true;
    }

    

	

	@Override
	public String toString() {
		return this.getNome();
	}
}
