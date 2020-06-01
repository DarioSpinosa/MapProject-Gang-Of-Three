package CaricamentoFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entita.Room;

public class RoomFile {

	public static void saveRoom(Room room) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("stanze.dat"));
		out.writeObject(room);
		out.close();
	}

	public static Room loadRoom() throws FileNotFoundException, IOException, ClassNotFoundException {
		Room room;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("stanze.dat"));
		room = (Room) in.readObject();
		in.close();
		return room;
	}
}
