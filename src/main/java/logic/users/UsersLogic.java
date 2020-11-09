package logic.users;

import communicationWithFirstTier.UserNotFoundException;
import communicationWithThirdTier.TemporaryDatabase;
import shared.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UsersLogic
{
   TemporaryDatabase db = new TemporaryDatabase();
  public UsersLogic()
  {
  }

  public User login(String username, String password)
  {
      User user = getUserFromDatabase(username);
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
  {
    //here it asks some class from 'communicationWithThirdTier' for the user giving username as argument
    return db.getUserByUsername(username);
  }
}
