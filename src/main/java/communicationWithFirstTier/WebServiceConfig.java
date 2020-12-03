package communicationWithFirstTier;

import logic.accounts.AccountsLogic;
import logic.offers.ArtworksLogic;
import logic.users.UsersLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import shared.Artwork;

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
    return new AccountsLogic();
  }
  @Bean
  public ArtworksLogic ArtworksLogic() throws Exception
  {
    return new ArtworksLogic();
  }
}
