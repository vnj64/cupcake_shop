package com.example.steamkey.controllers;

import com.example.steamkey.models.User;

import com.example.steamkey.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/login", produces = {"application/json", "text/xml"})
    public String login(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);

        if (user.isAdmin()) {
            Map<String, Object> tokenData = new HashMap<>();
            tokenData.put("userID", user.getUserId().toString());

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setClaims(tokenData);
            String key = "StrongKey#1";
            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
            System.out.println(token);

            model.addAttribute("token", token);
        } else {
            model.addAttribute("token", null);
        }

        return "login";
    }


    @GetMapping(value = "/profile", produces = {"application/json", "text/xml"})
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("userID", user.getUserId().toString());

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(tokenData);
        String key = "StrongKey#1";
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
        System.out.println(token);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping(value = "/registration", produces = {"text/html;charset=UTF-8"})
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping(value = "/registration", produces = {"application/json", "text/xml"})
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/user/{user}", produces = {"application/json", "text/xml"})
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }
}
