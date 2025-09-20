package Module;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id = UUID.randomUUID();
    public Account account;
    private double amount;
    private TransactionType type;
    private LocalDateTime date;

    public Transaction(Account account, double amount, TransactionType type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
    }

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    @Override
    public String toString() {
        return "[" + date + "] " + type + " de " + amount + " € sur le compte " + account.getAccountId();
    }
}
