package communicationWithThirdTier;

import shared.Account;
import shared.AccountDTO;
import shared.UserDTO;
import shared.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

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

  private Communicator() throws IOException
  {

      Socket socket = new Socket("localhost", 1098);

      outToServer = new ObjectOutputStream(socket.getOutputStream());
      inFromServer = new ObjectInputStream(socket.getInputStream());

  }

  public User getUserFromDatabase(String username)
  {
    try{
      Request request = new Request("getUser",username);
      outToServer.writeObject(request);
      UserDTO userDto = (UserDTO)inFromServer.readObject();
      User user = new User();
      if(userDto !=null){
        user.setUserId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setSecurityLevel(userDto.getSecurityLevel());
        return user;
      }
     }
    catch(Exception e){
      e.printStackTrace();
  }
    return  null;
  }

  public Account getAccountFromDatabase(String username)
  {
    try{
      Request request = new Request("getAccount",username);
      outToServer.writeObject(request);
      AccountDTO accountDto = (AccountDTO)inFromServer.readObject();
      Account account = new Account();
      if(accountDto !=null){
        account.setAccountId(accountDto.getAccountId());
        account.setPassword(accountDto.getPassword());
        account.setUsername(accountDto.getUsername());
        account.setSecurityLevel(accountDto.getSecurityLevel());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setDescription(accountDto.getDescription());
        account.setImg(accountDto.getImg());
        return account;
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return  null;
  }

  public Account saveAccountInDatabase(Account newAccount){
    try{
      Request request = new Request("saveUser",newAccount);
      outToServer.writeObject(request);
    }
    catch(Exception e)
    {
     e.printStackTrace();
    }
    return newAccount;
  }
}
