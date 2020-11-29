package communicationWithFirstTier;

import logic.accounts.AccountsLogic;
import logic.users.UsersLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "communicationWithFirstTier")
public class WebServiceConfig
{
  @Bean
  public UsersLogic UsersLogic() throws Exception
  {
    return new UsersLogic();
  }

  @Bean
  public AccountsLogic AccountsLogic() throws Exception
  {
    System.out.println("web config");
    return new AccountsLogic();
  }
}
