package com.example.authdemo.tdd;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.authdemo.user.InMemoryUserRepository;
import com.example.authdemo.user.UserService;

/**
 * Demonstrates RED phase: we introduce a regression allowing null username/password
 * and assert we expect IllegalArgumentException instead of NPE or silent behavior.
 */
public class NullInputUserServiceTest {

    private UserService service;

    @BeforeEach
    void setup() {
        service = new UserService(new InMemoryUserRepository(), new BCryptPasswordEncoder());
    }

    @Test
    @DisplayName("Signup with null username should throw IllegalArgumentException")
    void signupNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> service.signup(null, "password1"));
    }

    @Test
    @DisplayName("Signup with null password should throw IllegalArgumentException")
    void signupNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> service.signup("userX", null));
    }

    @Test
    @DisplayName("Login with null username should throw IllegalArgumentException")
    void loginNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> service.login(null, "pw123456"));
    }

    @Test
    @DisplayName("Login with null password should throw IllegalArgumentException")
    void loginNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> service.login("userY", null));
    }
}
