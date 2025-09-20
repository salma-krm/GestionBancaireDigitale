package Repository.Implements;

import Module.Account;
import Module.User;
import Repository.Interfaces.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryAccountRepository implements AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public void save(Account account) { accounts.add(account); }

    public Account findById(UUID id) {
        return accounts.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Account update(User user, double balance) {
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getUser().getId().equals(user.getId())) {
                acc.setBalance(acc.getBalance() + balance);
                accounts.set(i, acc);
                return acc;
            }
        }
        return null;
    }

    public Account retrait(Account account, double amount) {
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getId().equals(account.getId()) && acc.getBalance() >= amount) {
                acc.setBalance(acc.getBalance() - amount);
                accounts.set(i, acc);
                return acc;
            }
        }
        return null;
    }
}
