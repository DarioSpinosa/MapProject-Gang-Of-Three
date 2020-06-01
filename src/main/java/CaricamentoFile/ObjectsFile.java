package CaricamentoFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import General.GenericObject;

public class ObjectsFile {

	public static void saveObjects(ArrayList<GenericObject> objects) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("oggetti.dat"));
		out.writeObject(objects);
		out.close();
	}

	public static ArrayList<GenericObject> loadObjects() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<GenericObject> objects;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("oggetti.dat"));
		objects = (ArrayList<GenericObject>) in.readObject();
		in.close();
		return objects;
	}
}
