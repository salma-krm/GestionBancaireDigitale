package Repository.Interfaces;
import  Module.Account ;
import Module.User;
public interface AccountRepository {
    void save(Account account);
    Account findById(int id);
    Account update (User user , double balance);
}
