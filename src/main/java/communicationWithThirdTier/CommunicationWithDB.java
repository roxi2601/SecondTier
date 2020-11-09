package communicationWithThirdTier;

import java.io.IOException;
import java.net.Socket;

import org.omg.CORBA.OBJECT_NOT_EXIST;
import shared.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommunicationWithDB {

    public static void main(String[] args)
            throws IOException {

        new Communicator();
    }
}