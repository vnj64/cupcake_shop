package com.example.steamkey;

import com.example.steamkey.models.Product;
import com.example.steamkey.models.User;
import com.example.steamkey.models.enums.Role;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void shouldAddProductToUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setName("John Doe");
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        user.setRoles(roles);

        Product product = new Product();
        product.setTitle("Sample Product");

        user.addProductToUser(product);

        assertEquals(1, user.getProducts().size());
        assertEquals(user, product.getUser());
    }

    @Test
    public void shouldCheckAdminRole() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        user.setRoles(roles);

        assertTrue(user.isAdmin());
    }

    @Test
    public void shouldNotBeAdmin() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);

        assertFalse(user.isAdmin());
    }

}

