package communicationWithFirstTier;

import logic.accounts.AccountsLogic;
import logic.chats.ChatsLogic;
import logic.offers.ArtworksLogic;
import logic.users.UsersLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
    return new AccountsLogic();
  }

  @Bean
  public ArtworksLogic ArtworksLogic() throws Exception
  {
    return new ArtworksLogic();
  }

  @Bean
  public ChatsLogic ChatsLogic() throws Exception
  {
    return new ChatsLogic();
  }
}
