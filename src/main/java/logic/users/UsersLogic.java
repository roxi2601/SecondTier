package logic.users;

import shared.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UsersLogic
{

  public UsersLogic()
  {

  }

  public User login(String username, String password) throws Exception
  {
    User user = getUserFromDatabase(username);
    try{
      if(user.getPassword().equals(password))
      {
        return user;
      }
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      //Exception occurs when the username could not be found or other exception(we will know from the message)
    }
    throw new Exception("Wrong password");
  }

  public User getUserFromDatabase(String username)
  {
    //here it asks some class from 'communicationWithThirdTier' for the user giving username as argument
    throw new NotImplementedException();
  }
}
