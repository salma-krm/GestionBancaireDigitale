package Repository.Implements;

import Repository.Interfaces.AccountRepository;
import Module.Account;
import Module.User;
import java.util.ArrayList;
import java.util.List;

public class InMemoryAccountRepository implements AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public void save(Account account) {
        accounts.add(account);
    }

    @Override
    public Account findById(int id) {
        if (id >= 0 && id < accounts.size()) {
            return accounts.get(id);
        }
        return null;
    }

    public List<Account> findAll() {
        return accounts;
    }
    public Account update(User user, double balance) {
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getUser().getId().equals(user.getId())) {
                double newBalance = acc.getBalance() + balance;
                acc.setBalance(newBalance);
                accounts.set(i, acc);
                return acc;
            }
        }
        return null;
    }

}
