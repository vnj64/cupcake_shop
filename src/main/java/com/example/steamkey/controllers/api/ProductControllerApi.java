package com.example.steamkey.controllers.api;

import com.example.steamkey.models.Product;
import com.example.steamkey.models.User;
import com.example.steamkey.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerApi {
    private final ProductService productService;

    @GetMapping("/api")
    public List<Product> productsApi(@RequestParam(name = "searchWord", required = false) String title, Principal principal) {
        return productService.listProducts(title);
    }

    @GetMapping(value = "/api/v1/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> productInfoApi(@PathVariable Long id, Principal principal) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/api/v1/product/create")
    public ResponseEntity<Void> createProductApi(@RequestBody Product product, Principal principal) {
        try {
            productService.saveProduct(principal, product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/api/v1/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id, Principal principal) {
        productService.deleteProduct(productService.getUserByPrincipal(principal), id);
    }

    @GetMapping("/api/v1/my/products")
    public ResponseEntity userProductsApi(Principal principal) {
        User user = productService.getUserByPrincipal(principal);
        return ResponseEntity.ok(user.getProducts());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the product.");
    }
}
