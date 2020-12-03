package communicationWithFirstTier;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shared.Account;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>>
{
    @Override
    public EntityModel<Account> toModel(Account account) {
        return EntityModel.of(account,
                linkTo(methodOn(AccountController.class).signUp(account)).withSelfRel(),
                linkTo(methodOn(AccountController.class).getAccount(account.getUsername())).withSelfRel());
    }
}
