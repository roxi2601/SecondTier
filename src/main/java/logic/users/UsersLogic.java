package logic.users;

import communicationWithFirstTier.UserNotFoundException;
import communicationWithThirdTier.Communicator;
import communicationWithThirdTier.TemporaryDatabase;
import shared.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

public class UsersLogic
{
  //TemporaryDatabase db  = new TemporaryDatabase();
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
      e.printStackTrace();
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

  public User getUserFromDatabase(String username)
      throws IOException, ClassNotFoundException
  {

    return communicator.getUserFromDatabase(username);
  }
}
