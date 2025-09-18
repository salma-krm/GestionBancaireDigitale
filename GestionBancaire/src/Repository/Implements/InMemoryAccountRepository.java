package Repository.Implements;

import Repository.Interfaces.AccountRepository;
import Module.Account;

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
}
