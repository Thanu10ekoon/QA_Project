package com.example.authdemo.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.authdemo.user.InMemoryUserRepository;
import com.example.authdemo.user.UserService;

/**
 * TDD Unit tests for core auth behavior (signup & login).
 */
public class UserServiceTest {

    private InMemoryUserRepository repo;
    private PasswordEncoder encoder;
    private UserService service;

    @BeforeEach
    void setup() {
        repo = new InMemoryUserRepository();
        encoder = new BCryptPasswordEncoder();
        service = new UserService(repo, encoder);
    }

    @Test
    @DisplayName("Signup should create user and hash password")
    void signupCreatesUser() {
        var user = service.signup("alice", "password123");
        assertEquals("alice", user.getUsername());
        assertNotEquals("password123", user.getPasswordHash());
        assertTrue(encoder.matches("password123", user.getPasswordHash()));
    }

    @Test
    @DisplayName("Duplicate username should throw")
    void duplicateUsernameThrows() {
        service.signup("bob", "secret12");
        assertThrows(IllegalStateException.class, () -> service.signup("bob", "another"));
    }

    @Test
    @DisplayName("Too short password should throw")
    void shortPasswordThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.signup("mini", "123"));
    }

    @Test
    @DisplayName("Login success and failure cases")
    void loginScenarios() {
        service.signup("carol", "pw12345");
        assertTrue(service.login("carol", "pw12345"));
        assertFalse(service.login("carol", "wrong"));
        assertFalse(service.login("nouser", "pw12345"));
    }
}
