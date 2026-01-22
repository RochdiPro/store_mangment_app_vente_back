package shop.service;

import com.example.shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public User add(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        return userRepository.save(user);
    }

    public User update(Long id, User userData) {
        return userRepository.findById(id).map(user -> {
            user.setNom(userData.getNom());
            user.setTelephone(userData.getTelephone());
            user.setUsername(userData.getUsername());
            user.setPassword(userData.getPassword());
            user.setRole(userData.getRole());
            user.setPermissions(userData.getPermissions());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
