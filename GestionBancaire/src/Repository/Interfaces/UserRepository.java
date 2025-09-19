package Repository.Interfaces;

import Module.User;
import java.util.UUID;

public interface UserRepository {
    User findByEmail(String email);
    void save(User user);
    void update(User user);
    User findById(UUID id);
}
