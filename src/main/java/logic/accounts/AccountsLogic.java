package logic.accounts;
import communicationWithThirdTier.Communicator;
import shared.Account;
import shared.AccountDTO;


public class AccountsLogic
{
    Communicator communicator= null;
    public AccountsLogic() {

        try {
            communicator = Communicator.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problem with communicator accounts login construction");
        }
    }

    public Account signUp(Account account)  {
        System.out.println("accounts logic");
        Account accountFromDatabase=null;
        try{
            accountFromDatabase = getAccountFromDatabase(account.getUsername());
        }catch (Exception e)
        {
            System.out.println("Connection failed");

        }
        System.out.println("aaaaaaaaaaa");
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

