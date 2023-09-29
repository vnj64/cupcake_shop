package com.example.steamkey;

import com.example.steamkey.controllers.UserController;
import com.example.steamkey.models.User;
import com.example.steamkey.models.Product;
import com.example.steamkey.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private Model model;
    @Mock
    private Principal principal;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void login_ShouldReturnLoginPage() {
        User user = mock(User.class);

        when(userService.getUserByPrincipal(principal)).thenReturn(user);

        String result = userController.login(principal, model);

        assertEquals("login", result);
        verify(model).addAttribute("user", user);
    }

    @Test
    void profile_ShouldReturnProfilePage() {
        User user = mock(User.class);

        when(userService.getUserByPrincipal(principal)).thenReturn(user);

        String result = userController.profile(principal, model);

        assertEquals("profile", result);
        verify(model).addAttribute("user", user);
    }

    @Test
    void registration_ShouldReturnRegistrationPage() {
        User user = mock(User.class);

        when(userService.getUserByPrincipal(principal)).thenReturn(user);

        String result = userController.registration(principal, model);

        assertEquals("registration", result);
        verify(model).addAttribute("user", user);
    }

    @Test
    void createUser_ShouldCreateUserAndRedirectToLoginPage() {
        User user = mock(User.class);

        when(userService.createUser(user)).thenReturn(true);

        String result = userController.createUser(user, model);

        assertEquals("redirect:/login", result);
        verify(userService).createUser(user);
    }

    @Test
    void createUser_WithExistingUser_ShouldReturnRegistrationPageWithError() {
        User user = mock(User.class);

        when(userService.createUser(user)).thenReturn(false);

        String result = userController.createUser(user, model);

        assertEquals("registration", result);
        verify(model).addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
    }

    @Test
    void userInfo_ShouldReturnUserInfoPage() {
        User user = mock(User.class);
        User userByPrincipal = mock(User.class);
        List<Product> products = Collections.emptyList();

        when(user.getProducts()).thenReturn(products);
        when(userService.getUserByPrincipal(principal)).thenReturn(userByPrincipal);

        String result = userController.userInfo(user, model, principal);

        assertEquals("user-info", result);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("userByPrincipal", userByPrincipal);
        verify(model).addAttribute("products", products);
    }
}
