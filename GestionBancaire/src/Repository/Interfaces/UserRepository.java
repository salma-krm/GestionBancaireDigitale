package Repository.Interfaces;

import Module.User;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
    User findById(UUID id);
    void update(User user);
}
