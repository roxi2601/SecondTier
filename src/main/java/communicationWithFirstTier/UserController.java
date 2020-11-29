package communicationWithFirstTier;
import logic.users.UsersLogic;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import shared.User;

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
