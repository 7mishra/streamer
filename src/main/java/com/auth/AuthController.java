package com.auth;

import com.auth.payloads.request.NewUser;
import com.auth.payloads.request.UserLogin;
import com.auth.payloads.request.UserIdentifier;
import com.auth.payloads.response.NewUserCreationResponse;
import com.common.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Spring will automatically inject AuthService here
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/health")
    public String health() {
        return "Auth Server Up and Running";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> newUser(@RequestBody NewUser newUser) {
        System.out.println("Creating user");
        System.out.println(newUser.getPassword());
        System.out.println(newUser.getUsername());
        System.out.println(newUser.getEmail());

        Optional<User> user  = this.authService.createUser(newUser);
        System.out.println(user.toString());
        if (user.isPresent()) {
            System.out.println(user);
            NewUserCreationResponse obj = new NewUserCreationResponse(user.get().getUsername(), "User Created", "");
            System.out.println("User Created");
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        } else {
            System.out.println("User Failed to be created");
            NewUserCreationResponse obj = new NewUserCreationResponse("", "User Failed to be created", "");
            return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public String validateUser(@RequestBody UserLogin getUser) {
        return "validating new user";
    }

    @PostMapping("/get_user")
    public String getUser(@RequestBody UserIdentifier getUser) {
        return "validating new user";
    }

    @PostMapping("/logout")
    public String logoutUser(@RequestBody UserIdentifier logoutUser) {
        return "logging user out";
    }

}
