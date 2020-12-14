package logic.chats;

import communicationWithFirstTier.AccountException;
import communicationWithFirstTier.ChatException;
import communicationWithThirdTier.Communicator;
import shared.Message;
import shared.User;

import java.util.List;

public class ChatsLogic
{
    Communicator communicator = Communicator.getInstance();

    public ChatsLogic() throws Exception
    {
    }

    public Message sendMessage(Message message)
    {
        Message sentMessage;
        try{
            sentMessage = communicator.saveMessageInDb(message);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new ChatException("Server error");
        }
        if(sentMessage!= null)
        {
            return sentMessage;
        }
        throw new ChatException("Could not send the message");
    }

    public void deleteMessage(String message)
    {
        communicator.deleteMessage(message);
    }

    public Message updateChat(Message message)
    {
        Message updatedChat;
        try{
            updatedChat = communicator.updateChat(message);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new AccountException("Server error");
        }
        if(updatedChat!= null)
        {
            return updatedChat;
        }
        throw new ChatException("Could not update chat");
    }

    public List<Message> getAllChats()
    {
        try {
            return communicator.getAllChats();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ChatException("Server error, could not load previous messages");
        }
    }

    public User getUsername(String username)
    {
        User user;
        try {
            user = communicator.getUserFromDatabase(username);
        }
        catch (Exception e)
        {
            throw new ChatException("Server error, could not find this user");
        }
        if(user!=null)
        {
            return user;
        }
        throw new ChatException("This user no longer exist");
    }

}
