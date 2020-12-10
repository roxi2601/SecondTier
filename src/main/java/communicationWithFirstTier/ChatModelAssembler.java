package communicationWithFirstTier;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shared.Message;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ChatModelAssembler implements RepresentationModelAssembler<Message, EntityModel<Message>> {

    @Override
    public EntityModel<Message> toModel(Message chat) {
        return EntityModel.of(chat,
                /*linkTo(methodOn(ChatController.class).getUsername(chat.getUsername())).withSelfRel(),*/
                linkTo(methodOn(ChatController.class).getAllChats()).withRel("chats"),
                linkTo(methodOn(ChatController.class).sendMessage(chat)).withSelfRel(),
                linkTo(methodOn(ChatController.class).updateChat(chat)).withSelfRel()
        );
    }
}
