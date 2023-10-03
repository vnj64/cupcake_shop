package com.example.steamkey.controllers.api;

import com.example.steamkey.models.User;
import com.example.steamkey.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController

@RequiredArgsConstructor
public class UserControllerApi {
    private final UserService userService;
    @GetMapping("/api/users/profile")
    public User profile(Principal principal) {
        return userService.getUserByPrincipal(principal);
    }

    @PostMapping("/api/users/registration")
    public String createUser(@RequestBody User user) {
        if (!userService.createUser(user)) {
            return "User with email: " + user.getEmail() + " already exists";
        }
        return "Registration successful";
    }

    @GetMapping("/api/users/user/{user}")
    public User userInfo(@PathVariable("user") User user, Principal principal) {
        return user;
    }
}

