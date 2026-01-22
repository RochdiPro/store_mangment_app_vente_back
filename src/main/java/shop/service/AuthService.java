package shop.service;

import com.example.shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return user; // connexion réussie
            } else {
                throw new RuntimeException("Mot de passe incorrect");
            }
        }

        // Cas spécial : création ADMIN "rochdi/rochdi"
        if ("rochdi".equals(username) && "rochdi".equals(password)) {
            User admin = new User();
            admin.setNom("Rochdi");
            admin.setTelephone("00000000");
            admin.setUsername("rochdi");
            admin.setPassword("rochdi");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
            return admin;
        }

        throw new RuntimeException("Utilisateur non trouvé");
    }

    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username déjà utilisé");
        }
        return userRepository.save(user);
    }

    public void resetAdmin() {
        Optional<User> adminOpt = userRepository.findByUsername("admin");
        if (adminOpt.isPresent()) {
            User admin = adminOpt.get();
            admin.setPassword("admin");
            admin.setNom("Administrateur");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        } else {
            User admin = new User();
            admin.setNom("Administrateur");
            admin.setTelephone("00000000");
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
