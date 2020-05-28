package CaricamentoFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Entita.Stanza;

public class CaricaStanze {

	public static void salvaStanze(ArrayList<Stanza> luoghi) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("stanze.dat"));
		out.writeObject(luoghi);
		out.close();
	}

	public static ArrayList<Stanza> caricaOggetti() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Stanza> luoghi;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("stanze.dat"));
		luoghi = (ArrayList<Stanza>) in.readObject();
		in.close();
		return luoghi;
	}
}