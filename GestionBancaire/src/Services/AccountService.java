package Services;

import Module.Account;
import Module.User ;
import Repository.Interfaces.AccountRepository;

import java.util.AbstractList;

public class AccountService {
    private static final AccountRepository accountRepository =  null ;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static Account deposer(User user, double balance) {
        AbstractList<Account> accounts = user.getAccount();
        if (accounts != null && !accounts.isEmpty()) {
            Account updatedAccount = accountRepository.update(user, balance);
            return updatedAccount;
        }
        return null;
    }


    public void create(Account account) {
        accountRepository.save(account);
    }

}
