package shared;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Account implements  Serializable,IAccount  {
    private int userId;
    private String username;
    private String password;
    private int securityLevel;
    private String firstName;
    private String lastName;
    private String description;
    private byte[] pictureBytes;


    public Account()
    {}

    public Account(int userId, String username, String password,int securityLevel, String firstName,
                   String lastName, String description, byte[] pictureBytes)
    {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.securityLevel =securityLevel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.pictureBytes = pictureBytes;
    }
    public Account(AccountDTO accountDTO)
    {
        this(accountDTO.getUserId(), accountDTO.getUsername(), accountDTO.getPassword(),accountDTO.getSecurityLevel(),
                accountDTO.getFirstName(), accountDTO.getLastName(), accountDTO.getDescription()
                , accountDTO.getPictureBytes());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPictureBytes() {
        return pictureBytes;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictureBytes(byte[] pictureBytes) {
        this.pictureBytes = pictureBytes;
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
