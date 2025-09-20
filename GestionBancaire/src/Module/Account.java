package Module;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class Account {
    private UUID id = UUID.randomUUID();
    private String accountId;
    private User user;
    private double balance;
    private boolean active;
    private LocalDateTime createdAt;
    private AccountType type;

    public Account(User user, AccountType type) {
        this.user = user;
        this.balance = 0.0;
        this.accountId = generateAccountId();
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.type = type;
    }

    public UUID getId() { return id; }
    public String getAccountId() { return accountId; }
    public User getUser() { return user; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public AccountType getType() { return type; }

    private String generateAccountId() {
        int random1 = (int)(Math.random() * 10000);
        int random2 = (int)(Math.random() * 10000);
        String uuidPart = UUID.randomUUID().toString().substring(0, 4);
        return "BK-" + random1 + "-" + random2 + "-" + uuidPart;
    }

    public enum AccountType {
        COURANT,
        EPARGNE,
        SALAIRE
    }
}
