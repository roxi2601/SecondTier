package logic.users;

import communicationWithFirstTier.UserNotFoundException;
import communicationWithThirdTier.Communicator;
import shared.User;
import shared.UserDTO;

import java.io.IOException;
import java.net.SocketException;

public class UsersLogic
{
   Communicator communicator= Communicator.getInstance();
  public UsersLogic() throws Exception
  {
  }

  public User login(String username, String password)
  {
    User user = null;
    try{
      user = getUserFromDatabase(username);
    }catch (Exception e)
    {
      System.out.println(e);
      throw new RuntimeException("Connection failed");
    }

      if(user==null)
      {
       throw new UserNotFoundException("Username not found");
      }
      else if(user.getPassword().equals(password))
      {
        return user;
      }
        throw new UserNotFoundException("Wrong password");
  }
 // @// TODO: 11/27/2020
  public User signUp(User user) throws IOException, ClassNotFoundException {

    try{
      User userFromDatabase = getUserFromDatabase(user.getUsername());
      if(userFromDatabase !=null)
          return null;
    }catch (Exception e)
    {
      System.out.println(e);
      throw new RuntimeException("Connection failed");
    }

      return saveUserInDatabase(user);

  }
  public User getUserFromDatabase(String username)
      throws IOException, ClassNotFoundException, SocketException
  {
    return communicator.getUserFromDatabase(username);
  }

  public User saveUserInDatabase(User newUser)
          throws IOException, ClassNotFoundException, SocketException
  {
    return communicator.saveUserInDatabase(newUser);
  }
}
