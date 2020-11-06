package communicationWithThirdTier;

import shared.User;

import java.util.ArrayList;
import java.util.List;

public class TemporaryDatabase
{
  private List<User> users;

  public TemporaryDatabase()
  {
    users = new ArrayList<>();
    User user= new User(1,"Julia","0101",1);
    users.add(user);
  }
  public User getUserByUsername(String username)
  {
    for (User user:users)
    {
      if(user.getUsername().equals(username))
      {
        return user;
      }
    }
    return null;
  }
}
