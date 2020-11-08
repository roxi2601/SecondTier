package communicationWithThirdTier;

import com.google.gson.Gson;
import org.apache.catalina.Server;
import shared.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicationThreadHandler implements Runnable {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private String ip;

    public CommunicationThreadHandler(Socket socket) throws IOException {

        //Create input stream attached to the socket
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //Create output stream attached to the socket
        out = new PrintWriter(socket.getOutputStream(),true);
    }

    @Override
    public void run(){
        Gson gson = new Gson();
        try{
            //Read line from client
            String clientText = in.readLine();
            System.out.println("Client> " + clientText);

            //Convert from Json
            CommunicationWithDB id = gson.fromJson(clientText, CommunicationWithDB.class);
            System.out.println("Client> " + id);

            //Create reply
            User reply = new User("Welcome " + id);
            System.out.println("Reply> " + reply);

            //Convert reply to Json
            String replyJson = gson.toJson(reply);

            //Send reply to client
            System.out.println("Server> " + replyJson);
            out.println(replyJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
