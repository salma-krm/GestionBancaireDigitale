package Repository.Interfaces;
import  Module.Account ;
public interface AccountRepository {
    void save(Account account);
    Account findById(int id);
}
