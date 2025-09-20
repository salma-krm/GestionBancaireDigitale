package Repository.Interfaces;

import Module.Transaction;
import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);
    List<Transaction> getAll();
}
