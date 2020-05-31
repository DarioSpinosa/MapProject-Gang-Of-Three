/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

/**
 *
 * @author Elio
 */
import java.util.ArrayList;

public final class Combinations {
	
	private static class Node	{
		private final GenericObject firstObject;
		private final GenericObject secondObject;
		private final GenericObject resultantObject;
		
		public Node(GenericObject firstObject, GenericObject secondObject, GenericObject resultantObject) {
			this.firstObject = firstObject;
			this.secondObject = secondObject;
			this.resultantObject = resultantObject;
		}
		
		public GenericObject getFirstObject() {
			return firstObject;
		}
		
		public GenericObject getSecondObject() {
			return secondObject;
		}
		
		public GenericObject getResultantObject() {
			return resultantObject;
		}
	}
	
	private static ArrayList<Node> combinationsList = new ArrayList<>();
	
	public static void addCombination(GenericObject firstObject, GenericObject secondObject, GenericObject resultantObject) {
		combinationsList.add(new Node(firstObject,secondObject,resultantObject));
	}
	
	public static GenericObject getCombination(GenericObject firstObject, GenericObject secondObject) {
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
