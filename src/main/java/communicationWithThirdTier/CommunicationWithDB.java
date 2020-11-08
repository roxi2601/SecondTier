package communicationWithThirdTier;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommunicationWithDB {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            Socket socket = new Socket("localhost", 5678);

            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromSever = new ObjectInputStream(socket.getInputStream());

            String connection = keyboard.nextLine();
            outToServer.writeObject(connection);


            System.out.println((String) inFromSever.readObject());

            String username = keyboard.nextLine();
            outToServer.writeObject(username);

            System.out.println((String) inFromSever.readObject());

            String password = keyboard.nextLine();
            outToServer.writeObject(password);

            System.out.println((String) inFromSever.readObject());

            socket.close();
        }
    }
}