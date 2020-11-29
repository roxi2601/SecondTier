package communicationWithFirstTier;


import logic.accounts.AccountsLogic;
import logic.users.UsersLogic;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shared.Account;
import shared.User;

@RestController
public class AccountController
{

    private final AccountsLogic logic;
    private final AccountModelAssembler assembler;

    AccountController(AccountsLogic accountsLogic, AccountModelAssembler assembler)
    {

        this.logic = accountsLogic;
        this.assembler = assembler;
    }

    @PostMapping("/signUp")
    EntityModel<Account> signUp(@RequestBody Account account) {
        Account addedAccount = logic.signUp(account);
        System.out.println("hello sign up");
        return assembler.toModel(addedAccount);
    }

}
