package communicationWithFirstTier;

import logic.users.UsersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shared.User;

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

  @GetMapping("/login/{username}/{password}")
  EntityModel<User> one(@PathVariable String username,@PathVariable String password)
  {
     User user = logic.login(username,password);
     return assembler.toModel(user);
  }


}
