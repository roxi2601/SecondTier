package shared;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Account implements  Serializable,IAccount  {
    private int accountId;
    private String username;
    private String password;
    private int securityLevel;
    private String firstName;
    private String lastName;
    private String description;
    private byte[] img;


    public Account()
    {}

    public Account(int accountId, String username, String password,int securityLevel, String firstName,
                String lastName, String description, byte[] img)
    {
        this.accountId = accountId;
        this.password = password;
        this.username = username;
        this.securityLevel =securityLevel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.img = img;
    }
    public Account(AccountDTO accountDTO)
    {
        this(accountDTO.getAccountId(), accountDTO.getUsername(), accountDTO.getPassword(),accountDTO.getSecurityLevel(),
                accountDTO.getFirstName(), accountDTO.getLastName(), accountDTO.getDescription()
                , accountDTO.getImg());
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

    public byte[] getImg() {
        return img;
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

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getAccountId()
    {
        return accountId;
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

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
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
