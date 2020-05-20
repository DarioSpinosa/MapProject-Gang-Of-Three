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
		private final GenericObject oggetto1;
		private final GenericObject oggetto2;
		private final GenericObject oggettoRisultato;
		
		public Node(GenericObject oggetto1, GenericObject oggetto2, GenericObject oggettoRisultato) {
			this.oggetto1 = oggetto1;
			this.oggetto2 = oggetto2;
			this.oggettoRisultato = oggettoRisultato;
		}
		
		public GenericObject getOggetto1() {
			return oggetto1;
		}
		
		public GenericObject getOggetto2() {
			return oggetto2;
		}
		
		public GenericObject getOggettoRisultato() {
			return oggettoRisultato;
		}
	}
	
	private static ArrayList<Node> combinationsList = new ArrayList<>();
	
	public static void addCombination(GenericObject oggetto1, GenericObject oggetto2, GenericObject oggettoRisultato) {
		combinationsList.add(new Node(oggetto1,oggetto2,oggettoRisultato));
	}
	
	public static GenericObject testCombination(GenericObject oggetto1, GenericObject oggetto2) {
		GenericObject oggettoFinale = null;
		for(Node nodo : combinationsList) {
			if((oggetto1.equals(nodo.getOggetto1()) && oggetto2.equals(nodo.getOggetto2())) || 
				(oggetto2.equals(nodo.getOggetto1()) && oggetto1.equals(nodo.getOggetto2())))  {
				oggettoFinale = nodo.getOggettoRisultato();
				break;
			}
		}
		return oggettoFinale;
	}
}
