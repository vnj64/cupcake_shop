package com.example.steamkey.services;

import ch.qos.logback.classic.Logger;
import com.example.steamkey.models.Product;
import com.example.steamkey.models.User;
import com.example.steamkey.repositories.ProductRepository;
import com.example.steamkey.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.example.steamkey.common.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private Logger log;

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Principal principal, Product product) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        productRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteProduct(User user, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Продукт не найден."));
        if (product.getUser().equals(user)) {
            productRepository.delete(product);
        }
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}