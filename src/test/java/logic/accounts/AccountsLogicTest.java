package logic.accounts;

import communicationWithFirstTier.AccountException;
import communicationWithThirdTier.Communicator;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.function.Executable;
import shared.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AccountsLogicTest {

    Account account;
    AccountsLogic logic;
    Communicator communicator= null;
    @Test
    public void correctSignUp(){
        int id =9;
        account= communicator.getAccountFromDatabase(id);
        assertThrows(AccountException.class, ()->logic.signUp(account));
    }


}
