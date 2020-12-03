package logic.accounts;
import communicationWithFirstTier.AccountException;
import communicationWithFirstTier.ArtworkException;
import communicationWithThirdTier.Communicator;
import shared.Account;


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
        Account accountFromDatabase;
        try{
            accountFromDatabase = getAccountFromDatabase(account.getUserId());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new AccountException("Connection failed");

        }
        if(accountFromDatabase==null)
        {
            return saveAccountInDatabase(account);
        }
        throw new AccountException("Username already exists");

    }
    /*public Account deleteAccount(String username)
    {
        Account accountFromDatabase=null;
        try{
            accountFromDatabase = getAccountFromDatabase(username);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new AccountException("Connection failed");

        }
        if(accountFromDatabase!=null)
        {
            return deleteAccountFromDatabase(username);
        }
        throw new AccountException("Username does not exists");

    }*/
    /*public Account updateAccount(Account account)
    {
        Account accountFromDatabase=null;
        try{
            accountFromDatabase = getAccountFromDatabase(account.getUsername());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new AccountException("Connection failed");

        }
        if(accountFromDatabase==null)
        {
            return saveAccountInDatabase(account);
        }
        throw new AccountException("Username already exists");

    }
*/
    public Account get(int userId)
    {
        Account account;
        try{
            account =  communicator.getAccountFromDatabase(userId);
        }
        catch (Exception e)
        {
            throw new ArtworkException("Server error, could not load this offer");
        }
        if(account!=null)
        {
            return account;
        }
        throw  new ArtworkException("This account no longer exist");
    }
    public Account getAccountFromDatabase(int userId)
    {
        return communicator.getAccountFromDatabase(userId);
    }

    public Account saveAccountInDatabase(Account newAccount)
    {
        return communicator.saveAccountInDatabase(newAccount);
    }
    /*public Account deleteAccountFromDatabase(String username)
    {
        return communicator.deleteAccountFromDatabase(username);
    }*/
}

