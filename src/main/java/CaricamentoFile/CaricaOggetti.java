package CaricamentoFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import General.GenericObject;

public class CaricaOggetti {

	public static void salvaOggetto(ArrayList<GenericObject> oggetti) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("oggetti.dat"));
		out.writeObject(oggetti);
		out.close();
	}

	public static ArrayList<GenericObject> caricaOggetti()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<GenericObject> oggetti;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("oggetti.dat"));
		oggetti = (ArrayList<GenericObject>) in.readObject();
		in.close();
		return oggetti;
	}
}
