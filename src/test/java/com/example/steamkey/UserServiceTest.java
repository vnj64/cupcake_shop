package com.example.steamkey;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.steamkey.models.User;
import com.example.steamkey.repositories.UserRepository;
import com.example.steamkey.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

//    @Test
//    void testCreateUser_UserDoesNotExist() {
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
//        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
//
//        boolean result = userService.createUser(user);
//
//        assertTrue(result);
//        assertTrue(user.isActive());
//        assertEquals("encodedPassword", user.getPassword());
//        assertEquals(Collections.singleton(Role.ROLE_USER), user.getRoles());
//
//        verify(userRepository, times(1)).findByEmail(user.getEmail());
//        verify(passwordEncoder, times(1)).encode(user.getPassword());
//        verify(userRepository, times(1)).save(user);
//    }

    @Test
    void testCreateUser_UserExists() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        boolean result = userService.createUser(user);

        assertFalse(result);

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verifyNoMoreInteractions(passwordEncoder, userRepository);
    }

    @Test
    void testList() {
        List<User> userList = Arrays.asList(
                new User(),
                new User()
        );

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.list();

        assertEquals(userList, result);

        verify(userRepository, times(1)).findAll();
    }

}

