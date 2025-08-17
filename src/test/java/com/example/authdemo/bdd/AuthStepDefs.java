package com.example.authdemo.bdd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.authdemo.user.InMemoryUserRepository;
import com.example.authdemo.user.UserService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SuppressWarnings("unused")
public class AuthStepDefs {
    private final InMemoryUserRepository repo = new InMemoryUserRepository();
    private final UserService service = new UserService(repo, new BCryptPasswordEncoder());

    private String currentUsername;
    private String currentPassword;
    private boolean loginResult;

    @Given("a new user with username {string} and password {string}")
    public void a_new_user_with_username_and_password(String username, String password) {
        this.currentUsername = username;
        this.currentPassword = password;
    }

    @When("the user signs up")
    public void the_user_signs_up() {
        service.signup(currentUsername, currentPassword);
    }

    @When("the user attempts to login with password {string}")
    public void the_user_attempts_to_login_with_password(String attemptPassword) {
        loginResult = service.login(currentUsername, attemptPassword);
    }

    @Then("the login should be successful")
    public void the_login_should_be_successful() {
        assertTrue(loginResult, "Expected login to succeed");
    }

    @Then("the login should fail")
    public void the_login_should_fail() {
        assertFalse(loginResult, "Expected login to fail");
    }
}
