package logic.users;

import communicationWithFirstTier.UserNotFoundException;
import communicationWithThirdTier.TemporaryDatabase;
import shared.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersLogic
{
  public static void main(String[] args) {

   TString dbURL = "jdbc:postgresql://localhost:1099/postgres?currentSchema=base";
   String user = "postgres";
   String password = "password";
   

   try (Connection con = DriverManager.getConnection(dbURL, user, password);
      PreparedStatement pst = con.prepareStatement("SELECT * FROM username");
      ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
        
            System.out.print(rs.getInt(1));
            System.out.print(": ");
            System.out.println(rs.getString(2));
        }

    } catch (SQLException ex) {

      System.out.println("Error occurred");
      ex.printStackTrace();
    }
   }
      
      
  public UsersLogic()
  {
  }

  public User login(String username, String password)
  {
      User user = getUserFromDatabase(username);
      if(user==null)
      {
       throw new UserNotFoundException("username not found");
      }
      else if(user.getPassword().equals(password))
      {
        return user;
      }
        throw new UserNotFoundException("wrong password");
  }

  public User getUserFromDatabase(String username)
  {
    //here it asks some class from 'communicationWithThirdTier' for the user giving username as argument
    return db.getUserByUsername(username);
  }
}
