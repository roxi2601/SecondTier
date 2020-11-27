package shared;

import javax.persistence.Entity;

@Entity
public class User
{
  private int id;
  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private String confirmPassword;
  private String description;
  private byte[] img;
  private int securityLevel;

public User()
{}

public User(int id, String username, String password,int securityLevel, String firstName,
            String lastName, String confirmPassword, String description, byte[] img)
{
this.id = id;
this.password = password;
this.username = username;
this.securityLevel =securityLevel;
this.firstName = firstName;
this.lastName = lastName;
this.confirmPassword = confirmPassword;
this.description = description;
this.img = img;
}

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public String getDescription() {
    return description;
  }

  public byte[] getImg() {
    return img;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setImg(byte[] img) {
    this.img = img;
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
