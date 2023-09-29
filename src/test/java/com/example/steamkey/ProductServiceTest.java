package com.example.steamkey;

import com.example.steamkey.models.Product;
import com.example.steamkey.models.User;
import com.example.steamkey.repositories.ProductRepository;
import com.example.steamkey.repositories.UserRepository;
import com.example.steamkey.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListProducts_WithTitle_ReturnsMatchingProducts() {
        String title = "Test Title";
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        when(productRepository.findByTitle(title)).thenReturn(expectedProducts);
        List<Product> actualProducts = productService.listProducts(title);
        assertEquals(expectedProducts, actualProducts);
        verify(productRepository).findByTitle(title);
    }

    @Test
    void testListProducts_WithoutTitle_ReturnsAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        when(productRepository.findAll()).thenReturn(expectedProducts);
        List<Product> actualProducts = productService.listProducts(null);
        assertEquals(expectedProducts, actualProducts);
        verify(productRepository).findAll();
    }

    @Test
    void testSaveProduct_ValidPrincipal_CallsRepositorySave() throws IOException {
        Principal principal = mock(Principal.class);
        User user = new User();
        when(userRepository.findByEmail(principal.getName())).thenReturn(user);
        Product product = new Product();
        productService.saveProduct(principal, product);
        assertEquals(user, product.getUser());
        verify(productRepository).save(product);
    }

    @Test
    void testSaveProduct_NullPrincipal_SetsDefaultUser() throws IOException {
        Product product = new Product();
        productService.saveProduct(null, product);
        assertNotNull(product.getUser());
        verify(userRepository, never()).findByEmail(any());
        verify(productRepository).save(product);
    }



    @Test
    void testGetProductById_ExistingId_ReturnsProduct() {
        Long productId = 1L;
        Product expectedProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));
        Product actualProduct = productService.getProductById(productId);
        assertEquals(expectedProduct, actualProduct);
        verify(productRepository).findById(productId);
    }
}
