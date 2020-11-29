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

  public User signUp(User user)  {
      User userFromDatabase=null;
    try{
      userFromDatabase = getUserFromDatabase(user.getUsername());
    }catch (Exception e)
    {
      System.out.println(e);
      throw new RuntimeException("Connection failed");
    }

    if(userFromDatabase!=null)
    {
        try {
            throw new Exception("Username already exists");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      return saveUserInDatabase(user);

  }
  public User getUserFromDatabase(String username)
      throws IOException, ClassNotFoundException, SocketException
  {
    return communicator.getUserFromDatabase(username);
  }

  public User saveUserInDatabase(User newUser)
  {
    return communicator.saveUserInDatabase(newUser);
  }
}
