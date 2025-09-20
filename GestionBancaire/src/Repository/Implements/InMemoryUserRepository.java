package Repository.Implements;

import Module.User;
import Repository.Interfaces.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();

    public void save(User user) { users.add(user); }

    public User findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst().orElse(null);
    }

    public User findById(UUID id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                return;
            }
        }
    }
}
