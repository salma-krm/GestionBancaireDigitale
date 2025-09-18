package Module;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
public class Account {

    private String accountId;
    private User user;
    private Double balance;
    private boolean active;
    private LocalDateTime createdAt;

    public Account(User user) {
        this.user = user;
        this.balance = 0.00;
        this.accountId = generateAccountId();
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public Double getBalance() { return balance; }
    public String getAccountId() { return accountId; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getUser() { return user; }

    public void setBalance(Double balance) { this.balance = balance; }
    public void setActive(boolean active) { this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    private String generateAccountId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder id = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 14; i++) {
            int index = rand.nextInt(chars.length());
            id.append(chars.charAt(index));
        }
        return id.toString();
    }
}
