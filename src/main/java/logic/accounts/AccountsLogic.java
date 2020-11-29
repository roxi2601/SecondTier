package logic.accounts;
import communicationWithThirdTier.Communicator;
import shared.Account;


public class AccountsLogic
{
    Communicator communicator= Communicator.getInstance();
    public AccountsLogic() throws Exception
    {
    }

    public Account signUp(Account account)  {
        System.out.println("accounts logic");
        Account accountFromDatabase=null;
        try{
            accountFromDatabase = getAccountFromDatabase(account.getUsername());
        }catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("Connection failed");
        }

        if(accountFromDatabase!=null)
        {
            try {
                throw new Exception("Username already exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ooooooooo");
        return saveAccountInDatabase(account);

    }
    public Account getAccountFromDatabase(String username)
    {
        return communicator.getAccountFromDatabase(username);
    }

    public Account saveAccountInDatabase(Account newAccount)
    {
        return communicator.saveAccountInDatabase(newAccount);
    }
}

