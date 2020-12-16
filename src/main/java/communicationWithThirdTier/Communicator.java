package communicationWithThirdTier;

import shared.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Communicator {
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;


    private static Communicator instance;

    public synchronized static Communicator getInstance() throws Exception {
        if (instance == null) {
            instance = new Communicator();
        }
        return instance;
    }

    private Communicator() throws IOException {

        Socket socket = new Socket("localhost", 1098);

        outToServer = new ObjectOutputStream(socket.getOutputStream());
        inFromServer = new ObjectInputStream(socket.getInputStream());

    }

    public User getUserFromDatabase(String username) {
        try {
            Request request = new Request("getUser", username);
            outToServer.writeObject(request);
            UserDTO userDto = (UserDTO) inFromServer.readObject();
            User user = new User();
            if (userDto != null) {
                user.setUserId(userDto.getUserId());
                user.setPassword(userDto.getPassword());
                user.setUsername(userDto.getUsername());
                user.setSecurityLevel(userDto.getSecurityLevel());
                System.out.println(user.getUserId());
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Artwork get(int id) {
        try {
            Request request = new Request("getArtwork", id);
            outToServer.writeObject(request);
            ArtworkDTO dto = (ArtworkDTO) inFromServer.readObject();
            if (dto == null) {
                return null;
            }
            return new Artwork(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountFromDatabase(int userId)
    {
        try
        {
            Request request = new Request("getAccount", userId);
            outToServer.writeObject(request);
            Thread.sleep(500);
            AccountDTO dto = (AccountDTO) inFromServer.readObject();
            if (dto == null)
            {
                return  null;
            }
            System.out.println(dto.getUserId());
            return new Account(dto);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public List<Artwork> getAllArtworks() {
        List<Artwork> artworks = new ArrayList<>();
        try {

            Request request = new Request("getArtworks", null);
            outToServer.writeObject(request);
            Object obj = inFromServer.readObject();
            System.out.println(obj instanceof List);
            List<ArtworkDTO> dtos = (List<ArtworkDTO>) obj;
            for (ArtworkDTO dto : dtos) {
                artworks.add(new Artwork(dto));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artworks;
    }

    public List<Message> getAllChats() {
        List<Message> messages = new ArrayList<>();
        try {

            Request request = new Request("getAllChats", null);
            outToServer.writeObject(request);
            Object obj = inFromServer.readObject();
            System.out.println(obj instanceof List);
            List<MessageDTO> dtos = (List<MessageDTO>) obj;
            for (MessageDTO dto : dtos) {
                messages.add(new Message(dto));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    public Message saveMessageInDb(Message message) {
        try {
            MessageDTO dtoToSend = new MessageDTO(message);
            Request request = new Request("saveMessage", dtoToSend);
            outToServer.writeObject(request);
            MessageDTO dto = (MessageDTO) inFromServer.readObject();
            Message sent = new Message(dto);
            if (sent.getBody() != null)
                return sent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Artwork saveArtworkInDb(Artwork artwork) {
        try {
            ArtworkDTO dtoToSend = new ArtworkDTO(artwork);
            Request request = new Request("saveArtwork", dtoToSend);
            outToServer.writeObject(request);
            ArtworkDTO dto = (ArtworkDTO) inFromServer.readObject();
            Artwork saved = new Artwork(dto);
            if (saved.getTitle() != null)
                return saved;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account editAccount(Account account)
    {
        AccountDTO dto =new AccountDTO();
        try{
            AccountDTO dtoToSend = new AccountDTO(account);
            System.out.println(dtoToSend.getFirstName());
            Request request = new Request("editAccount",dtoToSend);
            outToServer.writeObject(request);
            Thread.sleep(500);
            dto = (AccountDTO)inFromServer.readObject();

            Account saved = new Account(dto);

            if(saved.getUsername()!=null){
                return saved;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println(dto);
        }
        return  null;
    }

    public Message updateChat(Message message) {
        MessageDTO dto = new MessageDTO();
        try {
            MessageDTO dtoToSend = new MessageDTO(message);
            System.out.println(dtoToSend.getChats());
            Request request = new Request("updateChat", dtoToSend);
            outToServer.writeObject(request);
            Thread.sleep(100);
            dto = (MessageDTO) inFromServer.readObject();

            Message saved = new Message(dto);

            if (saved.getUsername() != null) {
                return saved;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(dto);
        }
        return null;
    }

    public Artwork editArtwork(Artwork artwork) {
        try {
            ArtworkDTO dtoToSend = new ArtworkDTO(artwork);
            Request request = new Request("editArtwork", dtoToSend);
            outToServer.writeObject(request);
            ArtworkDTO dto = (ArtworkDTO) inFromServer.readObject();
            Artwork saved = new Artwork(dto);
            if (saved.getTitle() != null)
                return saved;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteArtwork(int id) {
        try {
            Request request = new Request("deleteArtwork", id);
            outToServer.writeObject(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(String message) {
        try {
            Request request = new Request("deleteMessage", message);
            outToServer.writeObject(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public void deleteAccount(int userId)
    {
        try{
            Request request = new Request("deleteAccount",userId);
            outToServer.writeObject(request);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
