package communicationWithThirdTier;

import java.io.IOException;
import java.net.Socket;

import shared.User;
import

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommunicationWithDB {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, SQLException {
        //TemporaryDatabase user = new TemporaryDatabase();
        String dbURL = "jdbc:postgresql://localhost:1099/postgres?currentSchema=base";
        String user = "postgres";
        String password = "password";
        Connection conn = null;

        while (true) {
            Socket socket = new Socket("localhost", 1099);

            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
            try {
                Class.forName("com.postgresql.jdbc.Driver");
                conn = DriverManager.getConnection(dbURL, user, password);
                if (conn != null) {
                    System.out.println("Connected to the database");
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Could not find database driver class");
                ex.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("An error occurred. Maybe user/password is invalid");
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            /*User localuser = conn.getUserByUsername("Julia");
            String username = localuser.getUsername();
            outToServer.writeObject(username);*/

            System.out.println((String) inFromServer.readObject());

            socket.close();
            conn.close();
        }
    }
}