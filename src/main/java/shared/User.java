package shared;

import javax.persistence.Entity;

@Entity
public class User
{
  private int id;
  private String username;
  private String password;
  private int securityLevel;

public User()
{}

public User(int id, String username, String password,int securityLevel)
{
this.id = id;
this.password = password;
this.username = username;
this.securityLevel =securityLevel;
}

  public int getId()
  {
    return id;
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

  public void setId(int id)
  {
    this.id = id;
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
