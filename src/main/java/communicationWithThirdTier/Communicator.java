package communicationWithThirdTier;

import shared.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Communicator
{
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;

  private static Communicator instance;

  public synchronized static Communicator getInstance() throws Exception
  {
    if(instance==null)
    {
      instance = new Communicator();
    }
    return instance;
  }

  private Communicator() throws IOException
  {

      Socket socket = new Socket("localhost", 1098);

      outToServer = new ObjectOutputStream(socket.getOutputStream());
      inFromServer = new ObjectInputStream(socket.getInputStream());

  }

  public User getUserFromDatabase(String username)
  {
    try{
      Request request = new Request("getUser",username);
      outToServer.writeObject(request);
      UserDTO userDto = (UserDTO)inFromServer.readObject();
      User user = new User();
      if(userDto !=null){
        user.setUserId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setSecurityLevel(userDto.getSecurityLevel());
        System.out.println(user.getUserId());
        return user;
      }
     }
    catch(Exception e){
      e.printStackTrace();
  }
    return  null;
  }

  public Artwork saveArtworkInDb(Artwork artwork)
  {
    try{
      ArtworkDTO dtoToSend = new ArtworkDTO(artwork);
      Request request = new Request("saveArtwork",dtoToSend);
      outToServer.writeObject(request);
      ArtworkDTO dto = (ArtworkDTO)inFromServer.readObject();
      Artwork saved = new Artwork(dto);
      if(saved.getTitle()!=null)
        return saved;
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return  null;
  }

  public Artwork get(int id)
  {
    try
    {
      Request request = new Request("getArtwork", id);
      outToServer.writeObject(request);
      ArtworkDTO dto = (ArtworkDTO) inFromServer.readObject();
      if (dto == null)
      {
        return  null;
      }
      return new Artwork(dto);
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return  null;
  }

  public List<Artwork> getAllArtworks()
  {
    List<Artwork> artworks = new ArrayList<>();
    try{
      Request request = new Request("getArtworks",null);
      outToServer.writeObject(request);
      List<ArtworkDTO> dtos = (List<ArtworkDTO>)inFromServer.readObject();
      for (ArtworkDTO dto :dtos)
      {
        artworks.add(new Artwork(dto));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return artworks;
  }


  public Account getAccountFromDatabase(String username)
  {
    try{
      Request request = new Request("getAccount",username);
      outToServer.writeObject(request);
      AccountDTO accountDto = (AccountDTO)inFromServer.readObject();
      Account account = new Account();
      if(accountDto !=null){
        account.setAccountId(accountDto.getAccountId());
        account.setPassword(accountDto.getPassword());
        account.setUsername(accountDto.getUsername());
        account.setSecurityLevel(accountDto.getSecurityLevel());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setDescription(accountDto.getDescription());
        account.setImg(accountDto.getImg());
        return account;
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return  null;
  }

  public Account saveAccountInDatabase(Account newAccount){
    AccountDTO accountDto;
    try{
      AccountDTO dto = new AccountDTO(newAccount);
      Request request = new Request("saveUser",dto);
      outToServer.writeObject(request);
       accountDto = (AccountDTO)inFromServer.readObject();
       if(accountDto==null)
      {
        throw new Exception("Account received from third tier is null");
      }
      return new Account(accountDto);
    }
    catch(Exception e)
    {
     e.printStackTrace();
    }
    return  null;
  }
}
