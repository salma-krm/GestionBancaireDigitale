package Repository.Interfaces;

import Module.Account;
import Module.User;
import java.util.UUID;

public interface AccountRepository {
    void save(Account account);
    Account findById(UUID id);
    Account update(User user, double balance);
    Account retrait(Account account, double balance);
}
