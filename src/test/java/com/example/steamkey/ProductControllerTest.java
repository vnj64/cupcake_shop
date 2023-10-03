package com.example.steamkey;

import com.example.steamkey.models.Product;
import com.example.steamkey.models.User;
import com.example.steamkey.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import com.example.steamkey.controllers.ProductController;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    void products_ShouldReturnProductsPage() {
        String title = "search word";
        User user = mock(User.class);
        List<Product> products = Collections.emptyList();

        when(productService.listProducts(title)).thenReturn(products);
        when(productService.getUserByPrincipal(principal)).thenReturn(user);

        String result = productController.products(title, principal, model);

        assertEquals("products", result);
        verify(model).addAttribute("products", products);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("searchWord", title);
    }


    @Test
    void createProduct_ShouldSaveProductAndRedirectToUserProductsPage() throws IOException {
        Product product = mock(Product.class);

        String result = productController.createProduct(product, principal);

        assertEquals("redirect:/my/products", result);
        verify(productService).saveProduct(principal, product);
    }

    @Test
    void deleteProduct_ShouldDeleteProductAndRedirectToUserProductsPage() {
        Long productId = 123L;
        User user = mock(User.class);

        when(productService.getUserByPrincipal(principal)).thenReturn(user);

        String result = productController.deleteProduct(productId, principal);

        assertEquals("redirect:/my/products", result);
        verify(productService).deleteProduct(user, productId);
    }

    @Test
    void userProducts_ShouldReturnUserProductsPage() {
        User user = mock(User.class);
        List<Product> products = Collections.emptyList();

        when(productService.getUserByPrincipal(principal)).thenReturn(user);
        when(user.getProducts()).thenReturn(products);

        String result = productController.userProducts(principal, model);

        assertEquals("my-products", result);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("products", products);
    }
}

