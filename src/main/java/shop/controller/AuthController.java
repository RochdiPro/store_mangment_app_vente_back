package shop.controller;

import com.example.shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        try {
            User user = authService.login(username, password);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User newUser = authService.register(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/reset-admin")
    public ResponseEntity<Void> resetAdmin() {
        authService.resetAdmin();
        return ResponseEntity.ok().build();
    }
}
