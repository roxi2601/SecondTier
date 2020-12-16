package shared;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class User
{
  private int userId;
  private String username;
  private String password;
  private int securityLevel;

  public User()
  {}

  public User(int userId, String username, String password,int securityLevel)
  {
    this.userId = userId;
    this.password = password;
    this.username = username;
    this.securityLevel =securityLevel;
  }

  public int getUserId()
  {
    return userId;
  }

  public String getUsername()
  {
    return username;
  }

  public int getSecurityLevel()
  {
    return securityLevel;
  }

  public String getPassword()
  {
    return password;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public void setSecurityLevel(int securityLevel)
  {
    this.securityLevel = securityLevel;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
}
