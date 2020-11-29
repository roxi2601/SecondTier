package communicationWithFirstTier;

import logic.accounts.AccountsLogic;
import logic.users.UsersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shared.Account;
import shared.User;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController
{
  private final UsersLogic logic;
  private final UserModelAssembler assembler;

  UserController(UsersLogic logic, UserModelAssembler assembler)
  {
    this.logic = logic;
    this.assembler = assembler;
  }

  @PostMapping("/login")
  EntityModel<User> one(@RequestBody User user)
  {
     User loggingUser = logic.login(user.getUsername(),user.getPassword());
     return assembler.toModel(loggingUser);
  }


}
