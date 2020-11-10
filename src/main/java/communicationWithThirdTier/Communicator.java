package communicationWithThirdTier;

import shared.DTO;
import shared.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Communicator
{
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;

  private static Communicator instance;

  public synchronized static Communicator getInstance() throws Exception
  {
    if(instance==null)
    {
      instance = new Communicator();
    }
    return instance;
  }

  public Communicator() throws IOException
  {
      Socket socket = new Socket("localhost", 1098);

      outToServer = new ObjectOutputStream(socket.getOutputStream());
      inFromServer = new ObjectInputStream(socket.getInputStream());

  }

  public User getUserFromDatabase(String username)
      throws IOException, ClassNotFoundException
  {
    Request request = new Request("getUser",username);
    outToServer.writeObject(request);
    DTO dto = (DTO)inFromServer.readObject();
    User user = new User();
    if(dto!=null){
      user.setId(dto.getId());
      user.setPassword(dto.getPassword());
      user.setUsername(dto.getUserName());
      user.setSecurityLevel(dto.getSecurityLevel());
      return user;
    }
    return null;
  }
}
