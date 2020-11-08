package communicationWithThirdTier;

import java.io.IOException;
import java.net.Socket;

import shared.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommunicationWithDB {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        TemporaryDatabase user = new TemporaryDatabase();
        while (true) {
            Socket socket = new Socket("localhost", 1099);

            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            User localuser = user.getUserByUsername("Julia");
            String username = localuser.getUsername();
            outToServer.writeObject(username);

            System.out.println((String) inFromServer.readObject());


            socket.close();
        }
    }
}