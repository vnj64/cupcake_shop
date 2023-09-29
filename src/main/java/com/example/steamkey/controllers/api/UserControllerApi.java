package com.example.steamkey.controllers.api;

import com.example.steamkey.models.User;
import com.example.steamkey.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController

@RequiredArgsConstructor
public class UserControllerApi {
    private final UserService userService;

    @PostMapping("/api/users/login")
    public String login(Principal principal) {
        User user = userService.getUserByPrincipal(principal);

        if (user.isAdmin()) {
            Map<String, Object> tokenData = new HashMap<>();
            tokenData.put("userID", user.getUserId().toString());

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setClaims(tokenData);
            String key = "abc123"; // It's better to retrieve the key from a secure location like environment variables
            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
            System.out.println(token);

            return token;
        }

        return "";
    }

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

