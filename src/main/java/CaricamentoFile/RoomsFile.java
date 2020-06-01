package CaricamentoFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Entita.Room;

public class RoomsFile {

	public static void saveRooms(ArrayList<Room> rooms) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("stanze.dat"));
		out.writeObject(rooms);
		out.close();
	}

	public static ArrayList<Room> loadRooms() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Room> rooms;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("stanze.dat"));
		rooms = (ArrayList<Room>) in.readObject();
		in.close();
		return rooms;
	}
}
