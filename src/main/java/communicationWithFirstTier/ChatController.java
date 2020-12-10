package communicationWithFirstTier;

import logic.chats.ChatsLogic;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import shared.Message;
import shared.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ChatController
{
    private final ChatModelAssembler assembler;
    private final ChatsLogic logic;

    public ChatController(ChatModelAssembler chatAssembler, ChatsLogic chatsLogic)
    {
        this.assembler = chatAssembler;
        this.logic = chatsLogic;
    }

   /*  @GetMapping("/users/{username}")
    EntityModel<User> getUsername(@PathVariable String username)
    {
        User user = logic.getUsername(username);
        System.out.println(user.getUsername());
       return assembler.toModel(user);
    }*/

    @GetMapping("/chats")
    List<EntityModel<Message>> getAllChats()
    {
        return logic.getAllChats().stream().map(assembler::toModel).collect(
                Collectors.toList());
    }

    @PutMapping ("/updateChat")
    EntityModel<Message> updateChat(@RequestBody Message message)
    {
        Message updatedChat = logic.updateChat(message);
        return assembler.toModel(updatedChat);
    }

    @DeleteMapping("/chats/{message}")
    void deleteMessage(@PathVariable String message)
    {
        logic.deleteMessage(message);
    }

    @PostMapping("/sendMessage")
    EntityModel<Message> sendMessage(@RequestBody Message messageBody)
    {
        Message message = logic.sendMessage(messageBody);
        return assembler.toModel(message);
    }
}
