package logic.accounts;
import communicationWithFirstTier.AccountException;
import communicationWithFirstTier.ArtworkException;
import communicationWithThirdTier.Communicator;
import shared.Account;
import shared.Artwork;

import java.util.List;


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
    public Account editAccount(Account account)
    {
        Account editedAccount;
        try{
            editedAccount = communicator.editAccount(account);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new ArtworkException("Server error");
        }
        if(editedAccount!= null)
        {
            return editedAccount;
        }
        throw new ArtworkException("Could not edit account");
    }

    public void deleteAccount(int userId)
    {
        communicator.deleteAccount(userId);
    }

    public Account get(int userId)
    {
        Account account;
        try{
            account =  communicator.getAccountFromDatabase(userId);
        }
        catch (Exception e)
        {
            throw new AccountException("Server error, could not load this offer");
        }
        if(account!=null)
        {
            return account;
        }
        throw  new AccountException("This account no longer exist");
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

