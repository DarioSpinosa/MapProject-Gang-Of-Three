package entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Prepositions {

	private HashMap<String, Set<CommandType>> prepositions = new HashMap<>();

	public Prepositions() {

	}

	public void addPreposition(String preposition, CommandType type) {
		Set<CommandType> types = new HashSet<>();
		types.add(type);
		prepositions.put(preposition, types);
	}

	public void addPreposition(String preposition, Set<CommandType> type) {
		prepositions.put(preposition, type);
	}

	public void addPreposition(String preposition, CommandType[] type) {
		prepositions.put(preposition, new HashSet<>(Arrays.asList(type)));
	}

	public void removePreposition(String preposition) {
		if(prepositions.containsKey(preposition))
			prepositions.remove(preposition);
	}

	public boolean isEmpty() {
		boolean empty = false;

		if(prepositions.size() == 0)
			empty = true;

		return empty;
	}

	public boolean isGoodCombination(String preposition, CommandType type) {
		if(prepositions.containsKey(preposition) && prepositions.get(preposition).contains(type)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPreposition(String preposition) {
		if(prepositions.containsKey(preposition)) {
			return true;
		} else {
			return false;
		}
	}
}
