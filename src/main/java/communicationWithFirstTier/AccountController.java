package communicationWithFirstTier;


import logic.accounts.AccountsLogic;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shared.Account;
import shared.Artwork;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;


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

  /*  @DeleteMapping("/accounts/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable int id) {

        logic.deleteById(id);

        return ResponseEntity.noContent().build();
    }*/
   /* @PutMapping("/account/{username}")
    ResponseEntity<?> replaceAccount(@RequestBody Account newAccount, @PathVariable String username) {

        Account updatedAccount = logic.getAccountFromDatabase(username) //
                .map(account -> {
                    account.s(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                }) //
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }*/
    @PostMapping("/signUp")
    EntityModel<Account> signUp(@RequestBody Account account) {
        Account addedAccount = logic.signUp(account);
        return assembler.toModel(addedAccount);
    }

    @GetMapping("/account/{username}")
    EntityModel<Account> getAccount(@PathVariable String username)
    {
        Account account = logic.getAccountFromDatabase(username);
        return assembler.toModel(account);
    }

}
