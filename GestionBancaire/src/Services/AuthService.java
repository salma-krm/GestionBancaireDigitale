package Services;

import Module.User;
import Repository.Interfaces.UserRepository;

import java.util.UUID;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public User login(User loginUser) {
        User user = userRepository.findByEmail(loginUser.getEmail());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            return user;
        }
        return null;
    }

    public User update(User updateUser) {
        User existing = userRepository.findById(updateUser.getId());
        if (existing != null) {
            userRepository.update(updateUser);
            return updateUser;
        }
        return null;
    }

    public User findById(UUID id) {
        return userRepository.findById(id);
    }
}
