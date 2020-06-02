package CaricamentoFile;

import General.Combinations.Node;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class CombinationsFile {

	public static void saveCombinations(ArrayList<Node> combinations) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("combinazioni.dat"));
		out.writeObject(combinations);
		out.close();
	}

	public static ArrayList<Node> loadCombinations() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Node> combinations;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("combinazioni.dat"));
		combinations = (ArrayList<Node>) in.readObject();
		in.close();
		return combinations;
	}
}
