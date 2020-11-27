package logic.users;

import communicationWithFirstTier.UserNotFoundException;
import communicationWithThirdTier.Communicator;
import shared.User;

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
  public User signUp(String username, String password, String confirmPassword, String firstName,
                     String lastName, String description, byte[] img) throws IOException, ClassNotFoundException {
    User user = new User();
    try{
      user = getUserFromDatabase(username);
    }catch (Exception e)
    {
      System.out.println(e);
      throw new RuntimeException("Connection failed");
    }
    if (user.getUsername().equals(username))
    {
      System.out.println("Username already exists");
    }
    else if(!lastName.isEmpty() && !firstName.isEmpty() && !img.toString().isEmpty() &&
    password.equals(confirmPassword))
    {
      saveUserInDatabase(username);
    }
    return null;
  }
  public User getUserFromDatabase(String username)
      throws IOException, ClassNotFoundException, SocketException
  {
    return communicator.getUserFromDatabase(username);
  }

  public User saveUserInDatabase(String username)
          throws IOException, ClassNotFoundException, SocketException
  {
    return communicator.saveUserInDatabase(username);
  }
}
