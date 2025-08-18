package com.example.authdemo.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService() { // default constructor wiring in-memory repo & encoder for simplicity
        this.userRepository = new InMemoryUserRepository();
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // For testing / DI
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // INTENTIONAL BUG : removed null checks; will throw NPE or allow bad data
    // public User signup(String username, String rawPassword) {
        
    //     if (username.isBlank()) {
    //         throw new IllegalArgumentException("Username required");
    //     }
    //     if (rawPassword == null || rawPassword.length() < 6) {
    //         throw new IllegalArgumentException("Password must be at least 6 chars");
    //     }
    //     if (userRepository.existsByUsername(username)) {
    //         throw new IllegalStateException("Username already taken");
    //     }
    //     String hash = passwordEncoder.encode(rawPassword);
    //     User user = new User(username, hash);
    //     userRepository.save(user);
    //     return user;
    // }

    // public boolean login(String username, String rawPassword) {

    //     // INTENTIONAL BUG: no explicit null guard; should raise IllegalArgumentException
    //     return userRepository.findByUsername(username)
    //             .map(u -> passwordEncoder.matches(rawPassword, u.getPasswordHash()))
    //             .orElse(false);
    // }

    // CORRECT IMPLEMENTATION 
    public User signup(String username, String rawPassword) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username required");
        }
        if (rawPassword == null || rawPassword.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 chars");
        }
        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("Username already taken");
        }
        String hash = passwordEncoder.encode(rawPassword);
        User user = new User(username, hash);
        userRepository.save(user);
        return user;
    }

    public boolean login(String username, String rawPassword) {
        if (username == null || rawPassword == null) {
            throw new IllegalArgumentException("Username and password required");
        }
        return userRepository.findByUsername(username)
                .map(u -> passwordEncoder.matches(rawPassword, u.getPasswordHash()))
                .orElse(false);
    }
    
}
