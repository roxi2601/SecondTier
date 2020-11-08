package communicationWithThirdTier;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CommunicationWithDB {
    private Scanner input;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public CommunicationWithDB(String host, int port) throws IOException {
        //Create socket and streams
        socket = new Socket(host,port);
    }

    public void execute() throws IOException {
        Gson gson = new Gson();
        Scanner inFromUser = new Scanner(System.in);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);

        //Read keyboard input
        System.out.println("Enter your id: ");
        String id = inFromUser.nextLine();
        System.out.println("Enter your message: ");
        String message = inFromUser.nextLine();
        inFromUser.close();

        //Create message object - TODO
        Message messageBody = new Message(message);
        System.out.println("Client object: " + messageBody);

        //Convert to Json
        String json = gson.toJson(messageBody);

        //Send to server
        System.out.println("Client> " + json);
        outToServer.println(json);

        //Read reply from Server
        String serverReply = inFromServer.readLine();
        System.out.println("Server> " + serverReply);

        //Convert Message from Json
        Message reply = gson.fromJson(serverReply,Message.class);
        System.out.println("Message: " + reply);
    }

    public void close() throws IOException {
        socket.close();
    }
}
