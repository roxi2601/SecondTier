package communicationWithFirstTier;

import logic.users.UsersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
  @PostMapping("/signUp")
  EntityModel<User> signUp(@RequestBody User user) {
    User addedUser = logic.signUp(user);
    System.out.println("hello sign up");
    return assembler.toModel(addedUser);
  }

}
