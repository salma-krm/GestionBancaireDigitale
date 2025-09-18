package Services;

import Module.Account;
import Repository.Interfaces.AccountRepository;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void create(Account account) {
        accountRepository.save(account);
    }
}
