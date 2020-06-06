/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <Entity> Responsabilità Node: Rappresenta una combinazione formata da due oggetti da combinare
 * e l'oggetto risultato.
 *<Entity> Responsabilità Combinations: Rappresenta una collezione di combinazioni, permette di verificare
 *se una combinazione appartiene alla collezione.
 *
 */
public final class Combinations implements Serializable {

	public class Node implements Serializable {
		private final GenericObject firstObject;
		private final GenericObject secondObject;
		private final GenericObject resultantObject;

		private Node(GenericObject firstObject, GenericObject secondObject, GenericObject resultantObject) {
			this.firstObject = firstObject;
			this.secondObject = secondObject;
			this.resultantObject = resultantObject;
		}

		private GenericObject getFirstObject() {
			return firstObject;
		}

		private GenericObject getSecondObject() {
			return secondObject;
		}

		private GenericObject getResultantObject() {
			return resultantObject;
		}
	}

	private ArrayList<Node> combinationsList = new ArrayList<>();

        public Combinations(){

        }

        public ArrayList<Node> getCombinationsList(){
            return combinationsList;
        }

        public void setCombinations(ArrayList<Node> combinationsList){
            this.combinationsList = combinationsList;
        }

	public void addCombination(GenericObject firstObject, GenericObject secondObject, GenericObject resultantObject) {
		combinationsList.add(new Node(firstObject,secondObject,resultantObject));
	}

	public GenericObject getResultantObject(GenericObject firstObject, GenericObject secondObject) {
		GenericObject finalObject = null;
		for(Node node : combinationsList) {
			if((firstObject.equals(node.getFirstObject()) && secondObject.equals(node.getSecondObject())) ||
				(secondObject.equals(node.getFirstObject()) && firstObject.equals(node.getSecondObject())))  {
				finalObject = node.getResultantObject();
				break;
			}
		}
		return finalObject;
	}
}
