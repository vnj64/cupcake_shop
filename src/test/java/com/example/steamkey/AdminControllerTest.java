package com.example.steamkey;

import com.example.steamkey.controllers.AdminController;
import com.example.steamkey.models.User;
import com.example.steamkey.models.enums.Role;
import com.example.steamkey.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void userBan_ShouldBanUserAndRedirectToAdminPage() {
        Long userId = 1L;

        String result = adminController.userBan(userId);

        assertEquals("redirect:/admin", result);
        verify(userService).banUser(userId);
    }

    @Test
    void userEdit_ShouldReturnUserEditPage() {
        User user = mock(User.class);
        Principal principal = mock(Principal.class);

        String result = adminController.userEdit(user, model, principal);

        assertEquals("user-edit", result);
        verify(model).addAttribute(eq("user"), eq(user));  // Provide matchers for all arguments
        verify(model).addAttribute(eq("user"), any(User.class));
        verify(model).addAttribute(eq("roles"), eq(Role.values()));
    }

    @Test
    void userEdit_ShouldChangeUserRolesAndRedirectToAdminPage() {
        User user = mock(User.class);
        Map<String, String> form = new HashMap<>();

        String result = adminController.userEdit(user, form);

        assertEquals("redirect:/admin", result);
        verify(userService).changeUserRoles(user, form);
    }
}
