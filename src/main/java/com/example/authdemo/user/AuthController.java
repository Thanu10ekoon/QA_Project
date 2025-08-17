package com.example.authdemo.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        userService.signup(request.username(), request.password());
        return ResponseEntity.ok("SIGNED_UP");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean success = userService.login(request.username(), request.password());
        if(success) return ResponseEntity.ok("LOGIN_OK");
        return ResponseEntity.status(401).body("LOGIN_FAILED");
    }

    public record SignupRequest(String username, String password) {}
    public record LoginRequest(String username, String password) {}
}
