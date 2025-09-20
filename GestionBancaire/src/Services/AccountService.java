package Services;

import Module.Account;
import Module.Transaction;
import Module.User;
import Repository.Interfaces.AccountRepository;
import Repository.Interfaces.TransactionRepository;

public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void create(Account account) {
        account.getUser().addAccount(account);
        accountRepository.save(account);
    }

    public Account deposer(User user, double amount) {
        if (user.getAccounts().isEmpty()) return null;

        Account account = user.getAccounts().get(0);
        Account updated = accountRepository.update(user, amount);

        if (updated != null) {
            transactionRepository.save(new Transaction(account, amount, Transaction.TransactionType.DEPOSIT));
        }

        return updated;
    }

    public Account retrait(User user, double amount) {
        if (user.getAccounts().isEmpty()) return null;

        Account account = user.getAccounts().get(0);
        Account result = accountRepository.retrait(account, amount);

        if (result != null) {
            transactionRepository.save(new Transaction(account, amount, Transaction.TransactionType.WITHDRAWAL));
        }

        return result;
    }
}
