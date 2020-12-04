package communicationWithFirstTier;


import logic.accounts.AccountsLogic;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import shared.Account;



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

    @DeleteMapping("/accounts/{userId}")
    void  delete(@PathVariable int userId)
    {
        logic.deleteAccount(userId);
    }
    @PutMapping ("/editAccount")
    EntityModel<Account> update(@RequestBody Account account)
    {
        Account editedAccount = logic.editAccount(account);
        return assembler.toModel(editedAccount);
    }


    @PostMapping("/signUp")
    EntityModel<Account> signUp(@RequestBody Account account) {
        Account addedAccount = logic.signUp(account);
        System.out.println(account.getUserId());
        return assembler.toModel(addedAccount);
    }

    @GetMapping("/account/{userId}")
    EntityModel<Account> getAccount(@PathVariable int userId)
    {
        Account account = logic.get(userId);
        System.out.println(userId);
        return assembler.toModel(account);
    }

}
